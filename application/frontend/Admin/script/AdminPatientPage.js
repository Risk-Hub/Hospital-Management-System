console.log("Admin Patient Page");

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
function autoPopulateTableData(){
    let patients = localStorage.getItem("patientList");
    if(patients == null){
        patientsObject = [];
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/patient.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new Patient.            
            </center>
        `;
        console.log("ok")
    }
    else{
        patientsObject = JSON.parse(patients);
        let tableBody = document.getElementById("tableBody");  
        tableBody.innerHTML = "";      
        patientsObject.forEach(element => {            
            tableBody.innerHTML += `
                <tr>
                    <th scope="row">${element.id}</th>
                    <td>${element.name}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editPatientModal"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewPatientModal"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deletePatientModal"><i class="lni lni-trash-can"></i> Remove</button>
                    </td>
                </tr>
            `;
        });
    }
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

function editPatient(){
    //Need to be worked upon
}

function deletePatient(){
    //Need to be worked upon
}



countOfPatients();
autoPopulateTableData();