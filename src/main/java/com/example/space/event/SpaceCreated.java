package com.example.space.event;

import java.util.HashMap;

public class SpaceCreated {
    private String spaceId;

    public SpaceCreated() {
    }

    public SpaceCreated(HashMap<String, Object> map) {
        this.spaceId = (String) map.get("spaceId");
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }
}
