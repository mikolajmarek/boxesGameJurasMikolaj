package com.mj;

import java.math.BigDecimal;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        List<Prize> listOfEvents = new LinkedList<>();

        Map<Prize, Integer> eventMap = new HashMap<>();
        eventMap.put(Prize.HUNDRED, 1);
        eventMap.put(Prize.TWENTY, 1);
        eventMap.put(Prize.FIVE, 1);
//        eventMap.put(Prize.EXTRA_LIFE, 1);
        eventMap.put(Prize.GAME_OVER, 1);


        ProbabilityTree probabilityTree = new ProbabilityTree();
        probabilityTree.generateTreeLevel(eventMap, listOfEvents);

//probabilityTree.getProbabilityEvents().stream().forEach(e-> e.getListOfOpenedBoxes());

        System.out.println("amount of combinations: " + probabilityTree.getProbabilityEvents().size());

        probabilityTree.getProbabilityEvents().stream()
                .forEach(r -> {
                    r.getListOfOpenedBoxes().stream().forEach(f -> System.out.print(f + ";"));
                    System.out.println();
                });


    }


//               Map.Entry pair = (Map.Entry)it.next();
//               listOfEvents.add(new ProbabilityEvent(pair.getKey(), pair.getValue());


}
