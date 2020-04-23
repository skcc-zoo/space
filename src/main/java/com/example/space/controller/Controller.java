package com.example.space.controller;

import com.example.space.Space;
import com.example.space.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RequestMapping("/space")
@RestController
public class Controller {

    private Integer MAX_POPULATION;

    @Autowired
    SpaceRepository spaceRepository;

    @PostConstruct
    public void setMAX_POPULATION() {
        try {

            this.MAX_POPULATION = Integer.valueOf(System.getenv("MAX_POPULATION"));
            System.out.println("MAX_POPULATION set by env.");
        } catch (Exception e) {
            e.printStackTrace();
            this.MAX_POPULATION = 100;
            System.out.println("MAX_POPULATION set by default.");
        }
    }

    @GetMapping("/max_population")
    public Integer getMAX_POPULATION() {
        return this.MAX_POPULATION;
    }

    @GetMapping("/permission")
    public boolean checkMaxPopulation() {
        Iterable<Space> spaces = spaceRepository.findAll();
        Integer currentPopulation = 0;

        for (Space space : spaces) {
            if(space.getPopulation() > 0) {
                currentPopulation = currentPopulation + space.getPopulation();
            }
        }

        return currentPopulation <= MAX_POPULATION;
    }

    @GetMapping("/current_population")
    public Integer getCurrentPopulation() {
        Iterable<Space> spaces = spaceRepository.findAll();

        Integer currentPopulation = 0;

        for (Space space : spaces) {
            currentPopulation = currentPopulation + space.getPopulation();
        }

        return currentPopulation;
    }
}
