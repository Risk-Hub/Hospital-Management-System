document.getElementById('loginForm').addEventListener('submit', function(event) {
    // Get the values of the username and password fields
    let username = document.getElementById('username').value.trim();
    let password = document.getElementById('password').value.trim();

    // Check if both fields are filled in
    if (username === "" || password === "") {
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

    

    // Password validation: must be username@123
    let expectedPassword = `${username}@123`;
    if (password !== expectedPassword) {
        alert("Invalid password. The password should be 'username@123'.");
        event.preventDefault();
        return;
    }


    // If all validations pass, redirect to demo.html
    event.preventDefault(); // Prevent the default form submission behavior
    window.location.href = "demo.html"; // Redirect to demo.html
});

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
