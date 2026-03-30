package com.buenaspracticas.personas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buenaspracticas.personas.model.Persona;
import com.buenaspracticas.personas.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona create(Persona persona) {
        return personaRepository.agregar(persona);
    }

    public List<Persona> readAll() {
        return personaRepository.leerTodos();
    }
}
