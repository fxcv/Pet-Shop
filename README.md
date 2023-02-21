# Pet-Shop
A secured backend application for a web pet shop.  
# Techstack:
  - Spring  
  - Spring Data JPA  
  - Spring Security  
  - MySQL  
  - JUnit  
# Endpoints:
  Secured endpoints requiring role ADMIN to access them:  
    - /pets/all fetches all the pets from the database and returns a list of their  
    - /pets/get fetches a specific pet by it's id and returns it's DTO  
    - /pets/add adds a pet to the database, DTO in request's body required  
