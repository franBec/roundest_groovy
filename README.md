# roundest_groovy

## Project Overview
This project is implemented in **Groovy Spring Boot 3** and provides a RESTful API for "The roundest Pokémon" type exercise. It is part of a group of repositories that all aim to solve the same exercise using different backend languages.

- roundest_groovy (this one)
- [roundest_java](https://github.com/franBec/roundest_java)
- [roundest_kotlin](https://github.com/franBec/roundest_kotlin)

## API Documentation
The OpenAPI documentation for the API can be found in the [src/main/resources/openapi/roundest.yaml file](https://github.com/franBec/roundest_groovy/blob/main/src/main/resources/openapi/roundest.yaml).

## Getting Started

### Prerequisites
- For running with IntelliJ:
    - **IntelliJ** (Community Edition works fine)
- For containerized deployment
    - **Docker**

### Run Locally with IntelliJ
1. Clone the repository:
    ```bash
    git clone https://github.com/franBec/roundest_groovy
    ```
2. Open the project in IntelliJ IDEA.
3. Set the environment variable `SPRING_PROFILES_ACTIVE` to `dev` for local development.
4. Run the application from the IntelliJ run configurations.

### Run with Docker
1. Clone the repository:
     ```bash
     git clone https://github.com/franBec/roundest_groovy
     ```
2. Navigate to the project directory:
    ```bash
    cd roundest_groovy
    ```
3. Build the Docker image:
    ```bash
    docker build -t roundest_groovy .
    ```
4. Run the Docker container:
     ```bash
     docker run -p 8080:8080 roundest_groovy
     ```
## About mutation testing

In contrast to its sibling repos, roundest_groovy [is not able to support mutation testing](https://github.com/szpak/gradle-pitest-plugin/issues/221#issuecomment-668480036).

## Author
Franco Exequiel Becvort <🐤/>
- [Linkedin](https://www.linkedin.com/in/franco-becvort/)
- [Website](https://pollito.dev/)

This project is intended for educational purposes.
