# JobApp
A simple backend application for managing job-related data, built with Spring Boot.

## Features
* CRUD Operations: Manage Companies, Jobs, and Reviews
* Entity Relationships:
  * A Company can have multiple Jobs and Reviews
  * Jobs and Reviews are associated with a Company
* RESTful Endpoints:
  * Retrieve Jobs by Job ID
  * Retrieve Reviews by Company ID
  * Retrieve Company details

## Technologies Used
* Java
* Spring Boot
* PostgreSQL
* Maven

## Getting Started
### Prerequisites
* Java Development Kit (JDK) 8 or higher
* Maven
* PostgreSQL

### Installation
1. Clone the repository:
  ```
  git clone https://github.com/brlivsky/job-app.git
  ```
2. Navigate to the project directory:
  ```
  cd job-app
  ```
3. Build the project:
  ```
  mvn clean install
  ```
4. Run the application:
  ```
  mvn spring-boot:run

  ```
## API Endpoints
- Companies:
  - `GET /companies`: Retrieve all companies
  - `GET /companies/{id}`: Retrieve a company by ID
  - `POST /companies`: Create a new company
  - `PUT /companies/{id}`: Update an existing company
  - `DELETE /companies/{id}`: Delete a company

- Jobs:
  - `GET /jobs`: Retrieve all jobs
  - `GET /jobs/{id}`: Retrieve a job by ID
  - `POST /jobs`: Create a new job
  - `PUT /jobs/{id}`: Update an existing job
  - `DELETE /jobs/{id}`: Delete a job

- Reviews:
  - `GET /companies/{companyId}/reviews`: Retrieve all reviews of a company
  - `GET /companies/{companyId}/reviews/{reviewId}`: Retrieve a review of a company
  - `POST /companies/{companyId}/reviews`: Create a new review for a company
  - `PUT /companies/{companyId}/reviews/{reviewId}`: Update an existing review of a company
  - `DELETE /companies/{companyId}/reviews/{reviewId}`: Delete a review of a company

## Project Structure
```
src
├───main
    ├───java
    │   └───com
    │       └───embarkx
    │           └───jobapp
    │               │   JobappApplication.java
    │               ├───company
    │               │   │   Company.java
    │               │   │   CompanyController.java
    │               │   │   CompanyRepository.java
    │               │   │   CompanyService.java
    │               │   └───impl
    │               │           CompanyServiceImpl.java     
    │               ├───job
    │               │   │   Job.java
    │               │   │   JobController.java
    │               │   │   JobRepository.java
    │               │   │   JobService.java 
    │               │   └───impl
    │               │           JobServiceImpl.java     
    │               └───review
    │                   │   Review.java
    │                   │   ReviewController.java
    │                   │   ReviewRepository.java
    │                   │   ReviewService.java
    │                   └───impl
    │                           ReviewServiceImpl.java                      
    └───resources
        │   application.properties

```

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License.
