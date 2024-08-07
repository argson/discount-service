# Discount WebService
REST API for calculating a given product's price based on the number of products ordered.
- Service is a servlet based Spring-Boot application with SwaggerUI
- To monitor state of service enabled actuator

# Getting Started

To run this service you need only one command:

`gradle startDockerContainer`

after this comman gradle based on OpenAPI spec `openapi/api.yaml` generate server API interfaces and necessary model, compile whole project and deploy docker container based on built docker image.

Container expose on port 8080 service http://localhost:8080/

# Requirements:

- Java  17
- Docker

# Assumptions

- Max parallel client number should not exceed 200 for more clients we should consider change architecture on reactive webservice
- Service persist state in H2 database and after reboot lose state. If will be necessary change not lose state should change DBMS

# Configuration

- After start service init database with default configuration:
  - Two Policies:
    ```
    AMOUNT_BASE
    PERCENTAGE_BASE
    ```
  - Products
    ```
       id: 43bcc57b-4538-411f-8f4c-6af4cf046786	name: Product1	price: 100 PLN
       id: feea4b6c-cf3d-4dfc-ae41-49dd9e6b3d5d	name: Product2	price: 50 PLN
    ```
  - DiscountLevels
    ```
       productCountThreshold: 10	parameters: PRICE:2.0 | DENOMINATION:PLN	policy: AMOUNT_BASED
       productCountThreshold: 100	parameters: PRICE:5.0 | DENOMINATION:PLN	policy: AMOUNT_BASED
       productCountThreshold: 200	parameters: PRICE:10.0 | DENOMINATION:PLN	policy: AMOUNT_BASED
       productCountThreshold: 10	parameters: PERCENTAGE:5	policy: PERCENTAGE_BASED
       productCountThreshold: 50	parameters: PERCENTAGE:7	policy: PERCENTAGE_BASED
       productCountThreshold: 100	parameters: PERCENTAGE:10	policy: PERCENTAGE_BASED 
      ```
  - On this step of implementation configuration can be change only from code in class: `pl/inpost/api/config/AppInit.java`
    - endpoints `POST /policy/discountLevel` and `DELETE /policy/discountLevel` to change and delete these configurations are defined but not implemented yet
  