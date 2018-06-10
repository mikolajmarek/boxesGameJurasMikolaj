package com.mj;

import java.math.BigDecimal;
import java.util.List;

public class ProbabilityEvent {
    private List<Prize> listOfOpenedBoxes;

    public ProbabilityEvent(List<Prize> listOfOpenedBoxes) {
        this.listOfOpenedBoxes = listOfOpenedBoxes;
    }

//

    public List<Prize> getListOfOpenedBoxes() {
        return listOfOpenedBoxes;
    }

    private Double probabilityOfCurrentEvent;
//    private BigDecimal prize;
//    private boolean extraLife;



}
