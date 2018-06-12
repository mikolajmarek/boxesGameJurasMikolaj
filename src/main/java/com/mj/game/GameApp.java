package com.mj.game;

import com.mj.ExpectedValue.Prize;
import com.mj.ExpectedValue.ProbabilityTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameApp {
    public static void main(String[] args) {

        Map<Prize, Integer> eventMap = new HashMap<>();
        eventMap.put(Prize.HUNDRED, 1);
        eventMap.put(Prize.TWENTY, 2);
        eventMap.put(Prize.FIVE, 5);
        eventMap.put(Prize.EXTRA_LIFE, 1);
        eventMap.put(Prize.GAME_OVER, 3);

        ProbabilityTree probabilityTree = new ProbabilityTree();
        probabilityTree.makeProbabilityTree(eventMap);
        OpeningBoxesGame openingBoxesGame = new OpeningBoxesGame();

        List<Prize> list = new LinkedList<>();
        list.addAll(openingBoxesGame.fillList(eventMap));

        int numberOfGames = 10000000;

        System.out.println("Simulation after 1e7 games: " + openingBoxesGame.rewardForManyGames(numberOfGames, list) / numberOfGames);
        System.out.println("Expected value of the Simulation: " + probabilityTree.getExpectedValue());


    }
}
