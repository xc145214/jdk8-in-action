package com.github.xc145214.chapter3;

/**
 * Created by Administrator on 2017/4/12.
 */
@FunctionalInterface
public interface TriFunction<T,U,V,R> {
    R apply(T t,U u,V v);
}
