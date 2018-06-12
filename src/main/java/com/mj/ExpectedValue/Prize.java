package com.mj.ExpectedValue;

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

    public int value() { return prizeValue; }
}
