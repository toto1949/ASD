CREATE DATABASE IF NOT EXISTS ADSDentalSurgery;
USE ADSDentalSurgery;

CREATE TABLE Dentist (
    dentist_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    specialization VARCHAR(100)
);


CREATE TABLE Patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    dob DATE,
    has_unpaid_bill BOOLEAN DEFAULT FALSE
);

CREATE TABLE Surgery (
    surgery_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address TEXT,
    phone VARCHAR(20)
);

CREATE TABLE Appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    time TIME,
    dentist_id INT,
    patient_id INT,
    surgery_id INT,
    status VARCHAR(50),
    FOREIGN KEY (dentist_id) REFERENCES Dentist(dentist_id),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (surgery_id) REFERENCES Surgery(surgery_id)
);

INSERT INTO Dentist (first_name, last_name, phone, email, specialization)
VALUES 
('John', 'Smith', '111-222-3333', 'john.smith@example.com', 'Orthodontist'),
('Jane', 'Doe', '222-333-4444', 'jane.doe@example.com', 'Pediatric');


INSERT INTO Patient (first_name, last_name, phone, email, address, dob, has_unpaid_bill)
VALUES 
('Alice', 'Johnson', '555-666-7777', 'alice.j@example.com', '123 Maple St', '1990-05-10', FALSE),
('Bob', 'Brown', '888-999-0000', 'bob.b@example.com', '456 Oak St', '1985-08-20', TRUE);


INSERT INTO Surgery (name, address, phone)
VALUES 
('Downtown Surgery', '789 Main St', '333-444-5555'),
('Westend Surgery', '321 Side St', '444-555-6666');


INSERT INTO Appointment (date, time, dentist_id, patient_id, surgery_id, status)
VALUES 
('2025-04-08', '09:00:00', 1, 1, 1, 'booked'),
('2025-04-09', '11:00:00', 1, 2, 1, 'booked'),
('2025-04-10', '14:00:00', 2, 1, 2, 'cancelled');

-- All Dentists
SELECT * FROM Dentist ORDER BY last_name ASC;

-- Appointments for a given Dentist
SELECT A.*, P.first_name, P.last_name, P.email
FROM Appointment A
JOIN Patient P ON A.patient_id = P.patient_id
WHERE A.dentist_id = 1;

-- Appointments scheduled at a Surgery
SELECT A.*, S.name AS surgery_name
FROM Appointment A
JOIN Surgery S ON A.surgery_id = S.surgery_id;

-- Appointments for a given Patient on a given Date
SELECT * FROM Appointment
WHERE patient_id = 1 AND date = '2025-04-08';


