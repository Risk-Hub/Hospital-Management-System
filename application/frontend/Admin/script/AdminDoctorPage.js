console.log("Admin Doctor Page");

const hamBurger = document.querySelector(".toggle-btn");
hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});



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
    document.getElementById('headingOnTable').innerHTML = `All Doctors(${count})`;
    console.log(count);
}



//This function will auto-populate table with the data present in LocalStorage
function autoPopulateTableData(){
    let doctors = localStorage.getItem("doctorList");
    console.log(doctors);
    if(doctors == null){
        doctorsObject = [];
        document.getElementById("tableDiv").innerHTML += `
            <center>
                <img src="../resource/img/EmptyFile.svg" alt="" style="height: 6em; margin-top: 2em;"><br><br>
                Nothing to display here! Please add a new doctor.            
            </center>
        `;
        console.log("ok")
    }
    else{
        doctorsObject = JSON.parse(doctors);
        let tableBody = document.getElementById("tableBody");
        doctorsObject.forEach(element => {
            tableBody.innerHTML += `
                <tr>
                    <th scope="row">${element.id}</th>
                    <td>${element.name}</td>
                    <td>
                        <button type="button" class="btn btn-sm btn-primary"><i class="lni lni-pencil"></i> Edit</button>
                        <button type="button" class="btn btn-sm btn-primary"><i class="lni lni-eye"></i> View</button>
                        <button type="button" class="btn btn-sm btn-danger"><i class="lni lni-trash-can"></i> Remove</button>
                    </td>
                </tr>
            `;
        });
    }
}



countOfDoctors();
autoPopulateTableData();