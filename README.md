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
