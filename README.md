# Employee Management System 

## Swagger-ui 
        
        http://localhost:8080/swagger-ui/index.html

### Base URL
        http://localhost:8080

## Employee API Endpoints

---

### 1. Get All Employees (Paginated)
**Request**

    GET /employee

    GET /employee?page=0
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
### 2.  Get All Employees (Lookup Only) (Paginated)

**Request**
    
    GET /employee?lookup=true

    GET /employee?lookup=true&page=0
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
### 3. Get Employee By ID
**Request**

    GET /employee/1
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
### 4. Create New Employee
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
### 5. Update Employee by ID
**Request**

    PUT /employee/29
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
### 6.  Update Employee Department
**Request**

    PATCH /employee/{id}/department
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
### 7.  Delete Employee by ID
**Request**

    DELETE /employee/29
**Response**
```json
{
  "message": "Employee deleted successfully"
}
```
----

## Department API Endpoints

----
### 8.  Get All Department (Paginated)
**Request**

    GET /department/

    GET /department?page=0
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
### 9.  Get Department By Id
**Request**

    GET /department/1
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
### 10.  Get Department By Id And Employee List of that Department
**Request**

    GET /department/1?expand=employee
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
### 11.  Create New Department
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
### 12.  Update Department By Id
**Request**

    PUT localhost:8080/department/5
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
### 13.  Delete Department By Id
**Request**

    DELETE /department/5
**Response**
```json
{
  "message": "Department deleted successfully."
}
```
----

## Dashboard API Endpoints

----

``
### 14.  Get Counts
**Request**

    GET /dashboard/count
**Response**
```json
{
  "totalEmployees": 28,
  "totalDepartments": 5
}
```
### 15.  Get Role Employee Count
**Request**

    GET dashboard/role_chart
**Response**
```json
[
  {
    "role": "Admin",
    "count": 1
  },
  {
    "role": "Manager",
    "count": 4
  },
  {
    "role": "Employee",
    "count": 23
  }
]
```

### 16.  Get Department Employee Count
**Request**

    GET /dashboard/department_chart
**Response**
```json
[
  {
    "departmentName": "IT",
    "employeeCount": 7
  },
  {
    "departmentName": "HR",
    "employeeCount": 6
  },
  {
    "departmentName": "Finance",
    "employeeCount": 7
  },
  {
    "departmentName": "Marketing",
    "employeeCount": 7
  },
  {
    "departmentName": "UI/UX",
    "employeeCount": 0
  }
]
```
----

## Reporting-chains API Endpoints

----

### 17.  Get By role
**Request**

    GET /reporting-chains/by_role
**Response**
```json
[
  {
    "role": "Manager",
    "reportsTo": "Admin"
  },
  {
    "role": "Employee",
    "reportsTo": "Manager"
  }
]
```

### 18.  Get By Id
**Request**

    GET /reporting-chains/12
**Response**
```json
{
  "employeeId": 12,
  "employeeName": "Ravi N",
  "reportingChain": [
    "Manager HR",
    "AdminManager"
  ]
}
```




