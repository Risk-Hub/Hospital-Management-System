// console.log("Admin Home Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});

let doctorsObject = [
    {
        "id":"doc01",
        "name":"Example Doctor01",
        "email":"doc01@hms.com",
        "contact":"+91-9470271892",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": false, "patientId": "pat01"},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat02"}
                ]
            },
            {
                "date":"2024-08-18",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": false, "patientId": "pat03"},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat04"}
                ]
            },
            {
                "date":"2024-08-19",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": true, "patientId": null}
                ]
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
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": false, "patientId": "pat04"},
                    {"time":"14:00-16:00", "isAvailable": true, "patientId": null}
                ]
            },
            {
                "date":"2024-08-18",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": false, "patientId": "pat06"},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat07"}
                ]
            },
            {
                "date":"2024-08-19",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat05"}
                ]
            }
        ]
    },
    {
        "id":"doc03",
        "name":"Example Doctor03",
        "email":"doc03@hms.com",
        "contact":"+91-8820749392",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": false, "patientId": "pat08"},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": true, "patientId": null}
                ]
            },
            {
                "date":"2024-08-18",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": true, "patientId": null}
                ]
            },
            {
                "date":"2024-08-19",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat09"}
                ]
            }
        ]
    },
    {
        "id":"doc04",
        "name":"Example Doctor04",
        "email":"doc04@hms.com",
        "contact":"+91-7891234987",
        "appointment":[
            {
                "date":"2024-08-17",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": false, "patientId": "pat10"},
                    {"time":"11:00-13:00", "isAvailable": false, "patientId": "pat11"},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat12"}
                ]
            },
            {
                "date":"2024-08-18",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": false, "patientId": "pat06"},
                    {"time":"14:00-16:00", "isAvailable": false, "patientId": "pat07"}
                ]
            },
            {
                "date":"2024-08-19",
                "timeslot":[
                    {"time":"09:00-11:00", "isAvailable": true, "patientId": null},
                    {"time":"11:00-13:00", "isAvailable": true, "patientId": null},
                    {"time":"14:00-16:00", "isAvailable": true, "patientId": null}
                ]
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
    },
    {
        "id":"user04",
        "name":"Example User04",
        "email":"user04@hms.com",
        "contact":"+91-941234567"
    },
    {
        "id":"user05",
        "name":"Example User05",
        "email":"user05@hms.com",
        "contact":"+91-8888032752"
    },
    {
        "id":"user06",
        "name":"Example User06",
        "email":"user06@hms.com",
        "contact":"+91-6542232752"
    },
    {
        "id":"user07",
        "name":"Example User07",
        "email":"user07@hms.com",
        "contact":"+91-9420005982"
    }
];

