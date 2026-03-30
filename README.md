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
