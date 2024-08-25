// console.log("Admin Doctor Page");

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
    // console.log(count);
}



//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData(){
    let doctors = localStorage.getItem("doctorList");
    if(doctors == null || doctors == "[]"){
        doctorsObject = [];
        tableBody.innerHTML = "";    
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/EmptyFile.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new doctor.            
            </center>
        `;
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
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editDoctorModal" onclick="editDoctorDetails('${element.id}')"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewDoctorModal" onclick="viewDoctorDetails('${element.id}')"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteDoctorModal" onclick="confirmDelete('${element.id}')"><i class="lni lni-trash-can"></i> Remove</button>
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
    let doctorName = document.getElementById("doctorName").value;
    let doctorEmail = document.getElementById("doctorEmail").value;
    let doctorContactNumber = document.getElementById("doctorContactNumber").value;
    
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
        alert("Doctor already exist");        //This needs to be handled. Made for testing purpose.
    }
    else{
        doctorsObject.push(obj);
    }
    // console.log(doctorsObject);
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
    countOfDoctors();
}


//This function make addition/deletion to the doctor's list present in LocalStorage as according when a patient profile gets added/deleted
function updateAppointmentsInLocalStorage() {
    let patientsObject = JSON.parse(localStorage.getItem("patientList"));

    // Get the list of valid patient IDs
    const validPatientIds = patientsObject.map(patient => patient.id);

    // Iterate over the doctors and their appointments
    doctorsObject.forEach(doctor => {
        doctor.appointment.forEach(appointment => {
            appointment.timeslot.forEach(slot => {
                // Check if the patient ID is not in the validPatientIds list
                if (slot.patientId && !validPatientIds.includes(slot.patientId)) {
                    // Update the slot to be available and remove the patientId
                    slot.isAvailable = true;
                    slot.patientId = null;
                }
            });
        });
    });

    // Save the updated doctorsObject to LocalStorage
    localStorage.setItem('doctorList', JSON.stringify(doctorsObject));
}



//This function displays the details of a Doctor on Modal.
function viewDoctorDetails(id){
    let doctorsObject = JSON.parse(localStorage.getItem("doctorList"));
    let selectedDoctor = doctorsObject.find(doctor => doctor.id === id);
    let patientsObject = JSON.parse(localStorage.getItem("patientList"));

    if(selectedDoctor){
        document.getElementById("exampleModalLabel3").innerText = `Details of Doctor: ${selectedDoctor.id}`;
        document.querySelector("#viewDoctorModal .modal-body").innerHTML = `
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedDoctor.id}" disabled>
                <label for="floatingInputValue">Doctor ID</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedDoctor.name}" disabled>
                <label for="floatingInputValue">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedDoctor.email}" disabled>
                <label for="floatingInputValue">Email</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedDoctor.contact}" disabled>
                <label for="floatingInputValue">Contact Number</label>
            </div>
        `;
    }

    let tableHtml = `
    <div class="col-12" id="tableDiv">
        <table class="table table-striped table-light">
            <thead>
                <tr class="highlight">
                    <th scope="col">Date</th>
                    <th scope="col">Time Slot</th>
                    <th scope="col">Availability(Yes/No)</th>
                    <th scope="col">Patient ID</th>
                    <th scope="col">Patient Name</th>
                </tr>
            </thead>
            <tbody class="table-group-divider" id="tableBody">`;

    doctorsObject.filter(doc => doc.id === id).forEach(doctor => {
        doctor.appointment.forEach(appointment => {
            appointment.timeslot.forEach(slot => {
                if(slot.isAvailable){
                    tableHtml += `
                        <tr>
                            <td scope="row">${appointment.date}</td>
                            <td>${slot.time}</td>
                            <td colspan="3">Yes</td>
                        </tr>`;
                }
                else{
                    let patient = patientsObject.find(p => p.id === slot.patientId);
                    tableHtml += `
                        <tr>
                            <td scope="row">${appointment.date}</td>
                            <td>${slot.time}</td>
                            <td>No</td>
                            <td>${patient.id}</td>
                            <td>${patient.name}</td>
                        </tr>`;
                }
            });
        });
    });

    tableHtml += `
            </tbody>
        </table>
    </div>`;

    document.querySelector("#viewDoctorModal .modal-body").innerHTML += tableHtml;
}



//This function sets the selected doctor ID to be edited in a global variable.
let doctorIdToBeEdited = null;
function editDoctorDetails(id){
    document.getElementById("floatingInputDisabled").value = "";
    document.getElementById("doctorName2").value = "";
    document.getElementById("doctorEmail2").value = "";
    document.getElementById("doctorContactNumber2").value = "";
    let doctors = JSON.parse(localStorage.getItem("doctorList"));
    let selectedDoctor = doctors.find(doctor => doctor.id === id);
    if(selectedDoctor){
        doctorIdToBeEdited = id;
        document.getElementById("floatingInputDisabled").value = selectedDoctor.id;
        document.getElementById("doctorName2").value = selectedDoctor.name;
        document.getElementById("doctorEmail2").value = selectedDoctor.email;
        document.getElementById("doctorContactNumber2").value = selectedDoctor.contact;
    }
    // doctorIdToBeEdited = null;
}


//This function finally saves the modified value
function saveEditedDoctor(){
    let doctors = JSON.parse(localStorage.getItem("doctorList"));
    doctors = doctors.map(doctor => {
        if (doctor.id === doctorIdToBeEdited) {
            return {
                ...doctor,
                name: document.getElementById("doctorName2").value,
                email: document.getElementById("doctorEmail2").value,
                contact: document.getElementById("doctorContactNumber2").value
            };
        }
        return doctor;
    });
    localStorage.setItem("doctorList", JSON.stringify(doctors));
    autoPopulateTableData();
}


//This function sets the selected Doctor ID to be deleted in a global variable.
let doctorIdToBeDeleted = null;
function confirmDelete(id) {
    // console.log("hello");
    doctorIdToBeDeleted = id;
}

//This function deletes a doctor's record by the 'id' selected by the user
function deleteDoctorRecord(){
    if(doctorIdToBeDeleted !== null){
        let doctors = localStorage.getItem("doctorList");
        doctorsObject = JSON.parse(doctors);
        const index = doctorsObject.findIndex(doctor => doctor.id === doctorIdToBeDeleted);
        doctorsObject.splice(index,1);
        localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
        autoPopulateTableData();
        countOfDoctors();
    }
    doctorIdToBeDeleted = null;
}



countOfDoctors();
autoPopulateTableData();
updateAppointmentsInLocalStorage();