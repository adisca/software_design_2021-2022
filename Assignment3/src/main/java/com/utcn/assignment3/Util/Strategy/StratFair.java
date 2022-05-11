package com.utcn.assignment3.Util.Strategy;

public class StratFair implements RoundStrategy{
    @Override
    public Float justRound(Float nb) {
        return nb;
    }
}
