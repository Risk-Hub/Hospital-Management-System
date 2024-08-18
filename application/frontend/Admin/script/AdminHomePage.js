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




//This method sets initial data from Doctor.json file to LocalStorage
function setInitialDataToLocalStorage() {
    localStorage.clear();                           //This needs to be deleted. Made for testing purpose.
    localStorage.setItem("doctorList",JSON.stringify(dummyDoctorData));
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




setInitialDataToLocalStorage();
countOfDoctors();
countOfUsers();
countOfPatients();


//Todo: appointments :)