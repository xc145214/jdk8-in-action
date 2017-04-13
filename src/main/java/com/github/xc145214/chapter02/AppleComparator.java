package com.github.xc145214.chapter02;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/4/12.
 */
public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
