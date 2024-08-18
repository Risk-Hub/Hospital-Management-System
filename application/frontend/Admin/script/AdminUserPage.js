console.log("Admin User Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});


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
    document.getElementById('headingOnTable').innerHTML = `All Users(${count})`;
    console.log(users);
}


//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData(){
    let users = localStorage.getItem("userList");
    if(users == null){
        usersObject = [];
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/user.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new User.            
            </center>
        `;
        console.log("ok")
    }
    else{
        usersObject = JSON.parse(users);
        let tableBody = document.getElementById("tableBody");  
        tableBody.innerHTML = "";      
        usersObject.forEach(element => {            
            tableBody.innerHTML += `
                <tr>
                    <th scope="row">${element.id}</th>
                    <td>${element.name}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewUserModal"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteUserModal"><i class="lni lni-trash-can"></i> Remove</button>
                    </td>
                </tr>
            `;
        });
    }
}


//This method adds a new doctor to LocalStorage
function addNewUserToList() {
    let users = localStorage.getItem("userList");
    if(users == null){
        usersObject = [];
    }
    else{
        usersObject = JSON.parse(users);
    }

    let userId = document.getElementById("userId").value;
    console.log(userId);
    let userName = document.getElementById("userName").value;
    console.log(userName);
    let userEmail = document.getElementById("userEmail").value;
    console.log(userEmail);
    let userContactNumber = document.getElementById("userContactNumber").value;
    console.log(userContactNumber);
    
    let obj = {
        "id":userId.toLowerCase(),
        "name":userName,
        "email":userEmail.toLowerCase() + "@hms.com",
        "contact":"+91-" + userContactNumber
    };
    if(usersObject.some(user => user.id === obj.id)){
        console.log("User already exist");        //This needs to be handled. Made for testing purpose.
        return;
    }
    else{
        usersObject.push(obj);
    }
    console.log(usersObject);
    localStorage.setItem("userList",JSON.stringify(usersObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
}

function editUser(){
    //Need to be worked upon
}

function editUser(){
    //Need to be worked upon
}



countOfUsers();
autoPopulateTableData();