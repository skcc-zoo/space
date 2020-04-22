package com.example.space;

import com.example.space.event.CommonEvent;
import com.example.space.event.GateCreated;
import com.example.space.event.PersonPassed;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.io.IOException;


@Service
public class KafkaListener {

    @Autowired
    private SpaceRepository spaceRepository;

    @StreamListener(Processor.INPUT)
    public void onEvent(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CommonEvent event = null;

        try {
            event = objectMapper.readValue(message, CommonEvent.class);

            if (event.getEventType().equals("PersonPassed")) {
                PersonPassed personPassed = new PersonPassed((HashMap<String, Object>) event.getContent());

                // 사람이 나온 스페이스의 인원수 감소
                Optional<Space> optionalFromSpace = spaceRepository.findById(personPassed.getFromId());
                Space fromSpace = optionalFromSpace.get();
                fromSpace.setPopulation(fromSpace.getPopulation() - 1);
                spaceRepository.save(fromSpace);

                // 사람이 들어간 스페이스의 인원수 추가
                Optional<Space> optionalToSpace = spaceRepository.findById(personPassed.getToId());
                Space toSpace = optionalToSpace.get();
                toSpace.setPopulation(toSpace.getPopulation() + 1);
                spaceRepository.save(toSpace);
                System.out.println("personPassedEvent processed.");
            } else if (event.getEventType().equals("GateCreated")) {
                GateCreated gateCreated = new GateCreated((HashMap<String, Object>) event.getContent());

                // From space 생성
                Optional<Space> optionalFromSpace = spaceRepository.findById(gateCreated.getFromSpaceId());
                if (!optionalFromSpace.isPresent()) {
                    Space space = new Space();
                    space.setId(gateCreated.getFromSpaceId());
                    space.setPopulation(0);
                    spaceRepository.save(space);
                }

                // To space 생성
                Optional<Space> optionalToSpace = spaceRepository.findById(gateCreated.getToSpaceId());
                if (!optionalToSpace.isPresent()) {
                    Space space = new Space();
                    space.setId(gateCreated.getToSpaceId());
                    space.setPopulation(0);
                    spaceRepository.save(space);
                }
                System.out.println("gateCreated event processed.");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
