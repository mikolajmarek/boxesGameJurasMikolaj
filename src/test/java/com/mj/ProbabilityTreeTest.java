package com.mj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mj.util.EmptyInputEception;
import com.mj.util.IncorrectNumberOfEvents;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityTreeTest {

    //@Before
    static final Map<Prize, Integer> TEST_MAP = new HashMap<>();

//        TEST_MAP.put(Prize.HUNDRED,1);
//        TEST_MAP.put(Prize.TWENTY,0);
//        TEST_MAP.put(Prize.FIVE,1);
////        eventMap.put(Prize.EXTRA_LIFE, 1);
//        TEST_MAP.put(Prize.GAME_OVER,1);

    ProbabilityTree probabilityTree = new ProbabilityTree();


    @Test
    public void shouldReturnFiveCombinationsForGivenGame() {
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 1);
        map.put(Prize.TWENTY, 1);
        map.put(Prize.GAME_OVER, 1);

        probabilityTree.generateTreeMap(map);

        Assert.assertEquals(5, probabilityTree.getProbabilityEvents().size());
    }

    @Test
    public void totalProbabilitySumMustAlwaysEqualOne() {
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 2);
        map.put(Prize.TWENTY, 2);
        map.put(Prize.GAME_OVER, 2);

        probabilityTree.generateTreeMap(map);



        double result = 0;
        result = probabilityTree.getProbabilityEvents().stream().mapToDouble(e -> e.getEventProbability()).sum();
        Assert.assertTrue(0.99D < Double.valueOf(result) && Double.valueOf(result) < 1.01D);
    }

    @Test(expected = EmptyInputEception.class)
    public void shouldThrowWmptyInputException() {

        Map<Prize, Integer> map = new HashMap<>();
        probabilityTree.generateTreeMap(map);
    }

    @Test(expected = IncorrectNumberOfEvents.class)
    public void shouldThrowEmptyInputException() {
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 3);
        map.put(Prize.TWENTY, 0);
        map.put(Prize.GAME_OVER, 2);

        probabilityTree.generateTreeMap(map);

    }

}