package com.github.crgz.dice;

import java.util.Random;

public class FairDie implements Die {
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}
