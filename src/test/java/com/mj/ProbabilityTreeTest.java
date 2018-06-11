package com.mj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mj.util.EmptyInputEception;
import com.mj.util.IncorrectNumberOfEvents;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityTreeTest {

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

        map.put(Prize.HUNDRED, 3);
        map.put(Prize.TWENTY, 5);
        map.put(Prize.GAME_OVER, 4);

        probabilityTree.generateTreeMap(map);

        double result = 0;
        result = probabilityTree.getProbabilityEvents().stream().mapToDouble(e -> e.getEventProbability()).sum();
        Assert.assertTrue(0.999D < Double.valueOf(result) && Double.valueOf(result) < 1.001D);
    }
    @Test
    public void shouldReturn50ForBinaryGame (){
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 1);
        map.put(Prize.GAME_OVER, 1);

        probabilityTree.generateTreeMap(map);
        Double result = probabilityTree.getExpectedValue();

        Assert.assertEquals(Double.valueOf(50), Double.valueOf(result));
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