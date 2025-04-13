# Employee Management System 
## Employee API Documentation

### Base URL
        http://localhost:8080
---
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
# Department API Documentation 

### 1.  Get All Department (Paginated)
**Request**

    GET /department/
**Response**
```json
{
  "totalPages": 1,
  "departments": [
    {
      "id": 1,
      "name": "IT",
      "creationDate": "2024-06-01",
      "departmentHeadId": 2,
      "employeeCount": 4
    },
    {
      "id": 2,
      "name": "HR",
      "creationDate": "2024-06-05",
      "departmentHeadId": 3,
      "employeeCount": 6
    },
    {
      "id": 3,
      "name": "Finance",
      "creationDate": "2024-06-10",
      "departmentHeadId": 4,
      "employeeCount": 7
    },
    {
      "id": 4,
      "name": "Marketing",
      "creationDate": "2024-06-15",
      "departmentHeadId": 5,
      "employeeCount": 7
    }
  ],
  "currentPage": 0
}
```
### 1.  Get Department By Id And Employee List of that Department
**Request**

    GET /department/1/expand=employee
**Response**
```json
{
  "id": 1,
  "name": "IT",
  "creationDate": "2024-06-01",
  "departmentHeadId": 2,
  "employees": [
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
    },
    {
      "id": 6,
      "name": "Arjun P",
      "salary": 42000.0,
      "address": "201 Infopark, Kochi",
      "role": "Employee",
      "joinDate": "2023-02-10",
      "dateOfBirth": "1995-01-15",
      "yearlyBonusPercentage": 5.0,
      "departmentId": 1,
      "reportingManagerId": 2
    },
    {
      "id": 7,
      "name": "Sneha R",
      "salary": 43000.0,
      "address": "202 City Lane, Kochi",
      "role": "Employee",
      "joinDate": "2022-11-20",
      "dateOfBirth": "1996-08-25",
      "yearlyBonusPercentage": 4.7,
      "departmentId": 1,
      "reportingManagerId": 2
    },
    {
      "id": 8,
      "name": "Vishnu D",
      "salary": 44000.0,
      "address": "203 IT Plaza, Kochi",
      "role": "Employee",
      "joinDate": "2023-04-15",
      "dateOfBirth": "1994-05-10",
      "yearlyBonusPercentage": 5.2,
      "departmentId": 1,
      "reportingManagerId": 2
    }
  ],
  "employeeCount": 4
}
```
### 1.  Get Department By Id
**Request**

    GET /department/{id}
**Response**
```json
{
  "id": 1,
  "name": "IT",
  "creationDate": "2024-06-01",
  "departmentHeadId": 2,
  "employeeCount": 7
}
```

### 1.  Create New Department
**Request**

    POST localhost:8080/department
**Request Body**
```json
{
  "name": "UI/UX",
  "creationDate": "2024-06-01",
  "departmentHeadId": null
}
```
**Response**
```json 
{
  "id": 5,
  "name": "UI/UX",
  "creationDate": "2024-06-01",
  "departmentHeadId": null,
  "employeeCount": 0
}
```
### 1.  Update Department By Id
**Request**

    PUT localhost:8080/department/{id}
**Request Body**
```json
{
  "name": "UI/UX Design",
  "creationDate": "2024-06-01",
  "departmentHeadId": null
}
```
**Response** 
```json 
{
  "id": 5,
  "name": "UI/UX Design",
  "creationDate": "2024-06-01",
  "departmentHeadId": null,
  "employeeCount": 0
}
```
### 1.  Delete Department By Id
**Request**

    DELETE /department/{id}
**Response**
```json
{
  "message": "Department deleted successfully."
}
```





