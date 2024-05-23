# Online Bookstore API

-----
## Introduction
The api is programmed with Spring framework (Spring Boot) in Java, and connected to a PostgresSQL database for data persistence. 

The following behaviours are implemented: 
- User can login, logout and save user info.
- Users can browse and filter books by categories, authors, titles, ratings, price.
- Users can place order, view their order history and cancel their orders
- unit tests for the endpoints.

## How to use
The application is deployed in a docker container (see `docker-compose.yml`) and can be invoked by calling `. ./start.sh`

Then you can access it @`localhost:8050`. Authentication is required and you can provide dummy credential prepared:
```
  username: henry
  password: henry
```

The following demonstration uses this credential by default.

## API Usage

---------
### Login & Logout 

To login, `GET localhost:8050/login` and provide the credentials.

To logout, `GET localhost:8050/logout`

### View, save user info

To view user info, `GET localhost:8050/api/customers/{username}`

To save user info, `PUT localhost:8050/api/customers/{username}` and provide new user info in request body, e.g.
```json
{
  "firstName": "Peter",
  "lastName": "Ho",
//  ... other field to be updated
}
```

### Browse books & filter books

To browse books, `GET localhost:8050/api/books`

To view a book detail, provide book ID `GET localhost:8050/api/books/{orderId}`, 

or ISBN-13 `GET localhost:8050/api/books/isbn13/{isbn13}`

To filter books, provide request parameters like this: 

`GET localhost:8050/api/books/search?category=science&author=hawking&title=history of time&price_min=40&price_max=100&rating_above=3`


### Place, view, cancel order

To place an order, `POST localhost:8050/api/orders/{username}` and provide order details in request body like this:

```json
{
  "isbn13": 9780553380163,
  "amount": 1
}
```

To view customer's order history, `GET localhost:8050/api/orders/{username}`, 

or provide order ID `GET localhost:8050/api/orders/{username}/{orderId}` to view particular details

To cancel an order, `DELETE localhost:8050/api/orders/{username}/{orderId}`

-----

## Unit Test

Unit tests for endpoints (i.e. controllers) are stored under `src/test/**`.

To run test, call `./gradlew test`.

------
Henry Chan, 23/5/2024