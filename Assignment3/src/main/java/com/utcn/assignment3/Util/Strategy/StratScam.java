package com.utcn.assignment3.Util.Strategy;

public class StratScam implements RoundStrategy{

    @Override
    public Float justRound(Float nb) {
        if ((float) nb.intValue() == nb)
            return nb;
        return Integer.valueOf(Float.valueOf(nb + 1).intValue()).floatValue();
    }
}
