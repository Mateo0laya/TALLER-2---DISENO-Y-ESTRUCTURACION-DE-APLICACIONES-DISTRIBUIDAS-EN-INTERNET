# TALLER 1: APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS,MAVEN, GIT)

En este proyecto se creo una aplicación usando Java, la cual crea un servidor usando WebSockets para realizar la conexión cliente-servidor, esta aplicación funciona como gateway entre el cliente y el API https://www.omdbapi.com/, con la finalidad de conocer información basica de una pelicula especifica a través del titulo de la pelicula. En este proyecto se priorizo la eficiencia realizando la implementación de una memoria caché que almacena las busquedas que se realizan para asi ahorrar recursos en la consulta al API y mejorar los tiempos de respuesta.

![image](https://github.com/Mateo0laya/Taller-1-Aplicacion-distribuidas---AREP/assets/89365336/923aa8bb-5f9c-40aa-ac45-871f70cf207b)

## Arquitectura 
La arquitectura debe tener las siguientes características.

1. El cliente Web debe ser un cliente asíncrono que corra en el browser  y use Json como formato para los mensajes.
2. El servidor de servirá como un gateway para encapsular llamadas a otros servicios Web externos.
3. La aplicación debe ser multiusuario.
4. Todos los protocolos de comunicación serán sobre HTTP.
5. Los formatos de los mensajes de intercambio serán siempre JSON.
6. La interfaz gráfica del cliente debe ser los más limpia y agradableolo HTML y JS (Evite usar librerías complejas). Para invocar métodos REST desde el cliente usted puede utilizar la tecnología que desee.
7. Debe construir un cliente Java que permita probar las funciones del servidor fachada. El cliente utiliza simples conexiones http para conectarse a los servicios. Este cliente debe hacer pruebas de concurrencia en su servidor de backend.
8. La fachada de servicios tendrá un caché que permitirá que llamados que ya se han realizado a las implementaciones concretas con parámetros específicos no se realicen nuevamente. Puede almacenar el llamado como un String con su respectiva respuesta, y comparar el string respectivo. Recuerde que el caché es una simple estructura de datos.
9. Se debe poder extender fácilmente, por ejemplo, es fácil agregar nuevas funcionalidades, o es fácil cambiar el proveedor de una funcionalidad.
10. Debe utilizar maven para gestionar el ciclo de vida, git y github para almacenar al código fuente y heroku como plataforma de producción.
11. En el backend debe utilizar solo Java. No puede utilizar frameworks como SPRING.

## Diseño de la aplicación



# Extensión de la aplicación



## Guia de inicio

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba. Consulte implementación para obtener notas sobre cómo implementar el proyecto en un sistema en vivo.

### Prerequisitos

- Java 8
- Maven
- Git 

### Instalación

Ubiquese en el directorio en donde desea descargar el repositorio
$ git clone https://github.com/Mateo0laya/Taller-1-Aplicacion-distribuidas---AREP

Cambie al directorio del repositorio
$ cd Taller-1-Aplicacion.distribuidas---AREP

Compile el proyecto
$ mvn compile

Empaquete el proyecto
$ mvn package



Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Construido con

* [Java](https://www.java.com/es/) - The main programming language
* [Maven](https://maven.apache.org/) - Dependency Management

## Version

Version 1.0.0.

## Autor

Mateo Olaya Garzon
