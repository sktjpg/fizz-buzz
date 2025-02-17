# Kotlin Project with Spring Boot and Gradle

This project uses Kotlin with Spring Boot and Gradle. Below are the steps to run the project using Docker Compose.

## Requirements

- Docker
- Docker Compose

## Instructions

1. Clone the repository:

    ```bash
    git clone https://github.com/sktjpg/fizz-buzz.git
    cd your-repository
    ```

2. Run Docker Compose:

    ```bash
    docker-compose up
    ```

3. Access the application:

   The application will be available at `http://localhost:8080`.

## Useful Commands

- To stop the containers:

    ```bash
    docker-compose down
    ```

- To rebuild the containers:

    ```bash
    docker-compose up --build
    ```

## Project Structure

- `src/main/kotlin`: Kotlin source code
- `build.gradle`: Gradle configuration file
- `docker-compose.yml`: Docker Compose configuration file

## Notes

Make sure to have Docker and Docker Compose installed on your system before running the above commands.