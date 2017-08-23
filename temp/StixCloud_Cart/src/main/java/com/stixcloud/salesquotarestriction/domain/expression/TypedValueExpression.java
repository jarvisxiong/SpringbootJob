package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

/**
 * Created by mango on 22/3/17.
 */
@FunctionalInterface
public interface TypedValueExpression<T> extends Expression {
    T getValue(Environment environment);
}
