document.getElementById('loginForm').addEventListener('submit', function(event) {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    if (username.trim() === "" || password.trim() === "") {
        alert("Please fill in both fields.");
        event.preventDefault();
    }
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

// Form submission validation
document.getElementById('loginForm').addEventListener('submit', function(e) {
    const captchaInput = document.getElementById('captchaInput').value;
    const captchaText = document.getElementById('captchaText').innerText;
    if (captchaInput !== captchaText) {
        e.preventDefault();
        alert('Captcha is incorrect. Please try again.');
        generateCaptcha(); // Generate a new CAPTCHA after incorrect attempt
    }
});
