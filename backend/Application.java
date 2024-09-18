package com.zbyszkobud;

import com.zbyszkobud.service.ApplicationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    private ApplicationFacade applicationFacade;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        // Inicjalizacja danych po uruchomieniu aplikacji
        applicationFacade.initializeData();

        // Wyświetlenie ilości załadowanych projektów
        System.out.println("Liczba projektów w bazie: " + applicationFacade.getProjects().size());

        // Dodanie danych startowych, jeśli baza jest pusta
        System.out.println("Ilość projektów: " + applicationFacade.getProjects());
        if (applicationFacade.getProjects().isEmpty()) {
            applicationFacade.initializeStartData();
            System.out.println("Dane startowe zostały dodane.");
        }
    }
}
