package com.mj;

import com.mj.ExpectedValue.Prize;
import com.mj.ExpectedValue.ProbabilityTree;
import org.junit.Assert;
import org.junit.Test;

import com.mj.util.EmptyInputMapException;
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

        probabilityTree.makeProbabilityTree(map);

        Assert.assertEquals(5, probabilityTree.getProbabilityEvents().size());
    }

    @Test
    public void totalProbabilitySumMustAlwaysEqualOne() {
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 3);
        map.put(Prize.TWENTY, 5);
        map.put(Prize.GAME_OVER, 4);

        probabilityTree.makeProbabilityTree(map);

        double result = 0;
        result = probabilityTree.getProbabilityEvents().stream().mapToDouble(e -> e.getEventProbability()).sum();
        Assert.assertTrue(0.999D < Double.valueOf(result) && Double.valueOf(result) < 1.001D);
    }
    @Test
    public void shouldReturn50ForBinaryGame (){
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 1);
        map.put(Prize.GAME_OVER, 1);

        probabilityTree.makeProbabilityTree(map);
        Double result = probabilityTree.getExpectedValue();

        Assert.assertEquals(Double.valueOf(50), Double.valueOf(result));
    }

    @Test(expected = EmptyInputMapException.class)
    public void shouldThrowWmptyInputException() {

        Map<Prize, Integer> map = new HashMap<>();
        probabilityTree.makeProbabilityTree(map);
    }

    @Test(expected = IncorrectNumberOfEvents.class)
    public void shouldThrowEmptyInputException() {
        Map<Prize, Integer> map = new HashMap<>();

        map.put(Prize.HUNDRED, 3);
        map.put(Prize.TWENTY, 0);
        map.put(Prize.GAME_OVER, 2);

        probabilityTree.makeProbabilityTree(map);

    }

}