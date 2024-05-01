
# Task Management System

This project is a task management system that allows users to manage their tasks efficiently.

## Features

- **Create Tasks:** Users can create tasks with a title, description, priority, status, and due date.
- **View Tasks:** Users can view a list of all tasks or filter tasks based on various criteria.
- **Update Tasks:** Users can update the details of a task, such as its status, priority, or due date.
- **Delete Tasks:** Users can delete tasks that are no longer needed.
- **Search Tasks:** Users can search for tasks based on keywords in the title or description.
- **Email Notification:** The system supports email notifications regarding task updates
- **Task and Notification History:** Users can view a list of all task history and Notification History.
- **User Authentication:** The system supports user authentication to ensure secure access to tasks.
  Use the credentials below to authenticate and get oauth token.

- **Path:** localhost:8080/authenticate
`{
    "username":"ardeneric",
    "password":"123-456-789"
}`

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database

## Setup

1. Clone the repository: `git clone https://github.com/your/repository.git`
2. Navigate to the project directory: `cd banquemisr`
3. Build the project: `mvn clean install`
4. Run the project: `mvn spring-boot:run`

## Usage

1. Run your Spring Boot application and navigate to [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/index.html) in your web browser.
   You should see the Swagger UI interface with documentation for your APIs.

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`
3. Make your changes and commit them: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request.
