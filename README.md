# Hospital-Management-System


# Folder Structure
The project is organized into the following folders:


### Admin Directory

The `Admin` directory contains pages and scripts specifically for administrative tasks, including managing doctors, appointments, patients, and user accounts.

- **HTML Files**:
  - `AdminHomePage.html`: Dashboard for the admin.
  - `AdminDoctorPage.html`: Manage doctor details.
  - `AdminAppointmentPage.html`: View and manage appointments.
  - `AdminPatientPage.html`: Manage patient details.
  - `AdminUserPage.html`: Manage user accounts.
  
- **Scripts**:
  - **`adminHomePage.js`**: JavaScript file for handling the logic on the admin home page.
  - **`adminDoctorPage.js`**: Manages doctor-related functionalities such as adding or removing doctors.
  - **`adminAppointmentPage.js`**: Handles appointment management logic for the admin.
  - **`adminPatientPage.js`**: Manages patient details and records.
  - **`adminUserPage.js`**: Manages user accounts and permissions.

- **CSS**:
  - `adminStyles.css`: Styles specific to the admin pages.

### Doctor Directory

The `Doctor` directory contains pages and scripts for doctors to manage appointments, schedules, medicines, and tests.

- **HTML Files**:
  - `DoctorAppointmentView.html`: View and manage patient appointments.
  - `DoctorHomePage.html`: Dashboard for doctors.
  - `DoctorMedicine.html`: Manage medicines prescribed.
  - `DoctorSchedule.html`: Manage doctor schedules.
  - `DoctorTest.html`: Manage tests requested by doctors.

- **Scripts**:
  - **`doctorAppointmentView.js`**: Handles appointment viewing and management for doctors.
  - **`doctorHomePage.js`**: JavaScript logic for the doctor's dashboard.
  - **`doctorMedicine.js`**: Manages the medicine prescriptions and related logic.
  - **`doctorSchedule.js`**: Handles scheduling logic for doctors, including availability updates.
  - **`doctorTest.js`**: Manages the ordering and reviewing of tests.

- **CSS**:
  - `doctorStyles.css`: Styles specific to the doctor pages.

### User Directory

The `User` directory is for functionalities accessible by patients and hospital staff, including booking appointments and viewing patient information.

- **HTML Files**:
  - `AddPatient.html`: Add new patient information.
  - `BookPatient.html`: Book appointments for patients.
  - `UserHomePage.html`: Dashboard for users.
  - `ViewPatientAppointment.html`: View booked appointments.

- **Scripts**:
  - **`addPatient.js`**: Handles logic for adding new patients to the system.
  - **`bookPatient.js`**: Manages the booking of appointments for patients.
  - **`userHomePage.js`**: JavaScript file for user dashboard functionalities.
  - **`viewPatientAppointment.js`**: Handles the viewing of patient appointments.

- **CSS**:
  - `userStyles.css`: Styles specific to user pages.

### Login Directory

The `Login` directory contains the login page and demo for accessing the system.

- **HTML Files**:
  - `Login.html`: Login page for all users.
  - `Demo.html`: Demo page for testing purposes.

- **Scripts**:
  - **`script.js`**: Handles login logic, validation, and session management.

- **CSS**:
  - `styles.css`: General styles for the login page.

## Getting Started

To get started with the Hospital Management System:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/hospital-management-system.git



# Configuration
There are no special configuration steps required for frontend beyond placing the files in the correct directories. Ensure all dependencies (if any) are correctly referenced in your HTML and JavaScript files.


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

Instructions for executing the backend code: 
1. Change the database credentials & the database url & name according to your local machine.
2. Make sure to first set up the database before running the program.
3. Use Java 17 & the maven dependencies mentioned in the pom.xml as given to avoid any exceptions due to it.
4. The doctor.json file is attached along the code, use correct filepath while execution. Another json file can also be used as per your input.
5. Due to data being inserted at a previous date, some features which nead current & future dates might not give you any result. To avoid that, manipulate the data to store the current date of the appointments.


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
