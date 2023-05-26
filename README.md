# microservices-store
This project is insipred from Spring Boot Microservices Full Course by Programming Techie.
Link to the course: https://www.youtube.com/watch?v=lh1oQHXVSc0&list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c

## Overview
Spring Boot project consists of products, orders and inventory microservises for orders processing. Product is used for Product REST operations, order is for placing orders and inventory to control products availability. To be able to place order it should have enough quantities of and if quantity reach zero product will be deleted.

## Technologies Used
This project was built using:
- Spring Boot
- Maven
- Spring Data JPA
- Eureka server
- Open Feign client
- MySQL
- Flyway migration

API Endpoints:
- Eureka serever: http://localhost:8761
- Orders: http://localhost:8081/api/v1/orders
- Inventory: http://localhost:8082/api/v1/inventories
- Products: http://localhost:8083/api/v1/products

## Thanks
Special thanks to DKichukov.