package com.mj.ExpectedValue;

import com.mj.util.EmptyInputEception;
import com.mj.util.IncorrectNumberOfEvents;
import com.mj.util.MissingGameOverEvent;
import com.mj.util.NoProbabilityEventsToEvaluate;

import java.util.*;
import java.util.stream.Collectors;

public class ProbabilityTree {
    private List<ProbabilityEvent> probabilityEvents = new LinkedList<>();


    public List<ProbabilityEvent> getProbabilityEvents() {
        return probabilityEvents;
    }

    private boolean checkInputMap(Map<Prize, Integer> map) {
        if (map.isEmpty()) {
            throw new EmptyInputEception("empty input");
        } else if (!map.containsKey(Prize.GAME_OVER)) {

            throw new MissingGameOverEvent("No end of the game conditions");
            //to be added conditions where number of extra life events < number of game over events
        }
        for (Integer value : map.values()) {
            if (value < 1) {
                throw new IncorrectNumberOfEvents("number of events must be positive integer");
            }
        }

        return true;
    }

    public void generateTreeMap(Map<Prize, Integer> eventMap) {
        List<Prize> openedBoxes = new LinkedList<>();
        double probabilityOfEvent = 1D;
        boolean extraLife = false;


        if (checkInputMap(eventMap)) {
            generateTreeLevel(eventMap, openedBoxes, probabilityOfEvent, extraLife);
        }
    }

    public Double getExpectedValue() {
        Double sum = 0D;
        if (probabilityEvents.isEmpty()) {
            throw new NoProbabilityEventsToEvaluate("run the experiment first then evaluate");
        } else {
            for (ProbabilityEvent events : probabilityEvents) {
                Double valueOfPrizes = events.getListOfOpenedBoxes().stream().mapToDouble(e -> Double.valueOf(e.value())).sum();
                sum += valueOfPrizes * events.getEventProbability();
            }
        }
        return sum;
    }

    private void generateTreeLevel(Map<Prize, Integer> map, List<Prize> openedBoxes, double probabilityOfEvent, boolean extraLife) {
//        Iterator it = eventMap.entrySet().iterator();

        for (Iterator<Map.Entry<Prize, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {

            double currentProbability = 1F;
            double amountOfAvailableEvent = 0F;
            boolean innerExtraLife = false;

            Map<Prize, Integer> eventMap;
            eventMap = map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
            for (Integer value : eventMap.values()) {
                amountOfAvailableEvent += value;
            }
            List<Prize> openedBoxesOnCurrentBranch;
            openedBoxesOnCurrentBranch = openedBoxes.stream().collect(Collectors.toList());

            Map.Entry<Prize, Integer> entry = it.next();
            Map<Prize, Integer> innerMap;
            innerMap = eventMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

            currentProbability = probabilityOfEvent * currentProbability * (entry.getValue() / amountOfAvailableEvent);

            if (!entry.getKey().equals(Prize.GAME_OVER)) {
                openedBoxesOnCurrentBranch.add(entry.getKey());
            } else {
                if (openedBoxesOnCurrentBranch.contains(Prize.EXTRA_LIFE)){
                    openedBoxesOnCurrentBranch.remove(openedBoxesOnCurrentBranch.indexOf(Prize.EXTRA_LIFE));
                    openedBoxesOnCurrentBranch.add(entry.getKey());
                } else {
                    probabilityEvents.add(new ProbabilityEvent(openedBoxesOnCurrentBranch, currentProbability));
                    continue;
                }
            }
            if (entry.getValue() - 1 >= 1) {
                innerMap.put(entry.getKey(), entry.getValue() - 1);
            } else {
//                innerMap.put(entry.getKey(), entry.getValue() - 1);
                innerMap.remove(entry.getKey(), entry.getValue());
            }


//
//            if (!entry.getKey().equals(Prize.GAME_OVER)) {
//                openedBoxesOnCurrentBranch.add(entry.getKey());
//            } else {
//                probabilityEvents.add(new ProbabilityEvent(openedBoxesOnCurrentBranch, currentProbability));
//                continue;
//
//            }
////

            if (entry.getValue() - 1 >= 1) {
                innerMap.put(entry.getKey(), entry.getValue() - 1);
            } else {
                innerMap.remove(entry.getKey(), entry.getValue());
            }
            List<Prize> currentOpenedBoxesOnCurrentBranch;
            currentOpenedBoxesOnCurrentBranch = openedBoxesOnCurrentBranch.stream().collect(Collectors.toList());
            if (!innerMap.isEmpty()) {
                generateTreeLevel(innerMap, currentOpenedBoxesOnCurrentBranch, currentProbability, innerExtraLife);
            }

        }
    }
}
