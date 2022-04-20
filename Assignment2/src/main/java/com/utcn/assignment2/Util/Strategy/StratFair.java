package com.utcn.assignment2.Util.Strategy;

public class StratFair implements RoundStrategy{
    @Override
    public Float justRound(Float nb) {
        return nb;
    }
}
