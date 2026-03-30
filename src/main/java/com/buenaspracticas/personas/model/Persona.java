package com.buenaspracticas.personas.model;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
@NoArgsConstructor

public class Persona {

    private static final AtomicInteger contador = new AtomicInteger(1);

    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String apellido;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede ser mayor a 120")
    private int edad;

    public Persona(String nombre, String apellido, int edad) {
        this.id = contador.getAndIncrement();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

}
