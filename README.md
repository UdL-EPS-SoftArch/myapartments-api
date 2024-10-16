# MyApartments API

Spring Boot project including Spring REST, HATEOAS, JPA, etc. Additional details: [HELP.md](HELP.md)

[![Open Issues](https://img.shields.io/github/issues-raw/UdL-EPS-SoftArch/myapartments-api?logo=github)](https://github.com/orgs/UdL-EPS-SoftArch/projects/22)
[![CI/CD](https://github.com/UdL-EPS-SoftArch/myapartments-api/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/UdL-EPS-SoftArch/myapartments-api/actions)
[![CucumberReports: UdL-EPS-SoftArch](https://messages.cucumber.io/api/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0/badge)](https://reports.cucumber.io/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0)
[![Deployment status](https://img.shields.io/uptimerobot/status/m795321927-f749cb61c053b61e8da643c3)](https://myapartments-api.fly.dev)

## Vision

**For** ... **who** want to ...
**the project** ... **is an** ...
**that** allows ...
**Unlike** other ...

## Features per Stakeholder

| USER             | ADMIN                         | STUDENT              | OWNER                          |
|------------------|-------------------------------|----------------------|--------------------------------|
| Register Student | Delete advertisements         | List Advertisements  | Register Apartment (and rooms) |
| Register Owner   | Block user                    | Find Advertisement   | Publish Advertisement          |
| Login            | Delete User                   | Request Visit        | List Owned Apartments          |
| Logout           |                               | Filter advertisement | List Own Advertisements        |
| View profile     |                               | Cancel Visit         | Accept Visit                   |
| Edit profile     |                               | Review Advertisement | Reject Visit                   |
|                  |                               | View Visit Status    |                                |

## Entities Model

```mermaid
classDiagram

class UriEntity {
    String uri
}
UriEntity <|-- Property
UriEntity <|-- ApartmentDetails
UriEntity <|-- Advertisement
UriEntity <|-- Visit
UriEntity <|-- Review
UriEntity <|-- User

class UserDetails { <<interface>> }

class User {
    String username
    String password
    String email
}
UserDetails <|.. User
User <|-- Admin
User <|-- Student
User <|-- Owner

class Student {
    String phoneNumber
    String name
}

class Property {
    String description
}
Property <|-- Apartment
Property <|-- Room

class Apartment {
    String street
    String number
    String city
}
Owner "1" --> "*" Apartment : owner
Apartment "1" <-- "1" ApartmentDetails : details
Apartment "1" <-- "*" Room : in

class ApartmentDetails {
    Float square
    Integer numBathrooms
    Integer numBedrooms
    Boolean hasAC
    Boolean hasElevator
}
    
class Advertisement {
    String title
    String description
    Double price
}
Property "1" <-- "*" Advertisement : about

class Visit {
    ZonedDateTime when
}
Advertisement "1" <-- "*" Visit : for
Student "1" <-- "*" Visit : visitor

class Review {
    String title
    String description
    Double rating
}
Advertisement "1" <-- "*" Review : about

```
