package com.github.xc145214.chapter02;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic +
                " " + apple.getColor() + " apple";
    }
}
