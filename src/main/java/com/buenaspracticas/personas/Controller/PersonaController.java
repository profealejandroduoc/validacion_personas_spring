package com.buenaspracticas.personas.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buenaspracticas.personas.Service.PersonaService;
import com.buenaspracticas.personas.model.Persona;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<String> postPersona(@Valid @RequestBody Persona persona) {
        Persona p = personaService.create(persona);
        return ResponseEntity.ok("Persona agregada correctamente");
    }
}
