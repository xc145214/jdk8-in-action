package com.github.xc145214.chapter05;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TradeTest extends TestCase {
    Trader raoul;
    Trader mario;
    Trader alan;
    Trader brian;
    List<Transaction> transactions;

    @Override
    public void setUp() throws Exception {
        raoul = new Trader("Raoul", "Cambridge");
        mario = new Trader("Mario", "Milan");
        alan = new Trader("Alan", "Cambridge");
        brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     *
     * @throws Exception
     */
    public void test1() throws Exception {
        List<Transaction> tran2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(tran2011);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     *
     * @throws Exception
     */
    public void test2() throws Exception {
        List<String> cities1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList());

        System.out.println(cities1);

        Set<String> cities2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());

        System.out.println(cities2);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序.
     *
     * @throws Exception
     */
    public void test3() throws Exception {
        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     *
     * @throws Exception
     */
    public void test4() throws Exception {
        String names1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println(names1);

        String names2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        System.out.println(names2);
    }

    /**
     * 有没有交易员在米兰工作的。
     *
     * @throws Exception
     */
    public void test5() throws Exception {
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                .getCity()
                .equals("Milan"));

        System.out.println(milanBased);

    }

    /**
     *  打印生活在剑桥的交易员的所有交易额
     *
     */
    public void test6(){

        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * 所有交易中交易额最高的金额
     */
    public void test7(){

        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(highestValue.get());
    }

    /**
     * 找到最小的交易
     *
     */
    public void test8(){
        Optional<Transaction> smallestTranscation = transactions.stream()
                .reduce((t1, t2) ->
                        t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println(smallestTranscation.get().getValue());

        Optional<Transaction>  minTranscation  = transactions.stream()
                .min(comparing(Transaction::getValue));

        System.out.println(minTranscation.get().getValue());
    }
}
