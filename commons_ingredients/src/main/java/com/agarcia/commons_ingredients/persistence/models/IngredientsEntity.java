package com.agarcia.commons_ingredients.persistence.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Entidad JPA que representa la tabla "ingredients" en la base de datos. 
 * Mapea la estructura de la tabla de ingredientes con sus atributos básicos
 * y operaciones de persistencia automáticas.
*
 * @author Andy Garcia
 * @version 1.0
 * @since 2025
 * 
 * @see IngredientsService
 * @see IngredientsRepository
 * 
 * @entity
 * @table ingredients
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "ingredients")
public class IngredientsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private int quantity;

    private int cost;
    
    private int stock;

    // Timestamp de creación del registro
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    // Método que se ejecuta antes de persistir la entidad para establecer la fecha de creación
    @PrePersist
    protected void onCreate() {
        createAt = new Date();
    }

    // Sobrescribe el método equals para comparar entidades por su ID
    @Override
    public boolean equals(Object obj) {
        // Verifica si el objeto es la misma instancia
        if (this == obj)
            return true;
        // Verifica si el objeto es una instancia de IngredientsEntity
        if (obj instanceof IngredientsEntity){
            return false;
        }
        // Compara los IDs de ambas entidades
        IngredientsEntity other = (IngredientsEntity) obj;
        return this.id != null && this.id.equals(other.getId());
    }

}
