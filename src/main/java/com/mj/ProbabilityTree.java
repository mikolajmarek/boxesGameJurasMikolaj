package com.mj;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProbabilityTree {
    private List<ProbabilityEvent> probabilityEvents = new LinkedList<>();


    public List<ProbabilityEvent> getProbabilityEvents() {
        return probabilityEvents;
    }

//    public void generateTreeMap(Map<Prize, Integer> eventMap) {
//        List<Prize> openedBoxes = new LinkedList<>();
//        if (!eventMap.isEmpty()) {
//            generateTreeLevel(eventMap, openedBoxes);
//        }
//    }

    public void generateTreeLevel(Map<Prize, Integer> map, List<Prize> openedBoxes) {
//        Iterator it = eventMap.entrySet().iterator();

        for (Iterator<Map.Entry<Prize, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Double currentProbability;
            Map<Prize, Integer> eventMap;
            eventMap = map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

            List<Prize> openedBoxesOnCurrentBranch = new LinkedList<>();
            openedBoxesOnCurrentBranch = openedBoxes.stream().collect(Collectors.toList());
//            it.remove();
            Map.Entry<Prize, Integer> entry = it.next();
            Map<Prize, Integer> innerMap;
            innerMap = eventMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

            if (!entry.getKey().equals(Prize.GAME_OVER)) {
                openedBoxesOnCurrentBranch.add(entry.getKey());

            } else {
                probabilityEvents.add(new ProbabilityEvent(openedBoxesOnCurrentBranch));

            }
            if (entry.getValue() - 1 >= 1) {
                innerMap.put(entry.getKey(), entry.getValue() - 1);
            } else {
//                innerMap.put(entry.getKey(), entry.getValue() - 1);
                innerMap.remove(entry.getKey(), entry.getValue());
            }
            List<Prize> currentOpenedBoxesOnCurrentBranch;
            currentOpenedBoxesOnCurrentBranch = openedBoxesOnCurrentBranch.stream().collect(Collectors.toList());
            if (!innerMap.isEmpty()) {
                generateTreeLevel(innerMap, currentOpenedBoxesOnCurrentBranch);
            }

        }
    }
}
