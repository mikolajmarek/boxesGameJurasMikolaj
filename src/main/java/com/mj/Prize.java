package com.mj;

public enum Prize {
    HUNDRED (100),
    TWENTY (20),
    FIVE (5),
    EXTRA_LIFE (0),
    GAME_OVER(0);

    private final int prizeValue;
    private Prize(int prizeValue) {
        this.prizeValue = prizeValue;
    }

    private int value() { return prizeValue; }
}