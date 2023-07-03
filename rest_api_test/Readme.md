# Springboot User Controller Test Project

This project aims to demonstrate how to test a Springboot application using JUnit and Mockito. The UserController is the main focus of the testing, which includes methods such as getAllUsers, getUserById, updateUser, and deleteUserById. The tests also consider cases where the user does not exist. Additionally, the UserController adheres to REST API standards by using the ResponseEntity class.

## Technologies Used

The following technologies were used in this project:

- Springboot
- JUnit
- Mockito
- Hamcrest
- Apache Derby
- Lombok
- Hibernate Validations

## Installation and Setup

1. Clone the repository to your local machine using the following command:

```
git clone https://github.com/xllWhiteReaper/spring_boot/tree/develop

```

2. Switch branches by using the following command:

```
git checkout develop
```

3. Navigate to the project directory:

```
cd rest_api_test
```

4. Open the project with your favorite IDE (Optional)
5. You might want to uncomment the application properties and edit them by using your database parameters
6. Build the application using the following command:

```
./mvnw clean package

```

6. If step 5 didn't work, you might need to install Maven locally
7. Start the application using the following command:

```
java -jar target/rest_api_test.jar
```

## Running tests

1. Navigate to the project directory.
2. Run the following command to execute the tests:

```
./mvnw test
```

## Conclusions

This project demonstrates how to test a Springboot application using JUnit and Mockito. The UserController is thoroughly tested, including cases where the user does not exist. Additionally, the controller adheres to REST API standards by using the ResponseEntity class.

## Contributing

Contributions to this project are welcome. If you find a bug or want to suggest a new feature, please create an issue on GitHub. If you want to contribute code, please fork the project, create a new branch, and submit a pull request.

## License

This project is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
