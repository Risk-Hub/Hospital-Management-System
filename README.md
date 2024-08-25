# Hospital-Management-System


# Folder Structure
The project is organized into the following folders:

hospital-management-system/
│
├── Admin/
│   ├── AdminHomePage.html
│   ├── AdminDoctorPage.html
│   ├── AdminAppointmentPage.html
│   ├── AdminPatientPage.html
│   ├── AdminUserPage.html
│   ├── scripts/
│   │   ├── adminHomePage.js
│   │   ├── adminDoctorPage.js
│   │   ├── adminAppointmentPage.js
│   │   ├── adminPatientPage.js
│   │   └── adminUserPage.js
│   └── css/
│       └── adminStyles.css
│
├── Doctor/
│   ├── DoctorAppointmentView.html
│   ├── DoctorHomePage.html
│   ├── DoctorMedicine.html
│   ├── DoctorSchedule.html
│   ├── DoctorTest.html
│   ├── scripts/
│   │   ├── doctorAppointmentView.js
│   │   ├── doctorHomePage.js
│   │   ├── doctorMedicine.js
│   │   ├── doctorSchedule.js
│   │   └── doctorTest.js
│   └── css/
│       └── doctorStyles.css
│
├── User/
│   ├── AddPatient.html
│   ├── BookPatient.html
│   ├── UserHomePage.html
│   ├── ViewPatientAppointment.html
│   ├── scripts/
│   │   ├── addPatient.js
│   │   ├── bookPatient.js
│   │   ├── userHomePage.js
│   │   └── viewPatientAppointment.js
│   └── css/
│       └── userStyles.css
│
└── Login/
    ├── Demo.html
    ├── Login.html
    ├── script.js
    └── styles.css


# Configuration
There are no special configuration steps required beyond placing the files in the correct directories. Ensure all dependencies (if any) are correctly referenced in your HTML and JavaScript files.


1. Admin Folder
Contains HTML pages and associated assets for the administrative interface.

HTML Pages:

AdminHomePage.html
AdminDoctorPage.html
AdminAppointmentPage.html
AdminPatientPage.html
AdminUserPage.html
Scripts Folder: Contains JavaScript files specific to the admin functionality.

CSS Folder: Contains a single CSS file used across all admin pages.

2. Doctor Folder
Contains HTML pages and associated assets for the doctor’s interface.

HTML Pages:

DoctorAppointmentView.html
DoctorHomePage.html
DoctorMedicine.html
DoctorSchedule.html
DoctorTest.html
Scripts Folder: Contains JavaScript files specific to doctor functionalities.

CSS Folder: Contains a single CSS file used across all doctor pages.

3. User Folder
Contains HTML pages and associated assets for the user’s interface.

HTML Pages:

AddPatient.html
BookPatient.html
UserHomePage.html
ViewPatientAppointment.html
Scripts Folder: Contains JavaScript files specific to user functionalities.

CSS Folder: Contains a single CSS file used across all user pages.

4. Login Folder
Contains login and demo-related pages and assets.

HTML Pages:
Demo.html
Login.html
Scripts: Contains script.js for login-related JavaScript.
Styles: Contains styles.css for login-related styles

# Credentials
Admin

Username: admin
Password: adminpass
Redirects to: Admin/AdminHomePage.html
Doctor

Username: doctor
Password: doctorpass
Redirects to: Doctor/DoctorHomePage.html
User

Username: user
Password: userpass
Redirects to: User/UserHomePage.html


## Contributing to the Project
Follow these simple steps to submit your first Pull Request (PR):

### 1. Fork the Repository
1. Go to the repository's page.
2. Click on the `Fork` button in the top-right corner of the page. This will create a copy of the repository in your GitHub account.

### 2. Clone Your Forked Repository
1. Clone the forked repository to your local machine.
   ```bash
   git clone https://github.com/your-username/repo-name.git

2. Navigate to the project directory. 
    ```bash
    cd repo-name

### 3. Create a New Branch
    git checkout -b your-branch-name

### 4. Make Your Changes
Make your changes to the codebase, such as adding a new feature, fixing a bug, or updating documentation.

### 5. Commit Your Changes
    1. Stage your changes.
        git add .
    2. Commit your changes with a meaningful message.
        git commit -m "Describe your changes"

### 6. Push to GitHub
Push your changes to your forked repository.
git push origin your-branch-name


### 7. Create a Pull Request
Go to the original repository's page on GitHub.
Click on the Pull Requests tab.
Click on the New Pull Request button.
Select the branch with your changes and create a pull request.
Provide a brief description of the changes you made and submit your pull request.
### 8. Review and Merge
Wait for the maintainers to review your pull request.
Once approved, your changes will be merged into the main branch!
