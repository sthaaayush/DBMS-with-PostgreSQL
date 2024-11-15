# PostgreSQL JDBC CRUD Operations

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
  - [Download Links](#download-links)
- [Implementation](#implementation)

## Introduction

This repository demonstrates how to perform CRUD (Create, Read, Update, Delete) operations using PostgreSQL with JDBC in Java. It includes connection management and a simple student management system.

## Setup

To set up the project, follow these steps:

1.  **Install PostgreSQL**:

    - Download and install PostgreSQL from the [official website](https://www.postgresql.org/download/).
    - During installation, note the username and password you set up.

2.  **Download JDBC Driver**:

    - Download the PostgreSQL JDBC driver from [PostgreSQL JDBC](https://jdbc.postgresql.org/download/postgresql-42.7.4.jar) for Java 8.

3.  **Add JDBC Driver to Your Project**:

    - **Using Eclipse**:
      1. Open your project in Eclipse.
      2. Right-click on the project in the Project Explorer.
      3. Select **Build Path** > **Configure Build Path**.
      4. In the "Java Build Path" window, go to the **Libraries** tab.
      5. Click on **Add External JARs...**.
      6. Navigate to the location where you downloaded the PostgreSQL JDBC driver (e.g., `postgresql-<version>.jar`).
      7. Select the JAR file and click **Open**.
      8. Click **Apply and Close** to save the changes.

4.  **Create a Database and Table**:

    - Create a database and the necessary tables in PostgreSQL. Use the following SQL commands to create a databse and tables:
      ```sql
      CREATE DATABASE testdb; 
      ```
      
      ```sql
      CREATE TABLE students (
         id SERIAL PRIMARY KEY,
         name VARCHAR(100),
         age INT,
         email VARCHAR(100)
      );
      ```
      ```sql
      CREATE TABLE accounts (
         account_id SERIAL PRIMARY KEY,
         account_holder VARCHAR(50) NOT NULL,
         balance NUMERIC(10, 2) DEFAULT 0.00
      );
      ```

      ```sql
      CREATE TABLE employees (
          id INT PRIMARY KEY,
          name VARCHAR(50) NOT NULL,
          department VARCHAR(50),
          salary DECIMAL(10, 2),
          isActive BOOLEAN DEFAULT TRUE
      );
      ```

5.  **Configure Database Connection**:
    - Update your database connection details in the `DatabaseConnection` class.

## Implementation

## Implementation

### 1. [Database Connection](src/DatabaseConnection.java)

Provides methods to establish and close a connection to the PostgreSQL database. The `getConnection()` method opens a new connection, and `closeConnection()` ensures proper resource management by closing it when operations are complete.

### 2. [CRUD Operations](src/CRUDExample.java)

Contains methods to perform all four CRUD (Create, Read, Update, Delete) operations on the `students` table. These operations enable data manipulation for account records within the database.

### 3. [Transaction Example](src/TransactionExample.java)

Implements transaction management to perform multiple updates within a single transaction. This example transfers funds between accounts by updating balances and ensures data consistency by either committing all changes or rolling them back in case of an error.

### 4. [Batch Processing](src/CRUDExample.java)
Implements batch processing for efficient bulk insertion of data into the `students` table. The method `insertStudentsBatch` inserts multiple student records in one go by queuing SQL statements and executing them as a single transaction, reducing database round trips.

### 4. [Stored & Callable Statement](src/StoredStatement.java)
Demonstrates the use of stored statements to retrieve data from a PostgreSQL view. This example uses a callable statement to execute a pre-defined query that fetches active employees’ details (ID, name, department, salary) from the `activeemployeedetails` view, simplifying data retrieval and improving reusability.


### Notes

- Ensure PostgreSQL is running before executing the Java application.
- The example includes basic error handling; consider enhancing it for production use.
