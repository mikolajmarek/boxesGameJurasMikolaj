package com.mj.ExpectedValue;

import java.util.*;

public class expectedValueApp {
    public static void main(String[] args) {

        List<Prize> listOfEvents = new LinkedList<>();

        Map<Prize, Integer> eventMap = new HashMap<>();
        eventMap.put(Prize.HUNDRED, 1);
        eventMap.put(Prize.TWENTY, 2);
        eventMap.put(Prize.FIVE, 5);
        eventMap.put(Prize.EXTRA_LIFE, 1);
        eventMap.put(Prize.GAME_OVER, 3);
//

        ProbabilityTree probabilityTree = new ProbabilityTree();
        probabilityTree.makeProbabilityTree(eventMap);

        System.out.println("amount of combinations: " + probabilityTree.getProbabilityEvents().size());

//********** Lambda to Display all combinations*******
//                    probabilityTree.getProbabilityEvents().stream()
//                    .forEach(r -> {
//                    r.getListOfOpenedBoxes().stream().forEach(f -> System.out.print(f + ";"));
//                    System.out.println(" probability = " + r.getEventProbability());
//                    System.out.println();
//                });

        System.out.println(probabilityTree.getExpectedValue());


    }
}
