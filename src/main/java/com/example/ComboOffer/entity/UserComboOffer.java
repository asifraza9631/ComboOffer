package com.example.ComboOffer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class UserComboOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "combo_offer_id")
    private Integer comboOfferId;
    @Column(name = "applied_date")
    private LocalDate appliedDate;



    public UserComboOffer(Integer userId, Integer comboOfferId, LocalDate appliedDate) {
        this.userId = userId;
        this.comboOfferId = comboOfferId;
        this.appliedDate = appliedDate;
    }

    // Getters and setters omitted for brevity
}
