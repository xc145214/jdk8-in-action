package com.github.xc145214.chapter04;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2017/4/12.
 */
public class DishTest extends TestCase {

    List<Dish> menu;

    @Override
    public void setUp() throws Exception {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @Test
    public void testDishOld() throws Exception {


        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        System.out.println(lowCaloricDishesName);
    }

    /**
     *
     * java8 流方式处理。
     * @throws Exception
     */
    public void testDishNew() throws Exception {
        List<String> lowCaloricDishesName =
                menu.stream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    /**
     * 利用多核架构。
     * @throws Exception
     */
    public void testDishOther() throws Exception {
        List<String> lowCaloricDishesName =
                menu.parallelStream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    public void testDishDetail() throws Exception {
        List<String> lowCaloricDishesName =
                menu.parallelStream()
                        .filter(d ->
                        {
                            System.out.println("filtering " + d.getName());
                           return d.getCalories() < 400;
                        })
                        .sorted(comparing(Dish::getCalories))
                        .map(d -> {
                            System.out.println("mapping " + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(toList());

        System.out.println(lowCaloricDishesName);
    }

    public void testStringStream() throws Exception {
       List<String> titles = Arrays.asList("java8","in","action");
        Stream<String> s = titles.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }
}
