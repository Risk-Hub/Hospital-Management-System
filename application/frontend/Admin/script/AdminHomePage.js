console.log("ok");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});

let dummyDoctorData = [
    {
        "id":"doc01",
        "name":"Example Doctor01",
        "email":"doc01@hms.com",
        "contact":"+91-9470271892",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            },
            {
                "date":"2024-08-18",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            }
        ]
    },
    {
        "id":"doc02",
        "name":"Example Doctor02",
        "email":"doc02@hms.com",
        "contact":"+91-9420032752",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            },
            {
                "date":"2024-08-18",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            }
        ]
    }
];

let dummyUserData = [
    {
        "id":"user01",
        "name":"Example User01",
        "email":"user01@hms.com",
        "contact":"+91-9470271892"
    },
    {
        "id":"user02",
        "name":"Example User02",
        "email":"user02@hms.com",
        "contact":"+91-9420032752"
    },
    {
        "id":"user03",
        "name":"Example User03",
        "email":"user03@hms.com",
        "contact":"+91-9420032752"
    }
];

let dummyPatientData = [
    {
        "id":"pat01",
        "name":"Example Patient01",
        "contact":"+91-9470271892",
        "age":23,
        "gender":"male"
    },
    {
        "id":"pat02",
        "name":"Example Patient02",
        "contact":"+91-9420032752",
        "age":35,
        "gender":"female"
    },
    {
        "id":"pat03",
        "name":"Example Patient03",
        "contact":"+91-9470271892",
        "age":60,
        "gender":"male"
    },
    {
        "id":"pat04",
        "name":"Example Patient04",
        "contact":"+91-9420032752",
        "age":45,
        "gender":"female"
    }
];


//This method fetches doctors data from DoctorsList.json file
// async function fetchDoctorsList() {
//   const response = await fetch(".././resource/DoctorsList.json");
//   if (!response.ok) {
//     throw new Error("Failed to fetch doctors list.");
//   } 
//   else {
//     return await response.json();
//   }
// }



//This method sets initial data from Doctor.json file to LocalStorage
function setInitialDataToLocalStorage() {
    localStorage.clear();                           //This needs to be deleted. Made for testing purpose.
    localStorage.setItem("doctorList",JSON.stringify(dummyDoctorData));
    localStorage.setItem("userList",JSON.stringify(dummyUserData));
    localStorage.setItem("patientList",JSON.stringify(dummyPatientData));
}



//This is a temporary method. It is used to check if the list is fetched scuccesfully or not.
//This need to be replaced with countOfDoctors function.
function displayDoctorsListToConsole() {      
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

//This is a temporary method. It is used to check if the list is fetched scuccesfully or not.
//This need to be replaced with countOfUsers function.
function displayUsersListToConsole(){
    let users = localStorage.getItem("userList");
    users = JSON.parse(users);
    console.log(users);
}


//This is a temporary method. It is used to check if the list is fetched scuccesfully or not.
//This need to be replaced with countOfPatients function.
function displayPatientsListToConsole(){
    let patients = localStorage.getItem("patientList");
    patients = JSON.parse(patients);
    console.log(patients);
}

function countOfDoctors() {
    let doctors = localStorage.getItem("doctorList");
    let count = 0;
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
        count = doctorsObject.length;
    }
    document.getElementsByTagName('h4')[0].innerText = `${count}`;
    console.log(count);
}

function countOfUsers() {
    let users = localStorage.getItem("userList");
    let count = 0;
    if(users == null){
        usersObject = [];
    }
    else{
        usersObject = JSON.parse(users);
        count = usersObject.length;
    }
    document.getElementsByTagName('h4')[2].innerText = `${count}`;
    console.log(count);
}


function countOfPatients() {
    let patients = localStorage.getItem("patientList");
    let count = 0;
    if(patients == null){
        patientsObject = [];
    }
    else{
        patientsObject = JSON.parse(patients);
        count = patientsObject.length;
    }
    document.getElementsByTagName('h4')[4].innerText = `${count}`;
    console.log(count);
}

//This method adds a new doctor to LocalStorage
function addNewDoctorToList() {
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

    let obj = {
        "id":"doc03",
        "name":"Example Doctor03",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            },
            {
                "date":"2024-08-18",
                "timeslot":["09:00","11:00","13:00","14:00"],
                "isAvailable":[true,true,true,true]
            }
        ]
    };
    if(doctorsObject.some(doctor => doctor.id === obj.id)){
        console.log("Doctor already exist");        //This needs to be handled. Made for testing purpose.
        return;
    }
    doctorsObject.push(obj);
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
}


//This function updates a pre-existing doctor
function updateDoctor(id,newNname) {
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
function deleteDoctorRecord(id){
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

    if(doctorsObject.length == 0){
        localStorage.removeItem("doctorList");
    }
    else{
        localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
    }
    console.log("After deletetion: ");    //Hit n Trial
}

setInitialDataToLocalStorage();
displayDoctorsListToConsole();
// addNewDoctorToList();
displayDoctorsListToConsole();
displayUsersListToConsole();
updateDoctor("doc01","Harsh");
displayDoctorsListToConsole();
// deleteDoctorRecord("doc03");
// deleteDoctorRecord("doc02");
// deleteDoctorRecord("doc01");
// displayDoctorsListToConsole();
countOfDoctors();
countOfUsers();
countOfPatients();


//Todo: delteDoctor() function and appointments :)