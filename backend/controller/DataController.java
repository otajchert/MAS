package com.zbyszkobud.controller;

import com.zbyszkobud.service.ApplicationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @PostMapping("/save-data")
    public ResponseEntity<String> saveData() {
        try {
            applicationFacade.saveDataToDatabase(); 
            return ResponseEntity.ok("Dane zostały zapisane pomyślnie.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Błąd podczas zapisywania danych.");
        }
    }
}
