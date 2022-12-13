### Wallet Project
This file solely contains instructions on how to run the application.
For info on the technical decisions, see  [project wiki](/project-wiki.md).

This is a maven based Spring Boot project. If you are using a modern IDE chances are that you could build the project directly from the IDE. If you are using command line, you can use the following command to build the project.

    mvn clean install 

Please keep in mind that the project needs to connect to MySQL db for initializing tables and for functioning correctly.
You could set your db credentials in the application.properties file.

    spring.datasource.url=jdbc:mysql://localhost:3306/wallet
    spring.datasource.username={yourdbusername}
    spring.datasource.password={yourdbpassword}