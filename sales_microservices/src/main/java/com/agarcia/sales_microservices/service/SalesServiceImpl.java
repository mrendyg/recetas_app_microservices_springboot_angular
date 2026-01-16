package com.agarcia.sales_microservices.service;

import org.springframework.stereotype.Service;

import com.agarcia.commons_microservices.services.CommonServiceImp;
import com.agarcia.commons_sales.persistence.models.SalesEntity;
import com.agarcia.sales_microservices.persistence.repository.SalesRepository;

@Service
public class SalesServiceImpl extends CommonServiceImp<SalesEntity, SalesRepository> implements SalesService{
    
}
