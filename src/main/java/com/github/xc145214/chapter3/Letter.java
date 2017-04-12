package com.github.xc145214.chapter3;

/**
 * Created by Administrator on 2017/4/12.
 */
public class Letter {

    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }
}
