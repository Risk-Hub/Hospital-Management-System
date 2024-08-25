console.log('Admin Appointment Page');


// document.addEventListener('DOMContentLoaded', function() {
    populateDropdowns();
    showAppointmentsList();
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
                            </tr>
                            `;
                        }
                    });
                }
            });
        });
    }
}
