console.log("Doctor Test to Patient Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
    document.querySelector("#sidebar").classList.toggle("expand");
});


function countOfPatients() {
    let patients = [];

    // Get patients from localStorage
    let localStoragePatients = localStorage.getItem("patientList");

    if (localStoragePatients != null) {
        patients = JSON.parse(localStoragePatients);
    }

            let uniquePatients = [];
            let patientIds = new Set();

            patients.forEach(patient => {
                if (!patientIds.has(patient.id)) {
                    patientIds.add(patient.id);
                    uniquePatients.push(patient);
                }
            });

            // Update count and display it
            let count = uniquePatients.length;
            document.getElementById('headingOnTable').innerHTML = `All Patients(${count})`;

            // Optional: Log merged patients
            console.log(uniquePatients);
}

//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData() {
    let patientsObject = [];

    // Get patients from localStorage
    let localStoragePatients = localStorage.getItem("patientList");

    if (localStoragePatients != null && localStoragePatients != "[]") {
        patientsObject = JSON.parse(localStoragePatients);
    }

            // Remove duplicates based on unique 'id'
            let uniquePatients = [];
            let patientIds = new Set();

            patientsObject.forEach(patient => {
                if (!patientIds.has(patient.id)) {
                    patientIds.add(patient.id);
                    uniquePatients.push(patient);
                }
            });

            // Clear the table body
            let tableBody = document.getElementById("tableBody");
            tableBody.innerHTML = "";

            // Populate the table with the combined patient data
            if (uniquePatients.length === 0) {
                document.getElementById("tableDiv").innerHTML += `
                    <center>
                        <img src="../resource/img/patient.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                        Nothing to display here! Please add a new Patient.            
                    </center>
                `;
                console.log("No patients to display");
            } else {
                uniquePatients.forEach(element => {
                    tableBody.innerHTML += `
                        <tr>
                            <th scope="row">${element.id}</th>
                            <td>${element.name}</td>
                            <td>
                                <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewPatientModal" onclick="viewPatientDetails('${element.id}')"><i class="lni lni-eye"></i> View</button>
                            </td>
                        </tr>
                    `;
                });
            }
}

const diseaseToTests = {
    "FLU": ["Blood Test", "Influenza Test"],
    "BACKPAIN": ["X-Ray", "MRI"],
    "HEADACHE": ["CT Scan", "Eye Examination"],
    "DEPRESSION": ["Psychological Evaluation"],
    "OSTEOPOROSIS": ["Bone Density Test", "X-Ray"],
    "CHRONICKIDNEYDISEASE": ["Blood Test", "Urine Test"],
    "ARTHRITIS": ["X-Ray", "MRI"],
    "MIGRAINE": ["MRI", "CT Scan"],
    "HYPERTENSION": ["Blood Pressure Monitoring", "Electrocardiogram (ECG)"],
    "DIABETES": ["Fasting Blood Sugar", "HbA1c Test"],
    "ASTHMA": ["Pulmonary Function Test", "Chest X-Ray"]
    // Add more mappings as needed
};



//This method adds a new doctor to LocalStorage
function addNewPatientToList() {
    let patients = localStorage.getItem("patientList");
    if (patients == null) {
        patientsObject = [];
    }
    else {
        patientsObject = JSON.parse(patients);
    }

    let patientId = document.getElementById("id").value;
    console.log(patientId);
    let patientName = document.getElementById("name").value;
    console.log(patientName);
    let appointmentDate = document.getElementById("appointment_date").value;
    console.log(appointmentDate);
    let appointmentSlot = document.getElementById("appointment_slot").value;
    console.log(appointmentSlot);
    let diseaseName = document.getElementById("disease_name").value.trim().toUpperCase();
    console.log(diseaseName);
    

    let disease = {
        name: diseaseName,
        tests: diseaseToTests[diseaseName] || [],
    };

    let obj = {
        "id": patientId,
        "name": patientName,
        "appointment_date": appointmentDate,
        "appointment_slot": appointmentSlot,
        "disease": disease
    };
    if (patientsObject.some(patient => patient.id === obj.id)) {
        console.log("Patient already exist");        //This needs to be handled. Made for testing purpose.
    }
    else {
        patientsObject.push(obj);
    }
    console.log(patientsObject);
    localStorage.setItem("patientList", JSON.stringify(patientsObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
}





//This function displays the details of a patient on Modal.
function viewPatientDetails(id) {


    let patientsObject = JSON.parse(localStorage.getItem("patientList"));
    let selectedPatient = patientsObject.find(patient => patient.id === id);

    if (selectedPatient) {

        document.getElementById("exampleModalLabel3").innerText = `Details of Patient: ${selectedPatient.id}`;

        // Debugging: Log the disease_name
        console.log("Patient Disease Name:", selectedPatient.disease_name);
        let disease = selectedPatient.disease_name ? selectedPatient.disease_name.trim().toUpperCase() : 'N/A';
        console.log(disease);
        
        // let suggestedTests = selectedPatient.disease?.tests.join(", ") || 'N/A';
        let suggestedTests = selectedPatient.disease.tests;
        console.log(suggestedTests);

        // Constructing the modal body with patient details
        document.querySelector("#viewPatientModal .modal-body").innerHTML = `
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="patientIdInput" placeholder="" value="${selectedPatient.id || 'N/A'}" disabled>
                <label for="patientIdInput">Patient ID</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="patientNameInput" placeholder="" value="${selectedPatient.name || 'N/A'}" disabled>
                <label for="patientNameInput">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="diseaseNameInput" placeholder="" value="${selectedPatient.disease?.name || 'N/A'}" disabled>
                <label for="diseaseNameInput">Disease Name</label>
            </div>
            <div class="form-floating mb-3">
                <textarea class="form-control" id="recommendedTestsInput" disabled>${suggestedTests}</textarea>

                <label for="recommendedTestsInput">Suggested Tests</label>
            </div>
        `;

        // <textarea class="form-control" id="recommendedTestsInput" disabled>${(selectedPatient.disease?.tests || []).join(', ') || 'N/A'}</textarea>
    }
    console.log(selectedPatient.name, selectedPatient.id, selectedPatient.appointment_date, selectedPatient.appointment_slot);

}


countOfPatients();
autoPopulateTableData();