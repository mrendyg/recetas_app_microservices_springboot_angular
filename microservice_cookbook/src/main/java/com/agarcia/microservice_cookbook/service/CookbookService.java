package com.agarcia.microservice_cookbook.service;

import com.agarcia.commons_microservices.services.CommonService;
import com.agarcia.microservice_cookbook.persistence.models.CookbookEntity;

public interface CookbookService extends CommonService<CookbookEntity> {
    
    // Method to find a cookbook by ingredient ID
    public CookbookEntity findCookbookByIngredientId(Long ingredientId);
    
}
