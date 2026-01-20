package com.agarcia.sales_microservices.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agarcia.commons_sales.persistence.models.SellerEntity;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario   
}
