// Definición del paquete: organiza el proyecto y pone este archivo en la carpeta principal de My_Pet
package com.example.My_Pet;

// Importaciones: Trae las herramientas de Spring Boot para poder encender el backend
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: Hace que el programa busque y active automáticamente todos los archivos de controladores, servicios y modelos que creamos
@SpringBootApplication
public class MyPetApplication {

    // Método main: Es el interruptor principal. El proyecto busca esta línea exacta para saber por dónde empezar a correr
    public static void main(String[] args) {
        // Enciende el servidor web en el puerto 8082 y abre la conexión con la base de datos MySQL de una vez
        SpringApplication.run(MyPetApplication.class, args);
    }

}