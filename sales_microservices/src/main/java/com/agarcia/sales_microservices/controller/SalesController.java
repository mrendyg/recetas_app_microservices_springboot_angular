package com.agarcia.sales_microservices.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agarcia.commons_microservices.services.controller.CommonController;
import com.agarcia.commons_sales.persistence.models.SalesEntity;
import com.agarcia.sales_microservices.service.SalesService;

@RestController
public class SalesController extends CommonController<SalesEntity, SalesService>{
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody SalesEntity sales, @PathVariable Long id){
        Optional<SalesEntity> o = service.findById(id);
        
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        SalesEntity salesDB = o.get();
        salesDB.setSellerId(sales.getSellerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(salesDB));

    }
}
