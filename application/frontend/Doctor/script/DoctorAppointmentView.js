console.log("Admin Doctor Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
    document.querySelector("#sidebar").classList.toggle("expand");
});

// TODO
// update the local storage


//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData() {
    let doctors = localStorage.getItem("doctorList");
    console.log(doctors);
    if (doctors == null || doctors == "[]") {
        doctorsObject = [];
        tableBody.innerHTML = "";
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/EmptyFile.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new doctor.            
            </center>
        `;
        console.log("ok")
    }
    else {
        doctorsObject = JSON.parse(doctors);
        let tableBody = document.getElementById("tableBody");
        tableBody.innerHTML = "";
        doctorsObject.forEach(element => {
            tableBody.innerHTML += `
                <tr>
                    <th scope="row">${element.id}</th>
                    <td>${element.name}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewDoctorModal" onclick="viewDoctorDetails('${element.id}')"><i class="lni lni-eye"></i> View</button>
                    </td>
                </tr>
            `;
        });
    }
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
function viewDoctorDetails(id) {
    let doctorsObject = JSON.parse(localStorage.getItem("doctorList"));
    let selectedDoctor = doctorsObject.find(doctor => doctor.id === id);
    let patientsObject = JSON.parse(localStorage.getItem("patientList"));

    if (selectedDoctor) {
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
                    <th scope="col">Patient ID</th>
                    <th scope="col">Patient Name</th>
                    <th scope="col">Date</th>
                    <th scope="col">Time Slot</th>
                    <th scope="col">Availability(Yes/No)</th>
                </tr>
            </thead>
            <tbody class="table-group-divider" id="tableBody">`;

    doctorsObject.filter(doc => doc.id === id).forEach(doctor => {
        doctor.appointment.forEach(appointment => {
            appointment.timeslot.forEach(slot => {
                if (slot.isAvailable) {
                    tableHtml += `
                        <tr>
                            <td></td>
                            <td></td>
                            <td scope="row">${appointment.date}</td>
                            <td>${slot.time}</td>
                            <td colspan="3">Yes</td>
                        </tr>`;
                }
                else {
                    let patient = patientsObject.find(p => p.id === slot.patientId);
                    tableHtml += `
                        <tr>
                            <td>${patient.id}</td>
                            <td>${patient.name}</td>
                            <td scope="row">${appointment.date}</td>
                            <td>${slot.time}</td>
                            <td>No</td>
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

autoPopulateTableData();
updateAppointmentsInLocalStorage();