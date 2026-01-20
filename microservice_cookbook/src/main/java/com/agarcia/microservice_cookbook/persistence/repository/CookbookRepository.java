package com.agarcia.microservice_cookbook.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agarcia.microservice_cookbook.persistence.models.CookbookEntity;

public interface CookbookRepository extends CrudRepository<CookbookEntity, Long> {
    
    // JPQL query to fetch a CookbookEntity by an associated Ingredient's ID
    @Query("select c from CookbookEntity c join fetch c.ingredients a where a.id = ?1")
    public CookbookEntity findCookbookByIngredientId(Long ingredientId);
}
