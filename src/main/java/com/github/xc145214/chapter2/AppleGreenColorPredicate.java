package com.github.xc145214.chapter2;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
