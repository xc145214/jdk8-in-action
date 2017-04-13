package com.github.xc145214.chapter03;

import com.github.xc145214.chapter02.Apple;
import com.github.xc145214.chapter02.AppleComparator;
import com.github.xc145214.chapter02.AppleSimpleFormatter;
import com.github.xc145214.chapter02.AppleUtils;
import junit.framework.TestCase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * Created by Administrator on 2017/4/12.
 */
public class LambdaTest extends TestCase {

    /**
     * 构造函数引用。
     *
     * @throws Exception
     */
    public void testAppleConstructor() throws Exception {

        Supplier<Apple> c1 = Apple::new;
        Apple apple1 = c1.get();

        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(110);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("greem", 110);
    }


    public void testColorConstructor() throws Exception {
        TriFunction<Integer, Integer, Integer, Color> colorFactory
                = Color::new;
        Color color = colorFactory.apply(111, 122, 223);
    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
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

    /**
     * 逆序排序。
     *
     * @throws Exception
     */
    public void testAppleComparatorReverse() throws Exception {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(comparing(Apple::getWeight).reversed());//逆序
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));//逆序
        AppleUtils.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }


    /**
     * letter 流水线检查
     *
     * @throws Exception
     */
    public void testLetterCheck1() throws Exception {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        String letter = transformationPipeline.apply("this is a letter,labda !!");

        System.out.println(letter);
    }

    /**
     * 加头尾。
     *
     * @throws Exception
     */
    public void testLetterCheck2() throws Exception {

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::addFooter);

        String letter = transformationPipeline.apply("this is a letter,labda !!");

        System.out.println(letter);
    }

    public double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public void testDoubleMath() throws Exception {
        System.out.println(integrate((double x) -> x + 10, 3, 7));
    }
}
