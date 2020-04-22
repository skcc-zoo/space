package com.example.space;

import com.example.space.event.CommonEvent;
import com.example.space.event.PopulationChanged;
import com.example.space.event.SpaceCreated;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import javax.persistence.*;

@Entity
public class Space {
    @Id
    private String id;
    private Integer population;

    @PostPersist
    public void publishSpaceCreatedEvent() {

        SpaceCreated spaceCreated = new SpaceCreated();
        spaceCreated.setSpaceId(this.id);
        CommonEvent commonEvent = new CommonEvent(SpaceCreated.class.getSimpleName(), spaceCreated);
        commonEvent.publish();
        System.out.println("spaceCreated event published.");
    }

    @PostUpdate
    public void publishPopulationChangedEvent() {
        PopulationChanged populationChanged = new PopulationChanged();
        populationChanged.setSpaceId(this.id);
        populationChanged.setPopulation(this.population);
        CommonEvent commonEvent = new CommonEvent(PopulationChanged.class.getSimpleName(), populationChanged);
        commonEvent.publish();
        System.out.println("populationChanged event published.");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
