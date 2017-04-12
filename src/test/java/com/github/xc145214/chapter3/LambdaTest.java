package com.github.xc145214.chapter3;

import com.github.xc145214.chapter2.Apple;
import junit.framework.TestCase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
}
