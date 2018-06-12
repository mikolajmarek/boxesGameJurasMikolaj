package com.mj.game;

import com.mj.ExpectedValue.Prize;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OpeningBoxesGameTest {

@Test
    public void shouldReturnCorrectListFromMap () {

    Map<Prize, Integer> map = new HashMap<>();
    map.put(Prize.HUNDRED, 4);
    map.put(Prize.GAME_OVER, 1);

    OpeningBoxesGame openingBoxesGame = new OpeningBoxesGame();

    Assert.assertEquals(5, openingBoxesGame.fillList(map).size());
    Assert.assertEquals(4, openingBoxesGame.fillList(map).stream().filter(e->e.equals(Prize.HUNDRED)).toArray().length);
}

@Test
    public void shouldStartTheGameAndReturnZeroForGameOverOnly() {
    OpeningBoxesGame openingBoxesGame = new OpeningBoxesGame();

    List<Prize> list = new ArrayList<>(5);
    list.add(Prize.GAME_OVER);
    list.add(Prize.GAME_OVER);
    list.add(Prize.GAME_OVER);
    list.add(Prize.GAME_OVER);

    double result = openingBoxesGame.playTheGame(list);

    Assert.assertTrue(result == 0D);
}

    @Test
    public void shouldStartTheGameAndReturnPositiveValueOrZero() {
        OpeningBoxesGame openingBoxesGame = new OpeningBoxesGame();

        List<Prize> list = new ArrayList<>(5);
        list.add(Prize.FIVE);
        list.add(Prize.TWENTY);
        list.add(Prize.GAME_OVER);
        list.add(Prize.GAME_OVER);

        double result = openingBoxesGame.playTheGame(list);

        Assert.assertTrue(result >= 0D);
    }

}