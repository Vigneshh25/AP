package roomallotment.service;


import roomallotment.model.Hall;

import java.util.HashMap;
import java.util.Map;

public class HallService {
    private Map<Integer, Hall> halls = new HashMap<>();

    public void addHall(Hall hall) {
        halls.put(hall.getHallId(), hall);
    }

    public Map<Integer, Hall> getHalls() {
        return halls;
    }
}
