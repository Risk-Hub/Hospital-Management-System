console.log('Admin Appointment Page');


// document.addEventListener('DOMContentLoaded', function() {
    populateDropdowns();
    showAppointmentsList();
    countOfAppointments();
    const doctorSelect = document.getElementById('doctorSelect');
    doctorSelect.addEventListener('change', function() {
        populateAvailableDates();
    });

    const appointmentDateInput = document.getElementById('appointmentDate');
    appointmentDateInput.addEventListener('change', function() {
        populateTimeSlots();
    });

// });


function populateDropdowns() {
    console.log('ok');
    
    // Fetch doctor and patient data from localStorage
    let doctors = localStorage.getItem("doctorList");
    // let doctorsObject;
    if(doctors){
        doctorsObject = JSON.parse(doctors);
    }
    else{
        doctorsObject = []
    }    

    let patients = localStorage.getItem("patientList");
    // let patientsObject;
    if(patients){
        patientsObject = JSON.parse(patients);
    }
    else{
        patientsObject = []
    }    

    let doctorSelect = document.getElementById('doctorSelect');
    let patientSelect = document.getElementById('patientSelect');

    // Populate doctors dropdown
    doctorsObject.forEach(doctor => {
        let option = document.createElement('option');
        option.value = doctor.id;
        option.textContent = `${doctor.id} - ${doctor.name}`;
        doctorSelect.appendChild(option);
    });
    

    // Populate patients dropdown
    patientsObject.forEach(patient => {
        console.log(patient);
        let option = document.createElement('option');
        option.value = patient.id;
        option.textContent = `${patient.id} - ${patient.name}`;
        patientSelect.appendChild(option);
    });
}



function populateAvailableDates() {
    const doctorSelect = document.getElementById('doctorSelect');
    const selectedDoctorId = doctorSelect.value;
    const appointmentDateInput = document.getElementById('appointmentDate');
    const doctorList = JSON.parse(localStorage.getItem('doctorList')) || [];

    // Clear previous dates
    appointmentDateInput.value = "";
    appointmentDateInput.min = "";
    // appointmentDateInput.max = "";

    if (selectedDoctorId !== 'Select Doctor\'s ID') {
        const selectedDoctor = doctorList.find(doctor => doctor.id === selectedDoctorId);
        if (selectedDoctor) {
            const availableDates = selectedDoctor.appointment.map(appointment => appointment.date);
            if (availableDates.length > 0) {
                appointmentDateInput.min = availableDates[0];
                // appointmentDateInput.max = availableDates[availableDates.length - 1];
            }
        }
    }
}



function populateTimeSlots() {
    const doctorSelect = document.getElementById('doctorSelect');
    const selectedDoctorId = doctorSelect.value;
    const appointmentDateInput = document.getElementById('appointmentDate');
    const selectedDate = appointmentDateInput.value;
    const timeSlotSelect = document.getElementById('timeSlotSelect');
    const doctorList = JSON.parse(localStorage.getItem('doctorList')) || [];

    // Clear previous time slots
    timeSlotSelect.innerHTML = '<option selected>Select Time Slot</option>';

    if (selectedDoctorId !== 'Select Doctor\'s ID' && selectedDate) {
        const selectedDoctor = doctorList.find(doctor => doctor.id === selectedDoctorId);
        const selectedAppointment = selectedDoctor.appointment.find(appointment => appointment.date === selectedDate);

        if (selectedAppointment) {
            const availableTimeSlots = selectedAppointment.timeslot.filter(slot => slot.isAvailable);
            
            const option = document.createElement('option');
            if (availableTimeSlots.length > 0) {
                availableTimeSlots.forEach(slot => {
                    option.value = slot.time;
                    option.textContent = slot.time;
                    timeSlotSelect.appendChild(option);
                });
            } 
            else {
                // alert('No available time slots for this date.');
                timeSlotSelect.innerHTML = `<option value="invalidValue" selected>No time slot is available!</option>`;
                timeSlotSelect.disabled = true;
            }
        }
    }
}



