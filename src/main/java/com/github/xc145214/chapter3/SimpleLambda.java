package com.github.xc145214.chapter3;

/**
 * Created by Administrator on 2017/4/12.
 */
public class SimpleLambda {
    public static void process (Runnable r){
        r.run();
    }
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello World 1");
        Runnable r2 = new Runnable() {
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

    }
}
