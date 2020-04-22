package com.example.space.event;


import java.util.HashMap;

public class GateCreated {
    private Long gateId;
    private String fromSpaceId;
    private String toSpaceId;


    public GateCreated() {
    }

    public GateCreated(HashMap<String, Object> map) {
        this.gateId = Long.valueOf((Integer)map.get("gateId"));
        this.fromSpaceId = (String) map.get("fromSpaceId");
        this.toSpaceId = (String) map.get("toSpaceId");
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public String getFromSpaceId() {
        return fromSpaceId;
    }

    public void setFromSpaceId(String fromSpaceId) {
        this.fromSpaceId = fromSpaceId;
    }

    public String getToSpaceId() {
        return toSpaceId;
    }

    public void setToSpaceId(String toSpaceId) {
        this.toSpaceId = toSpaceId;
    }
}
