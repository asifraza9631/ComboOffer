package com.example.ComboOffer.controller;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.ComboOffer.entity.ComboOffer;
import com.example.ComboOffer.entity.User;
import com.example.ComboOffer.entity.UserComboOffer;
import com.example.ComboOffer.exception.ComboOfferException;
import com.example.ComboOffer.service.ComboOfferService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/combo-offers")
@Log4j2
public class ComboOfferController {
    @Autowired
    private ComboOfferService comboOfferService;

    @GetMapping
    public ResponseEntity<List<ComboOffer>> getComboOffers() {
        List<ComboOffer> comboOffers = comboOfferService.getComboOffers();
        return ResponseEntity.ok(comboOffers);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyComboOffer(@RequestBody Map<String, Object> requestBody) {
        try {
            log.info("****applyComboOffer method starter***** ");
            Map<String, Object> user = (Map<String, Object>) requestBody.get("user");
            Map<String, Object> combooffer = (Map<String, Object>) requestBody.get("combooffer");

            User u = new User();
            u.setId((Integer) user.get("id"));
            u.setName((String) user.get("name"));
            u.setEmail((String) user.get("name"));

            ComboOffer c = new ComboOffer();
            c.setId((Integer) combooffer.get("id"));
            c.setName((String) combooffer.get("name"));
            c.setDescription((String) combooffer.get("description"));
            c.setDiscount((Double) combooffer.get("discount"));
             log.info("user object => " +u + " and combooffer object " + c);




            //comboOfferService.applyComboOffer((User) requestBody.get("user"), (ComboOffer) requestBody.get("combooffer"));
         String message=    comboOfferService.applyComboOffer(u,c);

            return ResponseEntity.ok(message);
        } catch (ComboOfferException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
