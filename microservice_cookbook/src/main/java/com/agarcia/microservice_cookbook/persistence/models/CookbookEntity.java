package com.agarcia.microservice_cookbook.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agarcia.commons_ingredients.persistence.models.IngredientsEntity;
import com.agarcia.commons_sales.persistence.models.SalesEntity;

@Entity
@Table(name = "cookbook")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CookbookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String image;
    private String description;
    private String instructions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cookbook_id")
    private List<IngredientsEntity> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "cookbook", fetch = FetchType.LAZY)
    private List<SalesEntity> sales = new ArrayList<>();

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    // Métodos helpers para la relación con ingredients
    public void addIngredient(IngredientsEntity ingredient) {
        this.ingredients.add(ingredient);
    }

    public boolean removeIngredient(Long ingredientId) {
        return ingredients.removeIf(ing -> ing.getId().equals(ingredientId));
    }
}