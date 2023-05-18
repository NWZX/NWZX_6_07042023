# MDD APP

This is the README file for the MDD app. The app is a web-based application built using Java 11 and NodeJS 18, with a MariaDB database. The app allows users to publish article and subscribe to them. The app also allows users to create and manage their own account.

## Requirements
Before installing and running the MDD app, make sure you have the following requirements installed on your machine:

 - JAVA 11
 - NodeJS 18
 - MariaDB


## Installation

To install and run the app, follow these steps:

1. Set up a Maria Server and create a database named "mdd".

2. Import the file "ressources/script.sql" to create the schema.

3. For the frontend (VSCode):

    a. Navigate to the "front" directory.
    
    b. Run the following commands:
    
       ```
       npm install
       npm run start
       ```

    c. The app should be running on http://localhost:4200

4. For the backend (IntelliJ):

    a. Edit the file "back/src/main/resources/application.properties" with your database credentials.

        ```
        spring.datasource.username=YOUR USERNAME
        spring.datasource.password=YOUR PASSWORD
        ```
    
    b. Run the following command:
    
       ```
       cd back
       mvn clean install
       ```


