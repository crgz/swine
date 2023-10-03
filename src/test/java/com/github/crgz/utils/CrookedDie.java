package com.github.crgz.utils;

import com.github.crgz.dice.Die;

import java.util.Iterator;

import static com.github.crgz.GameTest.EXPECTED_OUTPUT;

public class CrookedDie implements Die {
    protected Iterator<Integer> iterator = Matches.get("^Die result: ([0-6]).*", EXPECTED_OUTPUT).stream()
            .map(Integer::valueOf)
            .toList()
            .iterator();

    public int roll() {
        return this.iterator.next();
    }
}

