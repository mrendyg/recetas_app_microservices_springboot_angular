package com.agarcia.microservices_ingredients.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agarcia.commons_ingredients.persistence.models.IngredientsEntity;

public interface IngredientsRepository extends JpaRepository<IngredientsEntity, Long>{

    // Buscar ingredientes por nombre (contenga el termino)
    @Query("SELECT i FROM IngredientsEntity i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<IngredientsEntity> findByName(String term);
}
