// console.log("Admin User Page");

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
    // console.log(users);
}


//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData(){
    let users = localStorage.getItem("userList");
    if(users == null || users == "[]"){
        usersObject = [];
        tableBody.innerHTML = "";   
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/user.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new User.            
            </center>
        `;
        // console.log("ok")
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
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal" onclick="editUserDetails('${element.id}')"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#viewUserModal" onclick="viewUserDetails('${element.id}')"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteUserModal" onclick="confirmDelete('${element.id}')"><i class="lni lni-trash-can"></i> Remove</button>
                    </td>
                </tr>
            `;
        });
    }
}


//This method adds a new user to LocalStorage
function addNewUserToList() {
    let users = localStorage.getItem("userList");
    if(users == null){
        usersObject = [];
    }
    else{
        usersObject = JSON.parse(users);
    }

    let userId = document.getElementById("userId").value;
    let userName = document.getElementById("userName").value;
    let userEmail = document.getElementById("userEmail").value;
    let userContactNumber = document.getElementById("userContactNumber").value;
    
    let obj = {
        "id":userId.toLowerCase(),
        "name":userName,
        "email":userEmail.toLowerCase() + "@hms.com",
        "contact":"+91-" + userContactNumber
    };
    if(usersObject.some(user => user.id === obj.id)){
        alert("user already exist");        //This needs to be handled. Made for testing purpose.
    }
    else{
        usersObject.push(obj);
    }
    // console.log(usersObject);
    localStorage.setItem("userList",JSON.stringify(usersObject));
    autoPopulateTableData();        //Auto-populate function's logic need to be revised, displaying incorrect values on table UI.
    countOfUsers();
}




//This function sets the selected user ID to be edited in a global variable.
let userIdToBeEdited = null;
function editUserDetails(id){
    document.getElementById("floatingInputDisabled").value = "";
    document.getElementById("userName2").value = "";
    document.getElementById("userEmail2").value = "";
    document.getElementById("userContactNumber2").value = "";
    let users = JSON.parse(localStorage.getItem("userList"));
    let selectedUser = users.find(user => user.id === id);
    if(selectedUser){
        userIdToBeEdited = id;
        document.getElementById("floatingInputDisabled").value = selectedUser.id;
        document.getElementById("userName2").value = selectedUser.name;
        document.getElementById("userEmail2").value = selectedUser.email;
        document.getElementById("userContactNumber2").value = selectedUser.contact;
    }
    // userIdToBeEdited = null;
}


//This function finally saves the modified value
function saveEditedUser(){
    let users = JSON.parse(localStorage.getItem("userList"));
    users = users.map(user => {
        if (user.id === userIdToBeEdited) {
            return {
                ...user,
                name: document.getElementById("userName2").value,
                email: document.getElementById("userEmail2").value,
                contact: document.getElementById("userContactNumber2").value
            };
        }
        return user;
    });
    localStorage.setItem("userList", JSON.stringify(users));
    autoPopulateTableData();
}



//This function displays the details of a User on Modal.
function viewUserDetails(id){
    let usersObject = JSON.parse(localStorage.getItem("userList"));
    let selectedUser = usersObject.find(user => user.id === id);
    if(selectedUser){
        document.getElementById("exampleModalLabel3").innerText = `Details of User: ${selectedUser.id}`;
        document.querySelector("#viewUserModal .modal-body").innerHTML = `
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedUser.id}" disabled>
                <label for="floatingInputValue">User ID</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedUser.name}" disabled>
                <label for="floatingInputValue">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedUser.email}" disabled>
                <label for="floatingInputValue">Email</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInputValue" placeholder="" value="${selectedUser.contact}" disabled>
                <label for="floatingInputValue">Contact Number</label>
            </div>
        `;
    }
}






//This function sets the selected user ID to be deleted in a global variable.
let userIdToBeDeleted = null;
function confirmDelete(id) {
    // console.log("hello");
    userIdToBeDeleted = id;
}

//This function deletes a user's record by the 'id' selected by the user
function deleteUserRecord(){
    if(userIdToBeDeleted !== null){
        // console.log("ok");
        let users = localStorage.getItem("userList");
        usersObject = JSON.parse(users);
        const index = usersObject.findIndex(user => user.id === userIdToBeDeleted);
        usersObject.splice(index,1);
        localStorage.setItem("userList",JSON.stringify(usersObject));
    }
    countOfUsers();
    autoPopulateTableData();
    userIdToBeDeleted = null;
}



countOfUsers();
autoPopulateTableData();