package com.agarcia.commons_microservices.services.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.agarcia.commons_microservices.services.CommonService;

//  CommonController class providing generic CRUD operations
public class CommonController<E, S extends CommonService<E>> {

    // Autowired service instance
    @Autowired
    protected S service;

    // Endpoint to list all entities
    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(service.findAll());
    }

    // Endpoint to get a specific entity by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<E> o = service.findById(id);  
        //  Check if entity is present
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        //  Return the found entity
        return ResponseEntity.ok(o.get());
    }

    // Endpoint to create a new entity
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody E entity){
        E ingredientsDB = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientsDB);
    }

    // Endpoint to delete an entity by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
