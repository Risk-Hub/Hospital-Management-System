create database hospitalSystemDb;
use hospitalSystemDb;

CREATE TABLE staffLoginCredentials (
    loginId VARCHAR(35) PRIMARY KEY,
    userPassword VARCHAR(15),
    userType VARCHAR(15)
);
insert into staffLoginCredentials value ("testUser","UserTest123","User");

CREATE TABLE admin (
    adminId INT PRIMARY KEY,
    adminName VARCHAR(35),
    loginId VARCHAR(35)
);
alter table admin
add foreign key (loginId) references staffLoginCredentials(loginId);

CREATE TABLE user (
    userId INT PRIMARY KEY,
    userName VARCHAR(35),
    loginId VARCHAR(35)
);
alter table user add foreign key (loginId) references staffLoginCredentials(loginId);

CREATE TABLE doctor (
    doctorId INT PRIMARY KEY,
    doctorName VARCHAR(35),
    doctorContactNo LONG,
    doctorEmail VARCHAR(35),
    loginId VARCHAR(35)
);
alter table doctor add foreign key (loginId) references staffLoginCredentials(loginId);

CREATE TABLE patientDetails (
    patientId INT PRIMARY KEY,
    patientName VARCHAR(35),
    patientContactNo LONG,
    patientAge INT,
    patientGender VARCHAR(35),
    diseaseName VARCHAR(35),
    doctorId INT
);
alter table patientDetails add foreign key (doctorId) references doctor(doctorId);

CREATE TABLE appointments (
    doctorId INT,
    appointmentDate DATE,
    timeslot VARCHAR(35),
    isAvailable BOOLEAN,
    patientId INT
);
alter table appointments add foreign key (doctorId) references doctor(doctorId);
alter table appointments add foreign key (patientId) references patientDetails(patientId);
desc appointments;

CREATE TABLE diseases (
    diseaseName VARCHAR(100) PRIMARY KEY
);
CREATE TABLE medicinesNeeded (
    diseaseName VARCHAR(100),
    medicineName VARCHAR(100)
);
alter table medicinesNeeded add foreign key (diseaseName) references diseases(diseaseName);
CREATE TABLE testsNeeded (
    diseaseName VARCHAR(100),
    testsName VARCHAR(100)
);
alter table testsNeeded add foreign key (diseaseName) references diseases(diseaseName);

INSERT INTO staffLoginCredentials (loginId, userPassword, userType) VALUES 
("admin01", "Admin12345", "Admin"),
("user01", "User12345", "User"),
("doc01", "Doc12345", "Doctor"),
("doc02", "Doc12346", "Doctor"),
("doc03", "Doc12347", "Doctor");

-- Inserting dummy data into admin
INSERT INTO admin (adminId, adminName, loginId) VALUES
(1, "Admin One", "admin01");

-- Inserting dummy data into user
INSERT INTO user (userId, userName, loginId) VALUES
(1, "User One", "user01");

-- Inserting dummy data into doctor
INSERT INTO doctor (doctorId, doctorName, doctorContactNo, doctorEmail, loginId) VALUES
(1, "Dr. John Doe", 9876543210, "john.doe@example.com", "doc01"),
(2, "Dr. Jane Smith", 8765432109, "jane.smith@example.com", "doc02"),
(3, "Dr. Alice Johnson", 7654321098, "alice.johnson@example.com", "doc03");


-- Inserting dummy data into diseases
INSERT INTO diseases (diseaseName) VALUES
('Hypertension'),
('Diabetes'),
('Asthma');

-- Inserting dummy data into patientDetails
INSERT INTO patientDetails (patientId, patientName, patientContactNo, patientAge, patientGender, diseaseName, doctorId) VALUES
(1, "Patient One", 9876543211, 45, "Male", "Hypertension", 1),
(2, "Patient Two", 8765432110, 30, "Female", "Diabetes", 2),
(3, "Patient Three", 7654321109, 40, "Female", "Asthma", 3);

-- Inserting dummy data into appointments
INSERT INTO appointments (doctorId, appointmentDate, timeslot, isAvailable, patientId) VALUES
(1, '2024-08-18', '10:00-10:30 AM', FALSE, 1),
(2, '2024-08-19', '11:00-11:30 AM', FALSE, 2),
(3, '2024-08-20', '12:00-12:30 PM', FALSE, 3),
(1, '2024-08-21', '09:00-09:30 AM', TRUE, NULL), -- Available slot with no patient
(2, '2024-08-22', '01:00-01:30 PM', TRUE, NULL); -- Available slot with no patient

-- Inserting dummy data into medicinesNeeded
INSERT INTO medicinesNeeded (diseaseName, medicineName) VALUES
('Hypertension', 'Amlodipine'),
('Diabetes', 'Metformin'),
('Asthma', 'Albuterol');

-- Inserting dummy data into testsNeeded
INSERT INTO testsNeeded (diseaseName, testsName) VALUES
('Hypertension', 'Blood Pressure Test'),
('Diabetes', 'HbA1c Test'),
('Asthma', 'Spirometry Test');

