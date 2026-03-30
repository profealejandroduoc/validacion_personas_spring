package com.buenaspracticas.personas.repository;

import java.util.ArrayList;
import java.util.List;

import com.buenaspracticas.personas.model.Persona;

public class PersonaRepository {
    List<Persona> personas = new ArrayList<>();

    public Persona agregar(Persona persona) {
        personas.add(persona);
        return persona;
    }

}
