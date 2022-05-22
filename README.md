# StudentLibrary

# Swagger documentation can be found here --> http://localhost:8083/swagger-ui.html

| METHODS      | URLs           | ACTIONS  |
| ------------- |:-------------:| -----:|
| POST	    | /api/auth/register | Register a student |
| POST   | /api/auth/login/{id}      |   Student login using student ID |
| GET | /api/books      |    All the books available in the Library |


To start the application 
1. Clone the repository locally using **git clone https://github.com/sainathnair/StudentLibrary.git**
2. Import the project in eclipse or intellij
3. Right click on  the main class and choose *Run as Java Application* or click on the *execute* button 
4. Once the server is started, open  *http://localhost:8083/swagger-ui.html *
5. Register a student using the register endpoint and entering details in the request body (Id isn't mandatory but as it's taken care by the application)
6. Login using the id used to register 
7. The application is authorized internally using a JWT token 
