package com.mj.game;

import com.mj.ExpectedValue.Prize;

import java.util.*;
import java.util.stream.Collectors;

public class OpeningBoxesGame {

    public List<Prize> fillList(Map<Prize, Integer> eventsMap) {
        List<Prize> list = new LinkedList<>();
        eventsMap.entrySet().stream().forEach(e -> {
            for (int i = 0; i < e.getValue(); i++) {
                list.add(e.getKey());

            }
        });

        return list;
    }

    private List<Prize> shuffle(List<Prize> list) {

        Collections.shuffle(list);
        return list;
    }

    private Double openBox(Prize prize) {
        int currentReward = 0;
        switch (prize) {
            case HUNDRED:
                currentReward = Prize.HUNDRED.value();
                break;
            case TWENTY:
                currentReward = Prize.TWENTY.value();
                break;
            case FIVE:
                currentReward = Prize.FIVE.value();
                break;
        }
        return Double.valueOf(currentReward);
    }

    public Double playTheGame(List<Prize> game) {
        List<Prize> shuffledList = shuffle(game);
        double reward = 0;
        boolean extraLife = false;

        for (Prize box : shuffledList) {
            switch (box) {
                case GAME_OVER:
                    if (!extraLife) {
                        return reward;
                    } else {
                        extraLife = false;
                    }
                    break;

                case EXTRA_LIFE:
                    extraLife = true;
                    break;

                default:
                    reward += openBox(box);
            }

        }

        return reward;


    }

    public double rewardForManyGames(int n, List<Prize> l){
        double reward = 0;
        for (int i = 0; i < n; i++) {
            reward+= playTheGame(l);

        }
        return reward;
    }
}
