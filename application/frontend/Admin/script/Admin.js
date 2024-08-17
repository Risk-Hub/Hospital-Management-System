console.log("ok");

//This method fetches doctors data from DoctorsList.json file
async function fetchDoctorsList() {
  const response = await fetch(".././resource/DoctorsList.json");
  if (!response.ok) {
    throw new Error("Failed to fetch doctors list.");
  } 
  else {
    return await response.json();
  }
}



//This method sets initial data from Doctor.json file to LocalStorage
async function setInitialDataToLocalStorage() {
    localStorage.clear();                           //This needs to be deleted. Made for testing purpose.
    let doctorList = null;
    try{
        doctorList = await fetchDoctorsList();
    }
    catch(error){
        document.getElementsByTagName("body").innerHTML += "SOme error occured";
    }

    if(doctorList != null){
        // console.log(doctorList);        
        localStorage.setItem("doctorList",JSON.stringify(doctorList));
    }
    else{
        console.log("Doctor's data was not fetched.");
    }
}



//This is a temporary method. It is used to check if the list is fetched scuccesfully or not.
async function displayDoctorsListToConsole() {      
//   try {
//     const list = await fetchDoctorsList();
//     if (list) {
//       console.log(list);
//     }
//   } catch (error) {
//     document.getElementsByTagName("body").innerHTML += "SOme error occured";
//   }
    let doctors = localStorage.getItem("doctorList");
    doctors = JSON.parse(doctors);
    console.log(doctors);
}


//This method adds a new doctor to LocalStorage
async function addNewDoctorToList() {
    // let list;
    // try{
    //     list = await fetchDoctorsList();
    // }
    // catch(error){
    //     document.getElementsByTagName("body").innerHTML += "SOme error occured";
    // }

    let doctors = localStorage.getItem("doctorList");
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
    }

    let obj = {"id":"doc03","name":"Example Doctor03"};
    if(doctorsObject.some(doctor => doctor.id === obj.id)){
        console.log("Doctor already exist");        //This needs to be handled. Made for testing purpose.
        return;
    }
    doctorsObject.push(obj);
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
}


//This function updates a pre-existing doctor
async function updateDoctor(id,newNname) {
    let doctors = localStorage.getItem("doctorList");
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
    }

    doctorsObject = doctorsObject.map(doctor =>{
        if(doctor.id == id){
            doctor.name = newNname;
        }
        return doctor;
    });

    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
}


//This function deletes a doctor's record by the 'id' provided by the user
async function deleteDoctorRecord(id){
    let doctors = localStorage.getItem("doctorList");
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
    }

    console.log("Before deletetion: ");         //Hit n Trial
    console.log(doctorsObject);
    const index = doctorsObject.findIndex(doctor => doctor.id === id);
    if(index !== -1){
        doctorsObject.splice(index,1);
    }
    else{
        console.log('Doctor not found');
        
    }
    console.log("After deletetion: ");    //Hit n Trial
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
}

async function execute() {
    await setInitialDataToLocalStorage();
    await displayDoctorsListToConsole();
    await addNewDoctorToList();
    await displayDoctorsListToConsole();
    await updateDoctor("doc01","Harsh");
    await displayDoctorsListToConsole();
    await deleteDoctorRecord("doc03");
    await displayDoctorsListToConsole();
  }
  
execute();



//Todo: delteDoctor() function and appointments :)