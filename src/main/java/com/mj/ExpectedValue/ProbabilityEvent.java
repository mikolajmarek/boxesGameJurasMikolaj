package com.mj.ExpectedValue;

import java.util.List;

public class ProbabilityEvent {
    private List<Prize> listOfOpenedBoxes;
    private double eventProbability;

    public double getEventProbability() {
        return eventProbability;
    }

    public ProbabilityEvent(List<Prize> listOfOpenedBoxes, double eventProbability) {
        this.listOfOpenedBoxes = listOfOpenedBoxes;
        this.eventProbability = eventProbability;

    }

    public List<Prize> getListOfOpenedBoxes() {
        return listOfOpenedBoxes;
    }

}
