package com.example.ComboOffer.repository;

import com.example.ComboOffer.entity.ComboOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboOfferRepository  extends JpaRepository<ComboOffer,Integer> {
}