function scheduleNewAppointment(e) {
    // Here you can handle the scheduling logic, such as saving the new appointment to localStorage or sending it to a server
    const selectedDoctor = document.getElementById('doctorSelect').value;
    const selectedPatientId = document.getElementById('patientSelect').value;
    const selectedAppointmentDate = document.getElementById('appointmentDate').value;
    const selectedTimeSlot = document.getElementById('timeSlotSelect').value;
    if(selectedDoctor === "invalidValue"){
        alert('Please select a doctor!');
        e.preventDefault();
    }
    else if(selectedPatientId === "invalidValue"){
        alert("Please select a patient!");
        e.preventDefault();
    }
    else if(!selectedAppointmentDate){
        alert("Please select a date!");
        e.preventDefault();
    }
    else if(selectedTimeSlot === "invalidValue"){
        alert("Please select a time slot!");
        e.preventDefault();
    }
    else{        
        const doctorList = JSON.parse(localStorage.getItem('doctorList')) || [];
        let doctor = doctorList.find(doctor => doctor.id === selectedDoctor);
        let appointment = doctor.appointment.find(appointmt => appointmt.date === selectedAppointmentDate);
        if(!appointment){
            appointment = {
                date:selectedAppointmentDate,
                timeslot:[]
            };
            doctor.appointment.push(appointment);
        }

        let timeslot = appointment.timeslot.find(slot => slot.time === selectedTimeSlot);
        if(timeslot){
            timeslot.isAvailable = false;
            timeslot.patientId = selectedPatientId;
        }
        else{
            appointment.timeslot.push({
                time:selectedTimeSlot,
                isAvailable:false,
                patientId:selectedPatientId
            })
        }
        localStorage.setItem("doctorList",JSON.stringify(doctorList));
        showAppointmentsList();
    }
}


function countOfAppointments(){
    const targetDates = ["2024-08-17", "2024-08-18", "2024-08-19"];
    let doctors = localStorage.getItem("doctorList");
    let patients = localStorage.getItem("patientList");
    let count = 0;
    if(patients == null){
        patientsObject = [];
    }
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        doctorsObject = JSON.parse(doctors);
        doctorsObject.forEach(doctor => {
            doctor.appointment.forEach(appointment => {
                if(targetDates.includes(appointment.date)){
                    appointment.timeslot.forEach(slot => {
                        if(!slot.isAvailable && slot.patientId){
                            let patient = patientsObject.find(p => p.id === slot.patientId);
                            if(patient){
                                count++;
                            }
                        }
                    });
                }
            });
        });
    }
    document.getElementById('headingOnTable').innerText = `Upcoming Appointments(${count})`;
    console.log(count);
    
}


function showAppointmentsList(){
    const targetDates = ["2024-08-17", "2024-08-18", "2024-08-19"];
    let doctors = localStorage.getItem("doctorList");
    let patients = localStorage.getItem("patientList");
    if(patients == null){
        patientsObject = [];
    }
    if(doctors == null){
        doctorsObject = [];
    }
    else{
        let tableBody = document.getElementById("tableBody");
        tableBody.innerHTML = "";
        doctorsObject = JSON.parse(doctors);
        doctorsObject.forEach(doctor => {
            doctor.appointment.forEach(appointment => {
                if(targetDates.includes(appointment.date)){
                    appointment.timeslot.forEach(slot => {
                        if (!slot.isAvailable && slot.patientId) {
                        let patient = patientsObject.find(p => p.id === slot.patientId);                        
                            tableBody.innerHTML += `
                            <tr>
                                <td>${appointment.date}</td>
                                <td>${slot.time}</td>
                                <td>${doctor.id}</td>
                                <td>${doctor.name}</td>
                                <td>${patient.id}</td>
                                <td>${patient.name}</td>
                                <td>
                                    <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editAppointmentModal" onclick="editAppointmentDetails(['${appointment.date}','${slot.time}','${doctor.id}','${doctor.name}','${patient.id}','${patient.name}'])"><i class="lni lni-pencil"></i> Edit</button>
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteAppointmentModal" onclick="confirmDelete(['${doctor.id}','${appointment.date}','${slot.time}'])"><i class="lni lni-trash-can"></i> Remove</button>
                                </td>
                            </tr>
                            `;
                        }
                    });
                }
            });
        });
    }
}

