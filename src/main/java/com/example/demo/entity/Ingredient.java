package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column
    private double quantity;

    public Ingredient(String name, double quantity, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.recipe = recipe;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id") 
    private Recipe recipe;

}

