package com.example.space.event;

import java.util.HashMap;

public class PersonPassed {
    private Long gateId;
    private String fromId;
    private String toId;

    public PersonPassed() {
    }

    public PersonPassed(HashMap<String,Object> map) {
        this.gateId = Long.valueOf((Integer) map.get("gateId"));
        this.fromId = (String) map.get("fromId");
        this.toId = (String) map.get("toId");
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
