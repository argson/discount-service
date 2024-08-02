# Discount service
REST API for calculating a given product's price based on the number of products ordered.


# Getting Started

To run this service you need only one command:

`gradle startDockerContainer`

after this gradle based on OpenAPI spec `src/main/resources/openapi/api.yaml` generate server API interfaces and necessary model, compile whole project and deploy container based on built docker image.

Container expose on port 8080 service http://localhost:8080/