package com.github.xc145214.chapter02;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleTest extends TestCase {

    /**
     * 行为参数化。
     *
     * @throws Exception
     */
    public void testApplePrint() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        List<Apple> heavyApples =
                AppleUtils.filterApples(inventory, new AppleHeavyWeightPredicate());

        AppleUtils.prettyPrintApple(heavyApples, new AppleSimpleFormatter());
        AppleUtils.prettyPrintApple(heavyApples, new AppleFancyFormatter());

        List<Apple> greenApples =
                AppleUtils.filterApples(inventory, new AppleGreenColorPredicate());

        AppleUtils.prettyPrintApple(greenApples, new AppleSimpleFormatter());
        AppleUtils.prettyPrintApple(greenApples, new AppleFancyFormatter());
    }

    /**
     * 内部类方式。
     *
     * @throws Exception
     */
    public void testAppleInnerClass() throws Exception {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //匿名内部类。
        List<Apple> heavyApples =
                AppleUtils.filterApples(inventory, new ApplePredicate() {
                    @Override
                    public boolean test(Apple apple) {
                        return apple.getWeight() > 150;
                    }
                });

        AppleUtils.prettyPrintApple(heavyApples, new AppleSimpleFormatter());
        AppleUtils.prettyPrintApple(heavyApples, new AppleFancyFormatter());
    }

    /***
     * lambda 方式。
     * @throws Exception
     */
    public void testAppleWithLambda() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //lambda表达式。
        List<Apple> heavyApples =
                AppleUtils.filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);

        AppleUtils.prettyPrintApple(heavyApples, new AppleSimpleFormatter());
        AppleUtils.prettyPrintApple(heavyApples, new AppleFancyFormatter());
    }


}
