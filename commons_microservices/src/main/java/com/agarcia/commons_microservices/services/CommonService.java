package com.agarcia.commons_microservices.services;

import java.util.Optional;

// Common service interface for CRUD operations
public interface CommonService<E> {
    
    // Retrieve all entities
    Iterable<E> findAll();
    // Retrieve an entity by its ID
    Optional<E> findById(Long id);
    // Save or update an entity
    E save(E entity);
    // Delete an entity by its ID
    void deleteById(Long id);
}