let dummyPatientData = [
    {
        "id":"pat01",
        "name":"Example Patient01",
        "contact":"+91-9470271892",
        "age":23,
        "gender":"male",
        "disease": {
            "name": "FLU",
            "tests": ["Blood Test", "Influenza Test"],
            "medicines": ["Paracetamol", "Ibuprofen", "Vitamin C"]
        }
    },
    {
        "id":"pat02",
        "name":"Example Patient02",
        "contact":"+91-9420032752",
        "age":35,
        "gender":"female",
        "disease": {
            "name": "BACKPAIN",
            "tests": ["X-Ray", "MRI"],
            "medicines": ["Aspirin", "Cyclobenzaprine", "Naproxen"]
        }
    },
    {
        "id":"pat03",
        "name":"Example Patient03",
        "contact":"+91-9470271892",
        "age":60,
        "gender":"male",
        "disease": {
            "name": "HEADACHE",
            "tests": ["CT Scan", "Eye Examination"],
            "medicines": ["Amoxicillin", "Cetirizine", "Loratadine"]
        }
    },
    {
        "id":"pat04",
        "name":"Example Patient04",
        "contact":"+91-9420032752",
        "age":45,
        "gender":"female",
        "disease": {
            "name": "FLU",
            "tests": ["Blood Test", "Influenza Test"],
            "medicines": ["Paracetamol", "Ibuprofen", "Vitamin C"]
        }
    },
    {
        "id":"pat05",
        "name":"Example Patient05",
        "contact":"+91-9471234567",
        "age":18,
        "gender":"male",
        "disease": {
            "name": "ASTHMA",
            "tests": ["Pulmonary Function Test", "Chest X-Ray"],
            "medicines": ["Albuterol", "Montelukast", "Fluticasone"]
        }
    },
    {
        "id":"pat06",
        "name":"Example Patient06",
        "contact":"+91-9420032752",
        "age":30,
        "gender":"female",
        "disease": {
            "name": "DIABETES",
            "tests": ["Fasting Blood Sugar", "HbA1c Test"],
            "medicines": ["Metformin", "Glibenclamide", "Insulin"]
        }
    },
    {
        "id":"pat07",
        "name":"Example Patient07",
        "contact":"+91-9470271111",
        "age":52,
        "gender":"male",
        "disease": {
            "name": "HYPERTENSION",
            "tests": ["Blood Pressure Monitoring", "Electrocardiogram (ECG)"],
            "medicines": ["Lisinopril", "Amlodipine", "Hydrochlorothiazide"]
        }
    },
    {
        "id":"pat08",
        "name":"Example Patient08",
        "contact":"+91-9470222233",
        "age":40,
        "gender":"female",
        "disease": {
            "name": "MIGRAINE",
            "tests": ["MRI", "CT Scan"],
            "medicines": ["Sumatriptan", "Ergotamine", "Topiramate"]
        }
    },
    {
        "id":"pat09",
        "name":"Example Patient09",
        "contact":"+91-9470271892",
        "age":67,
        "gender":"male",
        "disease": {
            "name": "ARTHRITIS",
            "tests": ["X-Ray", "MRI"],
            "medicines": ["Ibuprofen", "Methotrexate", "Hydroxychloroquine"]
        }
    },
    {
        "id":"pat10",
        "name":"Example Patient10",
        "contact":"+91-9420054321",
        "age":55,
        "gender":"female",
        "disease": {
            "name": "CHRONIC KIDNEY DISEASE",
            "tests": ["Blood Test", "Urine Test"],
            "medicines": ["Losartan", "Calcium Carbonate", "Epoetin Alfa"]
        }
    },
    {
        "id":"pat11",
        "name":"Example Patient11",
        "contact":"+91-9470271877",
        "age":29,
        "gender":"male",
        "disease": {
            "name": "DEPRESSION",
            "tests": ["Psychological Evaluation"],
            "medicines": ["Sertraline", "Escitalopram", "Bupropion"]
        }
    },
    {
        "id":"pat12",
        "name":"Example Patient12",
        "contact":"+91-9420011223",
        "age":75,
        "gender":"female",
        "disease": {
            "name": "OSTEOPOROSIS",
            "tests": ["Bone Density Test", "X-Ray"],
            "medicines": ["Alendronate", "Raloxifene", "Calcitonin"]
        }
    }
];




//This method sets initial data from Doctor.json file to LocalStorage
function setInitialDataToLocalStorage() {
    // localStorage.clear();                           //This needs to be deleted. Made for testing purpose.
    localStorage.setItem("doctorList",JSON.stringify(doctorsObject));
    localStorage.setItem("userList",JSON.stringify(dummyUserData));
    localStorage.setItem("patientList",JSON.stringify(dummyPatientData));
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
    // console.log(count);
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
    // console.log(count);
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
    // console.log(count);
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
    document.getElementsByTagName('h4')[6].innerText = `${count}`;
    // console.log(count);    
}


//This function is made to update the appointments if in case a patient's profile is deleted.
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



function countOfAppointmentsByEachDoctor(){
    let doctors = localStorage.getItem("doctorList");
    if(doctors){
        doctorsObject = JSON.parse(doctors);
    }
    else{
        doctorsObject = [];
    }

    doctorsObject.forEach(doctor => {
        let totalAppointments = 0;
        doctor.appointment.forEach(appointment =>{
            appointment.timeslot.forEach(slot => {
                if(!slot.isAvailable && slot.patientId){
                    totalAppointments++;
                }
            })
        })
        document.getElementById("countOfAppointmentsByEachDoctor").innerHTML += `<p style="font-size: large; display: inline-block;">${doctor.id} (${doctor.name}) --> </p> <p style="font-size: large; display: inline-block; color: #0d6efd;"><b>${totalAppointments}</b></p><br>`;
    })
}


