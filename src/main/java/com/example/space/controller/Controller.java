package com.example.space.controller;

import com.example.space.Space;
import com.example.space.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/space")
@RestController
public class Controller {

    final Integer MAX_POPULATION = 100;

    @Autowired
    SpaceRepository spaceRepository;

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
