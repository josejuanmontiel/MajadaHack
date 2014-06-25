Codigo del hackaton MahadaHack 2014 (http://www.meetup.com/Hackathon-Lovers/events/179559362)

Proyecto montado con Spring Boot:

 - Spring Boot: posibilidad de arrancar directamente con el plugin de maven o generar un war para despliegue en tomcat o similar 

 - Servicio completo RESTful con Spring

 - Capa de base de datos con Spring DATA mongoDB

Requisitos:

 - Maven (instalado y configurado)

 - mongoDB server (instalado y arrancado, en localhost y con el puerto por defecto)

Comandos

 - Arrancar directamente con el plugin de SpringBoot:
 
    ```
    mvn spring-boot:run
    ```
  
 - Generar war (así como generar informes de jacoco):
 
    ```
    mvn clean package
    ```

Urls de acceso:

 - Pagina de inicio -> http://localhost:8090
