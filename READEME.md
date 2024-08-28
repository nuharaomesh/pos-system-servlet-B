
# POS System API Documentation

## Introduction
This API documentation provides details about the POS system's Service. The system manages items, customers, and orders.

## Base URL
```bash
http://localhost:8080/posSystem
```

## Authentication
Currently, no authentication mechanism is implemented.

### 1. Items API

#### 1.1 Add a New Item
- **Endpoint**: `/posSystem/item`
- **Method**: `POST`
- **Description**: Adds a new item to the inventory.

**Request**:
```json
{
  "id": "I001",
  "name": "Burger",
  "category": "Food",
  "price": 4.50,
  "qty": 100,
  "img": "example.img"
}
```

**Response**:
- Success: `HTTP 201 Created`
- Error: `HTTP 400 Bad Request`

#### 1.2 Update Item Quantity
- **Endpoint**: `/posSystem/updateItemQty`
- **Method**: `PUT`
- **Description**: Updates the quantity of an item.

**Request**:
```json
{
  "itemId": "item1",
  "qty": 50
}
```

**Response**:
- Success: `HTTP 200 OK`
- Error: `HTTP 404 Not Found`

#### 1.3 Get All Items
- **Endpoint**: `/posSystem/items`
- **Method**: `GET`
- **Description**: Retrieves all items in the inventory.

**Response**:
```json
[
  {
    "itemId": "item1",
    "name": "Burger",
    "category": "Food",
    "price": 4.50,
    "qty": 100,
    "img": "url_to_image"
  }
]
```

### 2. Customers API

#### 2.1 Add a New Customer
- **Endpoint**: `/posSystem/customer`
- **Method**: `POST`
- **Description**: Adds a new customer.

**Request**:
```json
{
  "id": "249183ae-25f4-451e-b618-8baab9212b30",
  "name": "Omesh Nuhara",
  "gender": "Male",
  "email": "omesh@example.com",
  "phone": "1234567890"
}
```

**Response**:
- Success: `HTTP 201 Created`
- Error: `HTTP 400 Bad Request`

#### 2.2 Get Customers
- **Endpoint**: `/posSystem/customer`
- **Method**: `GET`
- **Description**: Retrieves customer information by ID.

**Response**:
```json
[
  {
    "customerId": "249183ae-25f4-451e-b618-8baab9212b30",
    "name": "Omesh Nuhara",
    "gender": "Male",
    "email": "omesh@example.com",
    "phone": "1234567890"
  },
  {
    "customerId": "24912ae-25f4-451g-b418-2adfb9222b12",
    "name": "Liyanage",
    "gender": "Male",
    "email": "liyanage@example.com",
    "phone": "1234567890"
  }
]
```
- Error: `HTTP 404 Not Found`

### 3. Orders API

#### 3.1 Create an Order
- **Endpoint**: `/posSystem/order`
- **Method**: `POST`
- **Description**: Creates a new order.

**Request**:
```json
[

  [
    {
      "id": "I001",
      "qty": 8
    },
    {
      "id": "I002",
      "qty": 3
    }
  ]
,
  {
    "id": "O001",
    "price": 100.0,
    "time": "2024.10.1",
    "qty": 6,
    "cusID": "d7ed6917-0fde-4f61-84fe-49e59451c73c"
  },

  [
    {
      "orderID": "O001",
      "itemID": "I001",
      "price": 20.1,
      "count": 2
    },
    {
      "orderID": "O001",
      "itemID": "I002",
      "price": 10.1,
      "count": 3
    }
  ]

]
```

**Response**:
- Success: `HTTP 201 Created`
- Error: `HTTP 400 Bad Request`


## 4. Error Handling
- `HTTP 400 Bad Request`: Indicates invalid input or missing parameters.
- `HTTP 404 Not Found`: Indicates that the requested resource was not found.
- `HTTP 500 Internal Server Error`: Indicates a server-side issue.

## 5. Logging
Logging is used to track API requests, errors, and system operations. The logs are stored in a centralized location and can be used for debugging and monitoring purposes.

- Log Levels: `INFO`, `WARN`, `ERROR`
- Log Format: Standardized format including timestamps, request information, and error details.

## 6. Conclusion
This POS system API allows you to manage items, customers, and orders. For further details on how to use each endpoint, refer to the examples provided in this documentation.