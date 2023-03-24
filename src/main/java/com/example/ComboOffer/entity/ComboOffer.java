package com.example.ComboOffer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComboOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private double discount;


    public ComboOffer(String name, String description, double discount) {
        this.name = name;
        this.description = description;
        this.discount = discount;
    }

    // Getters and setters omitted for brevity
}
