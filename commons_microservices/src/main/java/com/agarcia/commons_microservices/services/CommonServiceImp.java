package com.agarcia.commons_microservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// Common service implementation for CRUD operations
public class CommonServiceImp<E, R extends CrudRepository<E, Long>> implements CommonService<E> {
    
    // Repository for entity operations
    @Autowired
    protected R repository; // Cambiado a protected para mejor acceso

    // Retrieve all entities
    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll(){
        return repository.findAll();
    }

    // Retrieve an entity by its ID
    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    // Save or update an entity
    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    // Delete an entity by its ID
    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}