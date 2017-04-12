package com.github.xc145214.chapter2;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleRedAndWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) &&
                apple.getWeight() > 150;
    }
}
