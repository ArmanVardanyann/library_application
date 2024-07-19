# Library Application

## Overview

The Library Application is an online platform that allows users to browse, purchase, and get intelligent suggestions for books based on their preferences. It features role-based access with three main roles: Super-Admin, Admin, and User. The application follows the Hexagonal Architecture, ensuring a clear separation between the core logic and external systems.

## Features

User Management: Sign up, sign in, and role-based access control.
Book Management: Browse and purchase books.
Sales Reports: Generate reports to check the number of books sold between specific dates.
Intelligent Book Suggestions: Suggest books to users based on their purchasing history and preferences.

# Technologies Used
- Java

- Spring Boot

- Spring Data JPA

- Spring Security

- Hibernate

- MySQL

- JUnit

- Gradle

# API Endpoints

## Sign-Up/Sign-In Endpoints

- Sign-Up for new users : POST /auth/sign-up
- Sign-In for users : POST /auth/sign-in
- Suggest books based on user preferences : GET /auth/suggest

## Admin-Related Endpoints

- Fetch all users : GET /admin/users
- Fetch specific user by id : GET /admin/users/{id}
- Update specific user by id : PUT /admin/users/{id}
- Delete specific user by id : DELETE /admin/users/{id}
- Fetch all the books : GET /admin/books
- Fetch specific book by id : GET /admin/books/{id}
- Add Book in the system : POST /admin/books
- Update specific book by id : PUT /books/{id}
- Delete specific book by id : DELETE /books/{id}

## SuperAdmin-Related Endpoints
- Add new Admins for the system : POST /super-admin/add-admin
- Fetch all sales reports : GET /super-admin/sales-reports
- Fetch sales reports by specifying date ranges : GET /super-admin/sales-reports/date-range

## User Related Endpoints
- Purchase specific book : POST /user/purchase/{id}/{bookId}
- Search books with criteria : GET /user/search