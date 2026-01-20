package com.agarcia.microservice_cookbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.commons_ingredients.persistence.models.IngredientsEntity;
import com.agarcia.commons_microservices.services.controller.CommonController;
import com.agarcia.microservice_cookbook.persistence.models.CookbookEntity;
import com.agarcia.microservice_cookbook.service.CookbookService;

@RestController
public class CookbookController extends CommonController<CookbookEntity, CookbookService> {
    
    // Editar cookbook especifico
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody CookbookEntity cookbookEntity, 
    @PathVariable Long id) {
        // Buscar cookbook por id
        Optional<CookbookEntity> o = service.findById(id);
        // Si no existe el cookbook
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Si existe el cookbook
        CookbookEntity cookbookDb = o.get();
        cookbookDb.setName(cookbookEntity.getName());
        cookbookDb.setImage(cookbookEntity.getImage());
        cookbookDb.setInstructions(cookbookEntity.getInstructions());
        cookbookDb.setDescription(cookbookEntity.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cookbookDb));
    }

    // Agregar ingrediente al cookbook
    @PutMapping("/{id}/add-ingredient")
    public ResponseEntity<?> addIngredient(@RequestBody List<IngredientsEntity> ingredient, @PathVariable Long id) {
        Optional<CookbookEntity> o = service.findById(id);
        // Si no existe el cookbook
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Si existe el cookbook
        CookbookEntity cookbookDb = o.get();
        // Agregar ingredientes al cookbook
        ingredient.forEach(a -> {
            cookbookDb.addIngredient(a);
        });
        // Guardar cambios
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cookbookDb));
    }

    // Eliminar ingrediente del cookbook
    @PutMapping("/{id}/remove-ingredient") 
    public ResponseEntity<?> removeIngredient(@RequestBody IngredientsEntity ingredient, @PathVariable Long id) {
        Optional<CookbookEntity> o = service.findById(id);
        // Si no existe el cookbook
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        //Validación de entrada
        if (ingredient == null || ingredient.getId() == null) {
            return ResponseEntity.badRequest().body("El ingrediente y su ID son requeridos");
        }
        
        CookbookEntity cookbookDb = o.get();
        
        // Verificar si se eliminó correctamente
        boolean removed = cookbookDb.removeIngredient(ingredient);
        // Si el ingrediente no estaba en el cookbook
        if (!removed) {
            return ResponseEntity.badRequest().body("El ingrediente no fue encontrado en el cookbook");
        }
        // Guardar cambios
        CookbookEntity updatedCookbook = service.save(cookbookDb);
        return ResponseEntity.ok(updatedCookbook);  // 200 OK es suficiente
    }

    // Buscar cookbook por id de ingrediente
    @GetMapping("/ingredient/{ingredientId}")
    public ResponseEntity<?> getCookbookByIngredientId(@PathVariable Long ingredientId) {
        CookbookEntity cookbook = service.findCookbookByIngredientId(ingredientId);
        return ResponseEntity.ok((cookbook));
    }
    
}
