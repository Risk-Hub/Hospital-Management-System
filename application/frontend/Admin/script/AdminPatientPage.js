// console.log("Admin Patient Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});


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
    document.getElementById('headingOnTable').innerHTML = `All Patients(${count})`;
    console.log(patients);
}


//This function will auto-populate table with the data present in LocalStorage
let ageToBeFiltered = -1;
let genderToBeFiltered = null;
function autoPopulateTableData() {
    ageToBeFiltered = document.getElementById("ageFilterList").value;
    genderToBeFiltered = document.getElementById("genderFilterList").value;

    let patients = localStorage.getItem("patientList");
    let tableBody = document.getElementById("tableBody");
    tableBody.innerHTML = ""; // Clear existing data

    if (patients == null || patients == "[]") {
        patientsObject = [];
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/patient.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new Patient.            
            </center>
        `;
    } 
    else {
        patientsObject = JSON.parse(patients);

        patientsObject.forEach(element => {
            // Apply filters for age and gender
            let ageMatches = (ageToBeFiltered === "" || element.age == ageToBeFiltered);
            let genderMatches = (genderToBeFiltered === "invalidValue" || element.gender === genderToBeFiltered);

            if (ageMatches && genderMatches) {
                tableBody.innerHTML += `
                    <tr>
                        <th scope="row">${element.id}</th>
                        <td>${element.name}</td>
                        <td>${element.age}</td>
                        <td>${element.gender}</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editPatientModal" onclick="editPatientDetails('${element.id}')"><i class="lni lni-pencil"></i> Edit</button>
                            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewPatientModal" onclick="viewPatientDetails('${element.id}')"><i class="lni lni-eye"></i> View</button>
                            <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deletePatientModal" onclick="confirmDelete('${element.id}')"><i class="lni lni-trash-can"></i> Remove</button>
                        </td>
                    </tr>
                `;
            }
        });
    }
}

function clearFilters() {
    document.getElementById("ageFilterList").value = "";
    document.getElementById("genderFilterList").selectedIndex = 0;
    autoPopulateTableData(); // Reload the table without any filters
}



//This method adds a new doctor to LocalStorage
function addNewPatientToList() {
    let patients = localStorage.getItem("patientList");
    if(patients == null){
        patientsObject = [];
    }
    else{
        patientsObject = JSON.parse(patients);
    }

    let patientId = document.getElementById("patientId").value;
    console.log(patientId);
    let patientName = document.getElementById("patientName").value;
    console.log(patientName);
    let patientContactNumber = document.getElementById("patientContactNumber").value;
    console.log(patientContactNumber);
    let patientAge = document.getElementById("patientAge").value;
    console.log(patientAge);
    let patientGender = document.getElementById("patientGender").value;
    console.log(patientGender);
    
    let obj = {
        "id":patientId.toLowerCase(),
        "name":patientName,
        "contact":"+91-" + patientContactNumber,
        "age":patientAge,
        "gender":patientGender
    };
    if(patientsObject.some(patient => patient.id === obj.id)){
        console.log("Patient already exist");        //This needs to be handled. Made for testing purpose.
    }
    else{
        patientsObject.push(obj);
    }
    console.log(patientsObject);
    localStorage.setItem("patientList",JSON.stringify(patientsObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
}





//This function sets the selected patient ID to be edited in a global variable.
let patientIdToBeEdited = null;
function editPatientDetails(id){
    document.getElementById("floatingInputDisabled").value = "";
    document.getElementById("patientName2").value = "";
    document.getElementById("patientContactNumber2").value = "";
    document.getElementById("patientAge2").value = "";
    document.getElementById("patientGender2").value = "";
    let patients = JSON.parse(localStorage.getItem("patientList"));
    let selectedPatient = patients.find(patient => patient.id === id);
    if(selectedPatient){
        patientIdToBeEdited = id;
        document.getElementById("floatingInputDisabled").value = selectedPatient.id;
        document.getElementById("patientName2").value = selectedPatient.name;
        document.getElementById("patientContactNumber2").value = selectedPatient.contact;
        document.getElementById("patientAge2").value = selectedPatient.age;
        document.getElementById("patientGender2").value = selectedPatient.gender;
    }
    // patientIdToBeEdited = null;
}


//This function finally saves the modified value
function saveEditedPatient(){
    let patients = JSON.parse(localStorage.getItem("patientList"));
    patients = patients.map(patient => {
        if (patient.id === patientIdToBeEdited) {
            return {
                ...patient,
                name: document.getElementById("patientName2").value,
                contact: document.getElementById("patientContactNumber2").value,
                age: document.getElementById("patientAge2").value,
                gender: document.getElementById("patientGender2").value
            };
        }
        return patient;
    });
    localStorage.setItem("patientList", JSON.stringify(patients));
    autoPopulateTableData();
}






//This function displays the details of a patient on Modal.
function viewPatientDetails(id){
    let patientsObject = JSON.parse(localStorage.getItem("patientList"));
    let selectedPatient = patientsObject.find(patient => patient.id === id);
    if(selectedPatient){
        document.getElementById("exampleModalLabel3").innerText = `Details of Patient: ${selectedPatient.id}`;
        document.querySelector("#viewPatientModal .modal-body").innerHTML = `
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedPatient.id}" disabled>
                <label for="floatingInputValue">Patient ID</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedPatient.name}" disabled>
                <label for="floatingInputValue">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInputValue" placeholder="" value="${selectedPatient.contact}" disabled>
                <label for="floatingInputValue">Contact Number</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInputValue" placeholder="" value="${selectedPatient.age}" disabled>
                <label for="floatingInputValue">Age(in years)</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInputValue" placeholder="" value="${selectedPatient.gender.charAt(0).toUpperCase() + selectedPatient.gender.substring(1)}" disabled>
                <label for="floatingInputValue">Gender</label>
            </div>
        `;
    }
}






//This function sets the selected patient ID to be deleted in a global variable.
let patientIdToBeDeleted = null;
function confirmDelete(id) {
    console.log("hello");
    patientIdToBeDeleted = id;
}

//This function deletes a patient's record by the 'id' selected by the patient
function deletePatientRecord(){
    if(patientIdToBeDeleted !== null){
        // console.log("ok");
        let patients = localStorage.getItem("patientList");
        patientsObject = JSON.parse(patients);
        const index = patientsObject.findIndex(patient => patient.id === patientIdToBeDeleted);
        patientsObject.splice(index,1);
        localStorage.setItem("patientList",JSON.stringify(patientsObject));
    }
    countOfPatients();
    autoPopulateTableData();
    patientIdToBeDeleted = null;
}



countOfPatients();
autoPopulateTableData();