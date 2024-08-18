console.log("Admin Doctor Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});



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
    document.getElementById('headingOnTable').innerHTML = `All Doctors(${count})`;
    console.log(count);
}



//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData(){
    let doctors = localStorage.getItem("doctorList");
    console.log(doctors);
    if(doctors == null){
        doctorsObject = [];
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/EmptyFile.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new doctor.            
            </center>
        `;
        console.log("ok")
    }
    else{
        doctorsObject = JSON.parse(doctors);
        let tableBody = document.getElementById("tableBody");
        tableBody.innerHTML = "";    
        doctorsObject.forEach(element => {
            tableBody.innerHTML += `
                <tr>
                    <th scope="row">${element.id}</th>
                    <td>${element.name}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editDoctorModal"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewDoctorModal"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteDoctorModal"><i class="lni lni-trash-can"></i> Remove</button>
                    </td>
                </tr>
            `;
        });
    }
}


//This method adds a new doctor to LocalStorage
function addNewDoctorToList() {
    let doctors = localStorage.getItem("doctorList");
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
    }

    let doctorId = document.getElementById("doctorId").value;
    console.log(doctorId);
    let doctorName = document.getElementById("doctorName").value;
    console.log(doctorName);
    let doctorEmail = document.getElementById("doctorEmail").value;
    console.log(doctorEmail);
    let doctorContactNumber = document.getElementById("doctorContactNumber").value;
    console.log(doctorContactNumber);
    
    let obj = {
        "id":doctorId.toLowerCase(),
        "name":doctorName,
        "email":doctorEmail.toLowerCase() + "@hms.com",
        "contact":"+91-" + doctorContactNumber,
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
    }
    else{
        doctorsObject.push(obj);
    }
    console.log(doctorsObject);
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
}

function editDoctor(){
    //Need to be worked upon
}

function viewDoctor(){
    //Need to be worked upon
}

//This function deletes a doctor's record by the 'id' provided by the user
//Need to be worked upon
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

    // if(doctorsObject.length == 0){
    //     localStorage.removeItem("doctorList");
    // }
    // else{
    //     localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
    // }
    console.log("After deletetion: ");    //Hit n Trial
}



countOfDoctors();
autoPopulateTableData();