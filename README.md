# Employee API Documentation

## Base URL
        http://localhost:8080

---

## Endpoints

### 1. Get All Employees (Paginated)

**Request**

    GET /employee
**Response**
```json
{
  "totalPages": 1,
  "employees": [
    {
      "id": 1,
      "name": "AdminManager",
      "salary": 75000.0,
      "address": "123 Main Street, Kochi",
      "role": "Admin",
      "joinDate": "2022-04-15",
      "dateOfBirth": "1990-06-10",
      "yearlyBonusPercentage": 10.5,
      "departmentId": null,
      "reportingManagerId": null
    },
    {
      "id": 2,
      "name": "Manager IT",
      "salary": 85000.0,
      "address": "101 Tech Park, Kochi",
      "role": "Manager",
      "joinDate": "2021-01-10",
      "dateOfBirth": "1980-03-25",
      "yearlyBonusPercentage": 12.0,
      "departmentId": 1,
      "reportingManagerId": 1
    }
  ],
  "currentPage": 0
}
```
### 1.  Get All Employees (Lookup Only) (Paginated)

**Request**
    
    GET /employee?lookup=true
**Response**
```json
{
  "totalPages": 1,
  "employees": [
    {
      "id": 1,
      "name": "AdminManager"
    },
    {
      "id": 2,
      "name": "Manager IT"
    }
  ],
  "currentPage": 0
}
```
### 1. Get Employee By ID
**Request**

    GET /employee/{id}
**Response**
```json
{
    "id": 1,
    "name": "AdminManager",
    "salary": 75000.0,
    "address": "123 Main Street, Kochi",
    "role": "Admin",
    "joinDate": "2022-04-15",
    "dateOfBirth": "1990-06-10",
    "yearlyBonusPercentage": 10.5,
    "departmentId": null,
    "reportingManagerId": null
}
```
### 1. Create New Employee
**Request**

    POST /employee
**Request Body**
```json
{
  "name": "Ashil",
  "salary": 44000.0,
  "address": "203 HM, Kochi",
  "role": "Employee",
  "joinDate": "2023-04-15",
  "dateOfBirth": "1994-05-10",
  "yearlyBonusPercentage": 5.2,
  "departmentId": 3,
  "reportingManagerId": 4
}
```
**Response**
```json
{
  "id": 29,
  "name": "Ashil",
  "salary": 44000.0,
  "address": "203 HM, Kochi",
  "role": "Employee",
  "joinDate": "2023-04-15",
  "dateOfBirth": "1994-05-10",
  "yearlyBonusPercentage": 5.2,
  "departmentId": 3,
  "reportingManagerId": 4
}
```
### 1. Update Employee by ID
**Request**

    PUT /employee/{id}
**Request Body**
```json
{
  "name": "Ashil B",
  "salary": 75000.0,
  "address": "123 Main Street, Kochi",
  "role": "employee",
  "joinDate": "2021-04-15",
  "dateOfBirth": "1992-06-10",
  "yearlyBonusPercentage": 10.5
}
```
**Response**
```json
{
  "id": 29,
  "name": "Ashil B",
  "salary": 75000.0,
  "address": "123 Main Street, Kochi",
  "role": "employee",
  "joinDate": "2021-04-15",
  "dateOfBirth": "1992-06-10",
  "yearlyBonusPercentage": 10.5,
  "departmentId": 3,
  "reportingManagerId": 4
}
```
### 1.  Update Employee Department
**Request**

    PUT /employee/{id}/department
**Request Body**
```json
{
  "departmentId": 4
}
```
**Response**
```json
{
  "id": 29,
  "name": "Ashil B",
  "salary": 75000.0,
  "address": "123 Main Street, Kochi",
  "role": "employee",
  "joinDate": "2021-04-15",
  "dateOfBirth": "1992-06-10",
  "yearlyBonusPercentage": 10.5,
  "departmentId": 3,
  "reportingManagerId": 4
}
```
### 1.  Delete Employee by ID
**Request**

    DELETE /employee/{id}
**Response**
```json
{
  "message": "Employee deleted successfully"
}

```