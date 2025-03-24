# Prerequisites

In order to be able to submit your assignement, you should have the following installed:
* JDK >= 17
* Maven
* Postman / any other tool that allows you to hit the application's endpoints
* Any versioning tool
* Any IDE that allows you to run the application


# How to

#### Run the application
The application should be run as a SpringBootApplication. Below is a quick guide on how to do that via IntelliJ:
* Edit Configuration 
   * Add New Configuration (Spring Boot)
     * Change the **Main class** to **ing.assessment.INGAssessment**
       * Run the app.

#### Connect to the H2 database
Access the following url: **http://localhost:8080/h2-console/**
 * **Driver Class**: _**org.h2.Driver**_
 * **JDBC URL**: _**jdbc:h2:mem:testdb**_
 * **User Name**: _**sa**_
 * **Password**: **_leave empty_**

# API Documentation
## Product Controller
This controller is responsible for retrieving existing products that are already initialized using a script. 
* **http://localhost:8080/products** - used to retrieve all products
* **http://localhost:8080/products/{id}** - used to retrieve a certain product by id. Will return empty if no product is found

## Order Controller
This controller is responsible for the management of orders. It includes operations such as:
* **POST http://localhost:8080/order** - creates an order given a request dto. This API checks if the requested products exist and if the requested stock is sufficient. If these validations pass, the order will be created and the product stock will be updated accordingly. The returned order will contain general, delivery costs and delivery time information. Request example: ```{
  "orderProducts": [
  {
  "productId": 1,
  "quantity": 2
  },
  {
  "productId": 2,
  "quantity": 2
  }
  ]
  }``` 
* **GET http://localhost:8080/order** - retrieves all existing orders from the system