function countOfAllAppointmentsByEachDay(){
    // Initialize an object to store the number of appointments for each day
    let appointmentsPerDay = {};
    let doctors = localStorage.getItem("doctorList");
    if(doctors){
        doctorsObject = JSON.parse(doctors);
    }
    else{
        doctorsObject = [];
    }

    // Iterate over each doctor in the doctorsObject array
    doctorsObject.forEach(doctor => {
        // Iterate over each appointment of the current doctor
        doctor.appointment.forEach(appointment => {
            // Extract the date of the appointment
            const date = appointment.date;

            // Initialize the count for this date if it doesn't exist in the appointmentsPerDay object
            if (!appointmentsPerDay[date]) {
                appointmentsPerDay[date] = 0;
            }

            // Count the number of booked timeslots for this appointment date
            appointment.timeslot.forEach(slot => {
                if (!slot.isAvailable) {
                    appointmentsPerDay[date]++;
                }
            });
        });
    });

    // Display the total number of appointments for each date
    for (const date in appointmentsPerDay) {
        document.getElementById("countOfAllAppointmentsByEachDay").innerHTML += `<p style="font-size: large; display: inline-block;">No. of appointments on ${date} --> </p> <p style="font-size: large; display: inline-block; color: #0d6efd;"><b>${appointmentsPerDay[date]}</b></p><br>`;
    }
}

function countOfAllAppointmentsByEachPatient(){
    // Initialize an object to store the number of visits for each patient
    let patientVisits = {};

    let doctors = localStorage.getItem("doctorList");
    if(doctors){
        doctorsObject = JSON.parse(doctors);
    }
    else{
        doctorsObject = [];
    }

    // Iterate over each doctor in the doctorsObject array
    doctorsObject.forEach(doctor => {
        // Iterate over each appointment of the current doctor
        doctor.appointment.forEach(appointment => {
            // Iterate over each timeslot in the appointment
            appointment.timeslot.forEach(slot => {
                // Check if the timeslot is booked (patientId is not null)
                if (slot.patientId) {
                    const patientId = slot.patientId;

                    // Initialize the count for this patient if it doesn't exist in the patientVisits object
                    if (!patientVisits[patientId]) {
                        patientVisits[patientId] = 0;
                    }

                    // Increment the count for this patient
                    patientVisits[patientId]++;
                }
            });
        });
    });

    // Display the total number of visits for each patient
    for (const patientId in patientVisits) {
        document.getElementById("countOfAppointmentsByEachPatient").innerHTML += `
            <div class="col-md-3 mb-3">
                <p style="font-size: large; display: inline-block;">${patientId} --> </p> 
                <p style="font-size: large; display: inline-block; color: #0d6efd;"><b>${patientVisits[patientId]}</b></p>
            </div>`;
    }

}

function countOfPatientsByGender(){
    // Initialize an object to store the count of patients by gender
    let genderCount = {};

    // Iterate over each patient in the dummyPatientData array
    dummyPatientData.forEach(patient => {
        const gender = patient.gender;

        // Initialize the count for this gender if it doesn't exist in the genderCount object
        if (!genderCount[gender]) {
            genderCount[gender] = 0;
        }

        // Increment the count for this gender
        genderCount[gender]++;
    });

    // Display the number of patients by gender
    for (const gender in genderCount) {
        document.getElementById("countOfPatientsByGender").innerHTML += `<p style="font-size: large; display: inline-block;">No. of ${gender.charAt(0).toUpperCase() + gender.slice(1)} patients --> </p> <p style="font-size: large; display: inline-block; color: #0d6efd;"><b>${genderCount[gender]}</b></p><br>`;
    }
}

setInitialDataToLocalStorage();
updateAppointmentsInLocalStorage();
countOfDoctors();
countOfUsers();
countOfPatients();
countOfAppointments();
countOfAppointmentsByEachDoctor();
countOfAllAppointmentsByEachDay();
countOfAllAppointmentsByEachPatient();
countOfPatientsByGender();
