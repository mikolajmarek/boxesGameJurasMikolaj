package com.mj;

import java.math.BigDecimal;
import java.util.List;

public class ProbabilityEvent {
    private List<Prize> listOfOpenedBoxes;
    private Float eventProbability;

    public Float getEventProbability() {
        return eventProbability;
    }

    public ProbabilityEvent(List<Prize> listOfOpenedBoxes, Float eventProbability) {
        this.listOfOpenedBoxes = listOfOpenedBoxes;
        this.eventProbability = eventProbability;

    }

//

    public List<Prize> getListOfOpenedBoxes() {
        return listOfOpenedBoxes;
    }


//    private BigDecimal prize;
//    private boolean extraLife;



}