//This function sets the selected doctor ID to be edited in a global variable.
let doctorIdToBeEdited = null;
function editAppointmentDetails(detailsArray){
    const [date, time, doctorId, doctorName, patientId, patientName] = detailsArray;
    document.getElementById("floatingInputDisabled").value = "";
    document.getElementById("patientId").value = "";
    document.getElementById("appointmentDate2").value = "";
    document.getElementById("timeslot").value = "";
    console.log(date + " " + time + " " + doctorId + " " + doctorName + " " + patientId + " " + patientName);
    

    doctorIdToBeEdited = doctorId;
    document.getElementById("floatingInputDisabled").value = doctorId + " - " + doctorName;
    document.getElementById("patientId").value = patientId + " - " + patientName;
    document.getElementById("appointmentDate2").value = date;
    document.getElementById("appointmentDate2").min = date;

    const selectedDoctor = doctorsObject.find(doctor => doctor.id === doctorId);
        const selectedAppointment = selectedDoctor.appointment.find(appointment => appointment.date === date);
        if (selectedAppointment) {
            const availableTimeSlots = selectedAppointment.timeslot.filter(slot => slot.isAvailable);
            
            const option = document.createElement('option');
            if (availableTimeSlots.length > 0) {
                availableTimeSlots.forEach(slot => {
                    option.value = slot.time;
                    option.textContent = slot.time;
                    timeSlotSelect.appendChild(option);
                });
            } 
            else {
                // alert('No available time slots for this date.');
                timeSlotSelect.innerHTML = `<option value="invalidValue" selected>No time slot is available!</option>`;
                timeSlotSelect.disabled = true;
            }
        }
    document.getElementById("timeslot").value = time;
}

function updateAvailableTimeSlots(selectedDate){
    
    
    //Ye wala function harsh ke zimme






    const timeslot = document.getElementById("timeslot");

    // Clear existing options in the timeslot dropdown
    timeslot.innerHTML = '<option value="invalidValue" selected>Select Time Slot</option>';

    // Example: Fetch available time slots based on the selected date
    // Find the doctor object using the doctorId
    const doctor = doctorsObject.find(doc => doc.id === doctorIdToBeEdited);
    if (!doctor) return []; // Return an empty array if doctor not found

    // Find the appointment object for the specific date
    const appointmentForDate = doctor.appointment.find(appt => appt.date === selectedDate);
    if (!appointmentForDate) return []; // Return an empty array if no appointments are found for that date

    // Filter available time slots
    const availableTimeSlots = appointmentForDate.timeslot.filter(slot => slot.isAvailable);

    // Populate the timeslot dropdown with the available time slots
    availableTimeSlots.forEach(slot => {
        const option = document.createElement("option");
        option.value = slot;
        option.textContent = slot;
        timeslot.appendChild(option);
    });
}


//This function sets the selected Doctor's ID (whose appointment is to be deleted) in a global variable.
let doctorIdToBeDeleted = null;
let appointmentDateToBeDeleted = null;
let timeSlotToBeDeleted = null;
function confirmDelete(id,appointmentDate,timeSlot) {
    // console.log("hello");
    doctorIdToBeDeleted = id;
    appointmentDateToBeDeleted = appointmentDate;
    timeSlotToBeDeleted = timeSlot;
}


//This function deletes an appointment record by the 'id' selected by the user
function deleteAppointmentRecord(){
    if(doctorIdToBeDeleted !== null){
        // console.log("ok");
        let doctors = localStorage.getItem("doctorList");
        doctorsObject = JSON.parse(doctors);
        const index = doctorsObject.findIndex(doctor => doctor.id === doctorIdToBeDeleted);
    }
    doctorIdToBeDeleted = null;

    if (doctorIdToBeDeleted !== null) {
        // Get the doctor list from localStorage
        let doctors = localStorage.getItem("doctorList");
        let doctorsObject = JSON.parse(doctors);

        // Find the index of the doctor whose appointment needs to be deleted
        const index = doctorsObject.findIndex(doctor => doctor.id === doctorIdToBeDeleted);

        if (index !== -1) {
            // Find the appointment date in the doctor's appointments
            const appointment = doctorsObject[index].appointment.find(appointmt => appointmt.date === appointmentDateToBeDeleted);

            if (appointment) {
                // Find the specific timeslot to delete (set `isAvailable` to true and `patientId` to null)
                const timeslot = appointment.timeslot.find(slot => slot.time === selectedTimeSlot);

                if (timeslot) {
                    timeslot.isAvailable = true;
                    timeslot.patientId = null;
                }
            }
        }

        // Save the updated doctorsObject back to localStorage
        localStorage.setItem("doctorList", JSON.stringify(doctorsObject));
    }

    // Reset the global variable after deletion
    doctorIdToBeDeleted = null;
    appointmentDateToBeDeleted = null;
    timeSlotToBeDeleted = null;
    console.log('ok');
    console.log(doctorsObject);
    
    showAppointmentsList();
}


// editAppointmentDetails function ko complete karna hai, diary mein pichhe jo diagram banaye hain uske "html populate doc of id (doc id)" pe kaam chal rha hai