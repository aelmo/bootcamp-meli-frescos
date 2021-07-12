# Bootcamp - Meli Frescos App

## Description

This project was developed in Java with Spring Framework and MySQL, to offer fresh products in the marketplace.

To use this API you must clone this repository. You must have mysql service running and set a database user and password in the application.properties file. When started, the database will be automatically populated. There is a postman collection in the resources path the can be used to test the endpoints.


## Docs

- [Postman Collection](docs/postman/MELI%20-%20Frescos.postman_collection.json)

- [Individual requirement (US06)](docs/specs/RequirementUS06.pdf)

## Auth

The endpoints can only be accessed with authenticated users (agent or buyer, according the requirement):
#### Agent
* username: agentuser
* password: 12345678
#### Buyer
* username: buyeruser
* password: 12345678