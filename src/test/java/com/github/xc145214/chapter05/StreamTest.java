package com.github.xc145214.chapter05;

import com.github.xc145214.chapter04.Dish;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2017/4/13.
 */
public class StreamTest extends TestCase {
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

    /**
     * 谓词筛选。
     *
     * @throws Exception
     */
    public void testStream1() throws Exception {
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianMenu);
    }

    /**
     * 筛选各异的元素.
     *
     * @throws Exception
     */
    public void testDistinct() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 截短流.
     */
    public void testLimit() throws Exception {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);
    }

    /**
     * 跳过元素。
     *
     * @throws Exception
     */
    public void testSkip() throws Exception {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes);
    }

    /**
     * 筛选荤菜。
     *
     * @throws Exception
     */
    public void testFilterAndLimit() throws Exception {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
        System.out.println(dishes);
    }

    public void testMap() throws Exception {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);
    }

    public void testWord() throws Exception {
        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<String> uniqueCharacters =
                streamOfwords
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(uniqueCharacters);
    }

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表.
     *
     * @throws Exception
     */
    public void testMath() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares =
                numbers.stream()
                        .map(n -> n * n)
                        .collect(toList());
        System.out.println(squares);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对
     *
     * @throws Exception
     */
    public void testNumbers() throws Exception {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        for (int[] arr : pairs) {
            Arrays.stream(arr).forEach(System.out::print);
            System.out.println();
        }
    }

    public void testNumbers2() throws Exception {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        for (int[] arr : pairs) {
            Arrays.stream(arr).forEach(System.out::print);
            System.out.println();
        }
    }

    public void testOptional() throws Exception {
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish);

        menu.stream().filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst();
        System.out.println(firstSquareDivisibleByThree);
    }

    public void testSum() throws Exception {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = nums.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        Optional<Integer> sumNum = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream().reduce((a, b) -> a + b);
        System.out.println(sumNum);
    }

    public void testMaxAndMin() throws Exception {
        List<Integer> nums = Arrays.asList(4, 5, 3, 9);
        Optional<Integer> min = nums.stream().reduce(Integer::min);
        Optional<Integer> max = nums.stream().reduce(Integer::max);

        System.out.println("min: " + min.get());
        System.out.println("max: " + max.get());
    }


    public void testCount() throws Exception {
        List<Integer> nums = Arrays.asList(4, 5, 3, 9);
        int count1 = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        long count2 = menu.stream().count();

        System.out.println("count1: " + count1);
        System.out.println("count2: " + count2);
    }

    /**
     * 卡路里合计
     *
     * @throws Exception
     */
    public void testCalories() throws Exception {
        //潜在装箱
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        System.out.println(calories);

        //转换数值流
        calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        System.out.println(calories);

        calories = menu.stream()
                .mapToInt(Dish::getCalories)    //拆箱
                .boxed()                        //装箱
                .reduce(0, Integer::sum);

        System.out.println(calories);

        //最大值
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        System.out.println(maxCalories.getAsInt());

        int max = maxCalories.orElse(1);

        System.out.println(max);

    }

    /**
     * 数值范围
     *
     * @throws Exception
     */
    public void testNumberRange() throws Exception {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count());
    }

    /**
     * 勾股数
     *
     * @throws Exception
     */
    public void testTriNum() throws Exception {

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples.limit(10)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriples2
                = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );
        pythagoreanTriples2
                .limit(10)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


    }

    /**
     * 值创建流
     *
     * @throws Exception
     */
    public void testCreateByValue() throws Exception {

        Stream<String> stream = Stream.of("Java 8", "Lambadas", "in", "action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * 数组生成流
     *
     * @throws Exception
     */
    public void testCreateByNumber() throws Exception {
        int[] numbers = {2, 3, 4, 5, 6, 7, 11};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    /**
     * 函数生成流
     *
     * @throws Exception
     */
    public void testCreateByFunction() throws Exception {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 斐波那契数列
     * @throws Exception
     */
    public void testNumberArray() throws Exception {
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .forEach(
                        t -> System.out.println("(" + t[0]+ ", " + t[1] + ")")
                );

        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }


}
