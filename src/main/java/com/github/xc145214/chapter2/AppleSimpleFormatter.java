package com.github.xc145214.chapter2;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
