let loginData = [
    {
        "username":"admin",
        "password":"admin@123"
    },
    {
        "username":"doc01",
        "password":"doc01@123"
    },
    {
        "username":"doc02",
        "password":"doc02@123"
    },
    {
        "username":"user01",
        "password":"user01@123"
    },
    {
        "username":"user02",
        "password":"user02@123"
    },
    {
        "username":"user03",
        "password":"user03@123"
    },
    {
        "username":"user04",
        "password":"user04@123"
    }
];



function validate(event) {
    // Get the values of the username and password fields
    let username = document.getElementById('username').value.trim();
    let password = document.getElementById('password').value.trim();

    // Check if both fields are filled in
    if (!username || !password) {
        alert("Please fill in both fields.");
        event.preventDefault();
        return;
    }
    
    // Captcha validation
    const captchaInput = document.getElementById('captchaInput').value.trim();
    const captchaText = document.getElementById('captchaText').innerText;
    if (captchaInput !== captchaText) {
        alert('Captcha is incorrect. Please try again.');
        event.preventDefault();
        generateCaptcha(); // Generate a new CAPTCHA after incorrect attempt
        return;
    }

    const user = loginData.find(user => user.username === username && user.password === password);

    if(user){

        localStorage.setItem("user",user.username);
        sessionStorage.setItem("isLoggedIn","true");

        if(user.username === "admin"){
            window.location.href = "../Admin/AdminHomePage.html";
        }
        else if(user.username.startsWith("doc")){
            window.location.href = "../Doctor/DoctorHomePage.html";
        }
        else if(user.username.startsWith("user")){
            window.location.href = "../User/UserPage.html"
        }
    }
    else{
        alert("Invalid username or password!");
        return;
    }
}

// Captcha generation
function generateCaptcha() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let captchaText = '';
    for (let i = 0; i < 6; i++) {
        captchaText += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    document.getElementById('captchaText').innerText = captchaText;
}

// Refresh CAPTCHA when clicking the refresh link
document.getElementById('refreshCaptcha').addEventListener('click', function(e) {
    e.preventDefault();
    generateCaptcha();
});

// Initial CAPTCHA generation when page loads
window.onload = generateCaptcha;
