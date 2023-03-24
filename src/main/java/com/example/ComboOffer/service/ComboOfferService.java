package com.example.ComboOffer.service;


import com.example.ComboOffer.entity.ComboOffer;
import com.example.ComboOffer.entity.User;
import com.example.ComboOffer.entity.UserComboOffer;
import com.example.ComboOffer.exception.ComboOfferException;
import com.example.ComboOffer.repository.ComboOfferRepository;
import com.example.ComboOffer.repository.UserComboOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ComboOfferService {
    @Autowired
    private ComboOfferRepository comboOfferRepository;
    @Autowired
    private UserComboOfferRepository userComboOfferRepository;



    public List<ComboOffer> getComboOffers() {
        return comboOfferRepository.findAll();
    }

    public String applyComboOffer(User user, ComboOffer comboOffer) throws ComboOfferException {
        if (!canApplyComboOffer(user, comboOffer)) {
            throw new ComboOfferException("You have already applied this combo offer within the last week");
        }

         LocalDate appliedDate = LocalDate.of(2023,3,17);

        // Check if today is the second Saturday or Sunday of the month
        boolean isComboOfferDay = false;
        if (appliedDate.getDayOfWeek() == DayOfWeek.SATURDAY || appliedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            LocalDate secondWeekSaturday = appliedDate.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));

            LocalDate sunday = appliedDate.with(DayOfWeek.SUNDAY);

            if (appliedDate.equals(secondWeekSaturday) || appliedDate.equals(sunday)) {
                isComboOfferDay = true;
            }
        }

        // If it's a combo offer day, apply the offer
        if (isComboOfferDay) {
            UserComboOffer userComboOffer = new UserComboOffer(user.getId(), comboOffer.getId(), appliedDate);
            UserComboOffer save = userComboOfferRepository.save(userComboOffer);

            return  "Today is a combo offer day!";

        } else {
             return "Sorry, today is not a combo offer day.";
        }

    }

    public boolean canApplyComboOffer(User user, ComboOffer comboOffer) {
        UserComboOffer userComboOffer = userComboOfferRepository.findByUserIdAndComboOfferId(user.getId(), comboOffer.getId());
        if (userComboOffer == null) {
            return true; // User has not applied this combo offer before
        }

            LocalDate appliedDate = userComboOffer.getAppliedDate();
            //LocalDate appliedDate = userComboOffer.getAppliedDate();
        LocalDate now = LocalDate.now();
        return appliedDate.plusWeeks(1).isBefore(now); // Check if the waiting period has passed
    }
}
