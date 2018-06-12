package com.mj.ExpectedValue;

import com.mj.util.EmptyInputMapException;
import com.mj.util.IncorrectNumberOfEvents;
import com.mj.util.MissingGameOverEvent;
import com.mj.util.NoProbabilityEventsToEvaluate;

import java.util.*;

public class ProbabilityTree {
    private List<ProbabilityEvent> probabilityEvents = new LinkedList<>();


    public List<ProbabilityEvent> getProbabilityEvents() {
        return probabilityEvents;
    }

    private boolean checkInputMap(Map<Prize, Integer> map) {
        if (map.isEmpty()) {
            throw new EmptyInputMapException("empty input");
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

    public void makeProbabilityTree(Map<Prize, Integer> eventMap) {

        List<Prize> openedBoxes = new LinkedList<>();
        double probabilityOfEvent = 1D;
        boolean extraLife = false;

        if (checkInputMap(eventMap)) {
            makeTreeLevel(eventMap, openedBoxes, probabilityOfEvent, extraLife);
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

    private void makeTreeLevel(Map<Prize, Integer> map, List<Prize> openedBoxes, double probabilityOfEvent, boolean extraLife) {

        for (Map.Entry<Prize, Integer> prizeIntegerEntry : map.entrySet()) {

            double currentProbability = 1F;
            double amountOfAvailableEvent = 0F;
            boolean innerExtraLife = false;

            Map<Prize, Integer> eventMap = new HashMap<>();
            eventMap.putAll(map);

            for (Integer value : eventMap.values()) {
                amountOfAvailableEvent += value;
            }
            List<Prize> openedBoxesOnCurrentBranch = new LinkedList<>();
            openedBoxesOnCurrentBranch.addAll(openedBoxes);

            currentProbability = probabilityOfEvent * currentProbability * (prizeIntegerEntry.getValue() / amountOfAvailableEvent);

            if (!prizeIntegerEntry.getKey().equals(Prize.GAME_OVER)) {
                openedBoxesOnCurrentBranch.add(prizeIntegerEntry.getKey());
            } else {
                if (openedBoxesOnCurrentBranch.contains(Prize.EXTRA_LIFE)) {
                    openedBoxesOnCurrentBranch.remove(openedBoxesOnCurrentBranch.indexOf(Prize.EXTRA_LIFE));
                    openedBoxesOnCurrentBranch.add(prizeIntegerEntry.getKey());
                } else {
                    probabilityEvents.add(new ProbabilityEvent(openedBoxesOnCurrentBranch, currentProbability));
                    continue;
                }
            }

            if (prizeIntegerEntry.getValue() - 1 >= 1) {
                eventMap.put(prizeIntegerEntry.getKey(), prizeIntegerEntry.getValue() - 1);
            } else {
                eventMap.remove(prizeIntegerEntry.getKey(), prizeIntegerEntry.getValue());
            }
            List<Prize> currentOpenedBoxesOnCurrentBranch = new LinkedList<>();
            currentOpenedBoxesOnCurrentBranch.addAll(openedBoxesOnCurrentBranch);

            if (!eventMap.isEmpty()) {
                makeTreeLevel(eventMap, currentOpenedBoxesOnCurrentBranch, currentProbability, innerExtraLife);
            }

        }
    }
}
