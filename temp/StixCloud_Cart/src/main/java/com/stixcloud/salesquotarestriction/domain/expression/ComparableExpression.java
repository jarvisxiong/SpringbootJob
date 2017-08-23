package com.stixcloud.salesquotarestriction.domain.expression;


import com.stixcloud.salesquotarestriction.domain.Environment;

/**
 * Created by mango on 20/3/17.
 */
@FunctionalInterface
public interface ComparableExpression<V extends Comparable<? super V>> {

    V get(Environment environment);

}
