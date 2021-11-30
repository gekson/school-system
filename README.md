# school-system

to run:
mvn spring-boot:run

You can check available endpoints through the swagger:
http://localhost:8084/swagger-ui.html

Create a Student use a object like:
{
  "id": 0,
  "name": "string"
}

Create a Course use a object like
{
  "id": 0,
  "title": "string"
}

There was a problem to upload the docker with Mysql, for now it is uploading with H2.
