# How to Setup
## Setup using docker
First, get [Docker](https://docs.docker.com/get-docker/) in your machine. 

1. from command line, run `docker compose up` from root folder. [The same folder this ReadMe is located.]

This will start front-end, backend and postgresql server. Front end will run on port 3000, backend will run in port 8080 
and postgresql will run on port 5432 by default.

It might take ~5 mins to start all the server depending on internet speed. 

2. After that you should be able to access the app using this url: http://localhost:3000

3. To stop servers just run  `docker compose down` 

## Manual Setup

For windows: 

Front-end setup:
Pre requisite: Download and Install Node >= v16.x.x 
1. From frontend folder, run `npm install`
2. Then run `npm start`. This will start front end project at port 3030

Back-end setup:

1. Download and Install [Java 8](https://www.oracle.com/java/technologies/downloads/#java8-windows)
 You might need to create an Oracle account if haven't already
2. Go to Environment Variables.
 On User Variables section, add JAVA_HOME and set value to the url of JDK installation directory.
 
 eg. `JAVA_HOME ='C:\Program Files\Java\jdk1.8.0_331`
 On System Variables section-> edit Path-> add new -> Add url of JDK/bin directory.
   eg. `C:\Program Files\Java\jdk-13.0.2\bin`

3. Download [Maven](https://maven.apache.org/download.cgi) , download the zip file. Extract it in `C:\Program Files`.
4. Again, go to Environment Variables. 
 On, User Variables section, add MAVEN_HOME and set value to the url of maven directory.
 Eg, `MAVEN_HOME = C:\Program Files\apache-maven-3.8.1-bin\apache-maven-3.8.1`
 On System Variables section -> edit Path -> add new -> Add url of maven/bin directory.
 Eg, `C:\Program Files\apache-maven-3.8.1-bin\apache-maven-3.8.1\bin`

5. Download and install [postgresql](https://www.postgresql.org/download/windows/) version 14.2
 When prompet  during installation, use username = 'postgres' and password = '1234'.
 Alternatively, use any username/password of your choice, then go to backend-> src->main->resources
 Edit the application.properties file and change the following values with your username/password:
 ```
 spring.datasource.username=postgres
 spring.datasource.password=1234
 ```
6. from command prompt,  run `mvn clean package` from /backend folder.
7. then run `java -jar backend.jar` from /backend/target folder.
 
 This should start the back-end project at port 8080.
 
 TroubleShooting: 
 In case of server not starting properly, 
 -> Check if postgresql is running. Do restart postgresql if needed. See online for more resources.
 -> Check if port 8080 is using by another service, if necessary kill this port and do step 7.
 
 
 For Mac and Linux Users:
 
 Steps mentioned for Windows will be slightly different for your OS. Kindly check online resource
 to complete the above mentioned steps for Windows.  
 