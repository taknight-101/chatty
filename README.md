# Chatty: Where you can find good people to talk to. 



This project is a sample playground which i created to train on Full-stack web-dev with Angular/Spring Boot

## Tech


- Angular
- mdbootstrap
- Spring Framework
- Spring Boot 
- JPA 
- Spring Data JPA
- Spring Security



## Installation




Angular-front-end 

```sh
cd chatty-front/
npm i --legacy-peer-deps
npm start 
```

Spring-Boot-backend

```sh
cd chatty 
./mvnw spring-boot:run
```


Database [PostgreSQL + Docker]


1. Run the following commands  
```sh
docker pull postgres:15-alpine
docker run --name postgres15 -p 5432:5432 -e POSTGRES_DB=chatty -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -d postgres:15-alpine

#To check db creation/login => docker exec -it postgres15 psql -U root chatty
```
2. Open any PostgreSQL Client GUI tool i.e. "pgAdmin" & execute the .sql script located @ ./database-files against the "chatty" db schemas


3. front-end App is accessed on: http://localhost:4200/

4. back-end App is accessed on: http://localhost:8080/api
