package com.utcn.assignment2.Util.Strategy;

import java.util.Objects;

public class StratScam implements RoundStrategy{

    @Override
    public Float justRound(Float nb) {
        if ((float) nb.intValue() == nb)
            return nb;
        return Integer.valueOf(Float.valueOf(nb + 1).intValue()).floatValue();
    }
}
