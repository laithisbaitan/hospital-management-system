# hospital-management-system
- [Introduction](#introduction)
This is a small hospital management API system, that manages doctors with their patients through appointments, 
and assign a prescription if needed

## Table of Contents

- [UML](#UML)
The following png is UML Class Diagram of a small hospital system,
with a docter and patient classes that are connected through an appointment class,
and a prescreption class linked to to that appointment, with the ability to 
schedual another appointment if needed by the prescreption.
![alt text](./HMS-UML.png)

- [APIs](#APIs)
- [Doctor](#Doctor)
As for how to use the API, the following are tables that describes the different http requests

| Http <br/>Method | URL Path                   | Http <br/>Status <br/>Code | Description                     | Sample Request                                                      | Sample Response                                                                                                                                                                                               |
|:-----------------|:---------------------------|:---------------------------|:--------------------------------|:--------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET              | /doctors                   | 200 /<br/>404              | Get all <br/>doctors            | GET /doctors                                                        | json {"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                  |
| POST             | /doctors                   | 201 /<br/>400              | Create a doctor                 | POST /doctors<br/>{<br/>"name":"john",<br/>"speciality":"eye"<br/>} | {"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                       |
| GET              | /doctors/{id}              | 200 /<br/>404              | Get Doctor by id                | GET /doctors/1                                                      | {"id":1,<br/>name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                       |
| DELETE           | /doctors/{id}              | 200 /<br/>404              | Delete doctor by id             | DELETE /doctors/1                                                   | Deleted successfully                                                                                                                                                                                          |
| GET              | /doctors/{id}/appointments | 200 /<br/>404              | Get all appointments for doctor | GET /doctors/{id}/appointments                                      | [<br/>{<br/>"id":1,<br/>"date":"2023-07-07",<br/>"doctor":{<br/>"id":1,<br/>"name":"john",<br/>"speciality":"eye"<br/>},<br/>"patient":{<br/>"id":1,<br/>"name":"omar",<br/>"phonenum":1234<br/>}<br/>}<br/>] |
| PUT              | /doctors/{id}              | 200 /<br/>404              | Update doctor                   | PUT /doctors/{id}<br/>{<br/>"name":"john",<br/>"speciality"<br/>}   | {<br/>"id":1,<br/>"name":"john",<br/>"speciality":"eye"<br/>}                                                                                                                                                      |




,you can also checkout the folder containing the full json api
[Postman](https://github.com/laithisbaitan/hospital-management-system/tree/main/API%20Examples%20as%20JSON%20useing%20postman "API Examples as JSON useing postman")
it has examples for each request for every entity