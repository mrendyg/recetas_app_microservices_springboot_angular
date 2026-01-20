package com.agarcia.microservices_ingredients.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agarcia.commons_ingredients.persistence.models.IngredientsEntity;
import com.agarcia.commons_microservices.services.CommonServiceImp;
import com.agarcia.microservices_ingredients.persistence.repository.IngredientsRepository;


@Service
public class IngredientsServiceImpl extends CommonServiceImp<IngredientsEntity, IngredientsRepository> implements IngredientsService {

    // Buscar ingredientes por nombre (contenga el termino)
    @Override
    @Transactional(readOnly = true)
    public List<IngredientsEntity> findByName(String term) {
        return repository.findByName(term);
    }

}
