package com.github.xc145214.chapter2;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

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

    /**
     * 传递代码。
     *
     * @throws Exception
     */
    public void testAppleComparator() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(new AppleComparator());
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    /**
     * inner class.
     *
     * @throws Exception
     */
    public void testAppleComparatorInnerClass() throws Exception {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public void testAppleComparatorLambda1() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort((Apple a1, Apple a2)
                -> a1.getWeight().compareTo(a2.getWeight())
        );
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public void testAppleComparatorLambda2() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort((a1, a2)
                -> a1.getWeight().compareTo(a2.getWeight())
        );
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public void testAppleComparatorLambda3() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        Comparator<Apple> appleComparator = comparing((Apple a) -> a.getWeight());
        inventory.sort(appleComparator);
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public void testAppleComparatorLambda4() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));


        inventory.sort(comparing((Apple a) -> a.getWeight()));
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    public void testAppleComparatorLambda5() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(comparing(Apple::getWeight));
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }
}
