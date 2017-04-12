package com.github.xc145214.chapter2;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {

    /**
     *
     * @param apple
     * @return
     */
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >150;
    }
}
