create database hospital;
use hospital;

CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    patientName VARCHAR(255) NOT NULL,
    patientGender VARCHAR(10),
    patientAge INT,
    medicalProblem VARCHAR(255),
    contactDetails VARCHAR(255),
    medicalTests TEXT, 
    medicines TEXT 
);

CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    doctorName VARCHAR(255) NOT NULL,
    doctorSpecialization VARCHAR(255),
    doctorEmail VARCHAR(255),
    doctorContactNumber VARCHAR(15)
);

CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY ,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    appointmentTime TIME,
    status VARCHAR(20) DEFAULT 'Scheduled'
);

CREATE TABLE Schedule (
    doctorId INT,
    startTime DATETIME,
    endTime DATETIME
);

ALTER TABLE Appointment ADD CONSTRAINT FOREIGN KEY (patientId) REFERENCES Patient(patientId);
ALTER TABLE Appointment ADD CONSTRAINT FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId);

ALTER TABLE Schedule ADD CONSTRAINT FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId);

INSERT INTO Patient (patientId, patientName, patientGender, patientAge, medicalProblem, contactDetails, medicalTests, medicines) VALUES
(1, 'Alice Smith', 'Female', 34, 'Acne', '7867340900', '', ''),
(2, 'Bob Johnson', 'Male', 45, 'Skin Rash', '1234567890', '', ''),
(3, 'Carol White', 'Female', 29, 'Cardiac Issues', '9090909090', '', ''),
(4, 'David Brown', 'Male', 52, 'Diabetes', '3245678912', '', ''),
(5, 'Eva Green', 'Female', 41, 'Flu', '3453453453', '', ''),
(6, 'Frank Miller', 'Male', 37, 'Acne', '8765432109', '', ''),
(7, 'Grace Lee', 'Female', 28, 'Skin Rash', '1010101010', '', '');

INSERT INTO Doctor (doctorId, doctorName, doctorSpecialization, doctorEmail, doctorContactNumber) VALUES
(101, 'Dr. Emma Davis', 'Dermatology', 'emma.davis@gamil.com', '1234567890'),
(102, 'Dr. Virat Kohli', 'Cardiology', 'virat.kohli@yaaho.com', '6543216565'),
(103, 'Dr. Laura Smith', 'Endocrinology', 'laura.smith@outloook.com', '1234567893'),
(104, 'Dr. Robert De Niro', 'General Physician', 'deniro.robert@hotloook.com', '3490908765')

INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, appointmentTime, status) VALUES
(1001, 1, 101, '2024-08-24', '10:00:00', 'Scheduled'), 
(1002, 2, 101, '2024-08-25', '11:00:00', 'Scheduled'),  
(1003, 3, 102, '2024-08-24', '09:30:00', 'Scheduled'),  
(1004, 4, 103, '2024-08-26', '14:00:00', 'Scheduled'),
(1005, 5, 104, '2024-08-26', '14:30:00', 'Scheduled'),
(1006, 6, 101, '2024-08-25', '13:00:00', 'Scheduled'),
(1007, 7, 101, '2024-08-26', '12:00:00', 'Scheduled');

INSERT INTO Schedule (doctorId, startTime, endTime) VALUES
(101, '2024-08-24 08:30:00', '2024-08-24 12:00:00'),
(101, '2024-08-25 08:30:00', '2024-08-25 12:00:00'),  
(101, '2024-08-25 12:00:00', '2024-08-25 15:30:00'),
(101, '2024-08-26 12:00:00', '2024-08-26 15:30:00'), 
(102, '2024-08-24 08:30:00', '2024-08-24 12:00:00'),
(102, '2024-08-24 12:00:00', '2024-08-24 15:30:00'),
(103, '2024-08-26 12:00:00', '2024-08-26 15:30:00'),
(103, '2024-08-26 15:30:00', '2024-08-26 18:00:00'),
(104, '2024-08-26 12:00:00', '2024-08-26 15:30:00'),
(104, '2024-08-26 15:30:00', '2024-08-26 18:00:00');

SELECT CONSTRAINT_NAME 
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE 
WHERE TABLE_NAME = 'Appointment' AND REFERENCED_TABLE_NAME = 'Doctor';

SELECT CONSTRAINT_NAME 
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE 
WHERE TABLE_NAME = 'Appointment' AND REFERENCED_TABLE_NAME = 'Patient';

SELECT CONSTRAINT_NAME 
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE 
WHERE TABLE_NAME = 'Schedule' AND REFERENCED_TABLE_NAME = 'Doctor';

ALTER TABLE Appointment DROP FOREIGN KEY appointment_ibfk_2;
ALTER TABLE Appointment DROP FOREIGN KEY appointment_ibfk_1;
ALTER TABLE Schedule DROP FOREIGN KEY schedule_ibfk_1;

ALTER TABLE Appointment
MODIFY appointmentId INT AUTO_INCREMENT;

