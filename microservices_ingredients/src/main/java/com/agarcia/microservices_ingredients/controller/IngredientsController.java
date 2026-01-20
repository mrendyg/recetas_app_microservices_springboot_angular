package com.agarcia.microservices_ingredients.controller;

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
import com.agarcia.microservices_ingredients.services.IngredientsService;

@RestController
public class IngredientsController extends CommonController<IngredientsEntity, IngredientsService> {

    // Editar ingrediente especifico
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody IngredientsEntity ingredientsEntity, 
    @PathVariable Long id){
        // Buscar ingrediente por id
        Optional<IngredientsEntity> o = service.findById(id);
        // Si no existe el ingrediente
        if (o.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        // Si existe el ingrediente
        IngredientsEntity ingredientsDB = o.get();
        ingredientsDB.setName(ingredientsEntity.getName());
        ingredientsDB.setBrand(ingredientsEntity.getBrand());
        ingredientsDB.setQuantity(ingredientsEntity.getQuantity());
        ingredientsDB.setCost(ingredientsEntity.getCost());
        ingredientsDB.setStock(ingredientsEntity.getStock());
        // Guardar cambios
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(ingredientsDB));
    }

    // Buscar ingredientes por nombre (contenga el termino)
    @GetMapping("/search-by-name/{term}")
    public ResponseEntity<?> searchByName(@PathVariable String term){
        return ResponseEntity.ok(service.findByName(term));
    }


}
