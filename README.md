# Reading Is Good
Online Book Retail Backend Service

### Technologies

- Spring Boot 2.6.1
- Spring Integration
- Spring Security
- PostgreSQL
- Open API
  
### Installation

1. Open the project in the IDE.

3. Execute Maven clean & install

4. Start the Spring Boot Application

### Docker Installation

- docker-compose
```sh
 docker-compose up -d
```

### Quick Start
- You can run the ``` startup.bat ```  to launch the application.

### Api Endpoints


```sh
View detailed sample requests, responses and models via  http://localhost:8081/swagger-ui.html
```


Method | Url | Description |
| ------ | --- | ----------- |
| POST   |/api/authenticate| Create Authentication Token
| POST   |/api/book/create | Create New Book |
| PUT   | /api/book/update/{id} | Update Book By Id  |
| GET   | /api/statistics/montlyorderstatistics | Querying Monthly Orders Staticstics |
| POST   | /api/customer/create| Create New Customer |
| GET   | /api/customer/all| Querying All Customers By Using Paging |
| POST   | /api/customer/api/order/create| Create New Order |
| GET   | /api/order/listordersbydateinterval| List Order By Time Interval |
| GET   | /api/order/{id}| Get Order By Order Id |

### Authentication Credential
```sh
username : getir
password : getir
```


### Postman
```sh
https://www.getpostman.com/collections/59b1a43e5d4afd5d5fe4
```