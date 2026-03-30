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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<String> postPersona(@Valid @RequestBody Persona persona) {
        try {
            Persona p = personaService.create(persona);
            return ResponseEntity.ok("Persona agregada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos incompletos" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getPersonas() {
        List<Persona> lista = personaService.readAll();

        if (lista.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("Recursos no encontrados");
        }

        return ResponseEntity.ok(lista);
    }

}
