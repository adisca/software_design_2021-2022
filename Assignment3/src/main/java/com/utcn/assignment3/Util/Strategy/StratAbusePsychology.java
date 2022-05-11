package com.utcn.assignment3.Util.Strategy;

public class StratAbusePsychology implements RoundStrategy {

    @Override
    public Float justRound(Float nb) {
        if ((float) nb.intValue() == nb)
            return (nb - 0.01f);
        String text = nb.toString();
        text = text.substring(0, text.length() - 1) + "9";
        return Float.valueOf(text);
    }
}
