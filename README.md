# hospital-management-system
## Introduction
This is a small hospital management API system, that manages doctors with their patients through appointments, 
and assign a prescription if needed

## Table of Contents
- [Introduction](#introduction)
- [UML](#UML)
- [APIs](#APIs)
- - [Doctor](#Doctor)
- - [Patient](#Patient)
- - [Appointment](#Appointment)
- - [Prescription](#Prescription)


## UML
The following png is UML Class Diagram of a small hospital system,
with a docter and patient classes that are connected through an appointment class,
and a prescreption class linked to to that appointment, with the ability to 
schedual another appointment if needed by the prescreption.
![alt text](./HMS-UML.png)

## APIs
As for how to use the API, the following are tables that describes the different http requests

## Doctor
| Http <br/>Method| URL Path                        | Http <br/>Status <br/>Code | Description                     | Sample Request                                                            | Sample Response                                                                                                                                                                                               |
|:----------------|:--------------------------------|:---------------------------|:--------------------------------|:--------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET             | /doctors                        | 200/<br/>404              | Get all <br/>doctors            | GET <br/>/doctors                                                         | json {<br/>"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                             |
| POST            | /doctors                        | 201/<br/>400              | Create a doctor                 | POST <br/>/doctors<br/>{<br/>"name":"john",<br/>"speciality":"eye"<br/>}  | {<br/>"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                  |
| GET             | /doctors/{id}                   | 200/<br/>404              | Get Doctor by id                | GET <br/>/doctors/1                                                       | {<br/>"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                  |
| DELETE          | /doctors/{id}                   | 200/<br/>404              | Delete doctor by id             | DELETE <br/>/doctors/1                                                    | Deleted successfully                                                                                                                                                                                          |
| GET             | /doctors/{id}/<br/>appointments | 200/<br/>404              | Get all appointments for doctor | GET <br/>/doctors/{id}/appointments                                       | [<br/>{<br/>"id":1,<br/>"date":"2023-07-07",<br/>"doctor":{<br/>"id":1,<br/>"name":"john",<br/>"speciality":"eye"<br/>},<br/>"patient":{<br/>"id":1,<br/>"name":"omar",<br/>"phonenum":1234<br/>}<br/>}<br/>] |
| PUT             | /doctors/{id}                   | 200/<br/>404              | Update doctor                   | PUT <br/>/doctors/{1}<br/>{<br/>"name":"john",<br/>"speciality":eye<br/>} | {<br/>"id":1,<br/>"name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                 |


## Patient
| Http <br/>Method | URL Path      | Http <br/>Status <br/>Code | Description           | Sample Request                                                           | Sample Response                                                  |
|:-----------------|:--------------|:---------------------------|:----------------------|:-------------------------------------------------------------------------|:-----------------------------------------------------------------|
| GET              | /patient      | 200/<br/>404              | Get all <br/>Patients | GET <br/>/Patients                                                       | json {<br/>"id":1,<br/>name":"john",<br/>"phoneNum":"1234"<br/>} |
| POST             | /patient      | 201/<br/>400              | Create a Patient      | POST <br/>/Patients<br/>{<br/>"name":"john",<br/>"phoneNum":"123"<br/>}  | {<br/>"id":1,<br/>name":"john",<br/>"phoneNum":"123"<br/>}       |
| GET              | /patient/{id} | 200/<br/>404              | Get Patient by id     | GET <br/>/Patients/1                                                     | {<br/>"id":1,<br/>name":"john",<br/>"phoneNum":"123"<br/>}       |
| DELETE           | /patient/{id} | 200/<br/>404              | Delete Patient by id  | DELETE <br/>/Patients/1                                                  | Deleted successfully                                             |
| PUT              | /patient/{id} | 200/<br/>404              | Update Patient        | PUT <br/>/Patients/{1}<br/>{<br/>"name":"john",<br/>"phoneNum":123<br/>} | {<br/>"id":1,<br/>"name":"john",<br/>"phoneNum":"123"<br/>}      |


## Appointment
| Http <br/>Method | URL Path          | Http <br/>Status <br/>Code | Description               | Sample Request                                                                                   | Sample Response                                                                  |
|:-----------------|:------------------|:---------------------------|:--------------------------|:-------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------|
| GET              | /Appointment      | 200/<br/>404               | Get all <br/>Appointments | GET <br/>/Appointments                                                                           | json {<br/>"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                |
| POST             | /Appointment      | 201/<br/>400              | Create a Appointment      | POST <br/>/Appointments<br/>{<br/>"date":"2023-01-03",<br/>"doctorId":1,<br/>"patientId":2<br/>} | {<br/>"id":1,<br/>"date":"2023-01-03",<br/>"doctorId":1,<br/>"patientId":2<br/>} |
| GET              | /Appointment/{id} | 200/<br/>404              | Get Appointment by id     | GET <br/>/Appointments/1                                                                         | {<br/>"id":1,<br/>"date":"2023-01-03",<br/>"doctorId":1,<br/>"patientId":2<br/>} |
| DELETE           | /Appointment/{id} | 200/<br/>404              | Delete Appointment by id  | DELETE <br/>/Appointments/1                                                                      | Deleted successfully                                                             |
| PUT              | /Appointment/{id} | 200/<br/>404              | Update Appointment        | PUT <br/>/Appointments/{1}{<br/>"date":"2023-01-03",<br/>"doctorId":2,<br/>"patientId":3<br/>}   | {<br/>"id":1,<br/>"date":"2023-01-03",<br/>"doctorId":2,<br/>"patientId":3<br/>} |


## Prescription
| Http <br/>Method | URL Path           | Http <br/>Status <br/>Code | Description                | Sample Request                                                                                          | Sample Response                                                                           |
|:-----------------|:-------------------|:---------------------------|:---------------------------|:--------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------|
| GET              | /Prescription      | 200/<br/>404              | Get all <br/>Prescriptions | GET <br/>/Prescription                                                                                  | json {<br/>"id":1,<br/>dosage":10,<br/>"medication":"trufin",<br/>"appointmentId":1<br/>} |
| POST             | /Prescription      | 201/<br/>400              | Create a Prescription      | POST <br/>/Prescription<br/>{<br/>"dosage":10,<br/>"medication":"trufin",<br/>"appointmentId":1<br/>}   | {<br/>"id":1,<br/>dosage":10,<br/>"medication":"trufin",<br/>"appointmentId":1<br/>}      |
| GET              | /Prescription/{id} | 200/<br/>404              | Get Prescription by id     | GET <br/>/Prescription/1                                                                                | {<br/>"id":1,<br/>dosage":10,<br/>"medication":"trufin",<br/>"appointmentId":1<br/>}      |
| DELETE           | /Prescription/{id} | 200/<br/>404              | Delete Prescription by id  | DELETE <br/>/Prescription/1                                                                             | Deleted successfully                                                                      |
| PUT              | /Prescription/{id} | 200/<br/>404              | Update Prescription        | PUT <br/>/Prescription/{1}<br/>{<br/>dosage":20,<br/>"medication":"acamol",<br/>"appointmentId":1<br/>} | {<br/>"id":1,<br/>dosage":20,<br/>"medication":"acamol",<br/>"appointmentId":1<br/>}      |


,you can also checkout the folder containing the full json api
[Postman](https://github.com/laithisbaitan/hospital-management-system/tree/main/API%20Examples%20as%20JSON%20useing%20postman "API Examples as JSON useing postman")
it has examples for each request for every entity