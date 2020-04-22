package com.example.space.event;

import java.util.HashMap;

public class PopulationChanged {
    private String spaceId;
    private Integer population;

    public PopulationChanged() {
    }

    public PopulationChanged(HashMap<String, Object> map) {
        this.spaceId = (String) map.get("spaceId");
        this.population = (Integer) map.get("population");
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
