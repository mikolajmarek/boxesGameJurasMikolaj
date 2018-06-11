package com.mj;

import com.mj.util.EmptyInputEception;
import com.mj.util.IncorrectNumberOfEvents;
import com.mj.util.MissingGameOverEvent;

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


        if (checkInputMap(eventMap)) {
            generateTreeLevel(eventMap, openedBoxes, probabilityOfEvent);
        }


    }

    public void generateTreeLevel(Map<Prize, Integer> map, List<Prize> openedBoxes, double probabilityOfEvent) {
//        Iterator it = eventMap.entrySet().iterator();

        for (Iterator<Map.Entry<Prize, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {

            double currentProbability = 1F;
            double amountOfAvailableEvent = 0F;

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

            currentProbability = probabilityOfEvent * currentProbability*( entry.getValue() / amountOfAvailableEvent);

            if (!entry.getKey().equals(Prize.GAME_OVER)) {
                openedBoxesOnCurrentBranch.add(entry.getKey());
            } else {
                probabilityEvents.add(new ProbabilityEvent(openedBoxesOnCurrentBranch, currentProbability));
                continue;
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
                generateTreeLevel(innerMap, currentOpenedBoxesOnCurrentBranch, currentProbability);
            }

        }
    }
}
