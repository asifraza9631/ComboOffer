package com.example.ComboOffer.repository;


import com.example.ComboOffer.entity.UserComboOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComboOfferRepository extends JpaRepository<UserComboOffer, Integer> {
    UserComboOffer findByUserIdAndComboOfferId(Integer userId, Integer comboOfferId);
}

