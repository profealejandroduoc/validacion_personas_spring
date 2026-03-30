# Validaciones y buenas prácticas

**Fullstack I**

## Agregar dependencia al XML

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

## Crear un medelo

En este caso creamos el modelo persona con id, nombre, apellido y edad y sobre los atributos agregaremos las anotaciones que sean necesarias para validar nuestra clase

```java
public class Persona {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String apellido;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede ser mayor a 120")
    private int edad;

}
```

## Agregar las capas repository, service, y controller

Para el ejemplo pasaremos directamente por las capas hasta el controlador

```java
package com.buenaspracticas.personas.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {

}

```

agregar el metodo POST para persona, en el métodos se debe agregar la notación *@Valid* para que se aplique la validación correctamente. Además utilizaremos los métodos *ResponseEntity* que servirán para personalizar de forma más profesional nuestros mensajes

```java
public ResponseEntity<String> postPersona(@Valid @RequestBody Persona persona){
    return ResponseEntity.ok("Persona agregada correctamente");
}
```

En este punto podemos usar postman e intentar un post:

```json
{
    "nombre":"Wacoldo",
    "apellido":"Soto",
    "edad":20
}
```

Debería devolver que se agrego correctamente

Ahora podemos intentar el error con datos vacíos, lo que debería devolver bad Request

```json
{
    "nombre":"Diogenes",
    "apellido":"",
    "edad":40
}
```

## Capturando el error

Podemos manejar el error de varias formas, por ejemplo con try catch pero no es recomandado si uso @valid entonces crearemos nuestro manejo de errores personalizado

Para ello crearemos un nuevo package(carpeta) exception y crearemos una clase de java llamada *GlobalExceptionHandler.java* como se conoce de forma standar

```java
package com.buenaspracticas.personas.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejoErroresValidacion(MethodArgumentNotValidException ex) {
        @SuppressWarnings("unused")
        Map<String, String> errores = new HashMap<>();

        /*
         * 
         * for (var error : ex.getBindingResult().getFieldErrors()) {
         * errores.put(error.getField(), error.getDefaultMessage());
         * }
         */

        // Lo mismo usando función de flecha
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return errores;

    }

}
```
