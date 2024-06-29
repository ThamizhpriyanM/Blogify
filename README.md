# Blogify Application

Blogify is a web application for managing blog posts using Spring Boot and MongoDB.

## Features

- **User Authentication**: Secure login and registration with role-based access (user and admin).
- **Blog Management**: CRUD operations for creating, reading, updating, and deleting blog posts.
- **Role-Based Access Control**: Users can manage their own posts; admins have full access to all posts and user management.
- **Responsive UI**: Utilizes Thymeleaf and Bootstrap for a responsive and user-friendly interface.
- **Persistence**: MongoDB used for storing user credentials and blog post data.

## Technologies Used

- **Backend**: Java, Spring Boot, Spring Data MongoDB
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: MongoDB
- **Build Tool**: Maven

## Setup and Run

1. **Clone the repository**: https://github.com/ThamizhpriyanM/blogify.git

2. **Build and run the application**:

3. **Access the application**:
Open your web browser and go to [http://localhost:8080](http://localhost:8080)

## Usage

- **Login**: Access the login page to authenticate as a user or admin.
- **Home**: Users are directed to their respective home pages based on their role after login.
- **Create, Update, Delete Posts**: Users can manage their own posts; admins can manage all posts.
- **User Management**: Admins can view all users and their posts.

## API Endpoints

- Detailed list of API endpoints for blog post management and user operations.

## Deployment

Deploy Blogify to a server or cloud platform supporting Java applications and MongoDB. Configure database settings and environment variables as needed.

## License

This project is licensed under the MIT License.
