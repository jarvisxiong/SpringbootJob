package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mango on 20/3/17.
 */
public class ConstantExpression<T extends Comparable<? super T>> implements TypedValueExpression<T>, Serializable {

    private static final long serialVersionUID = 5279832523095827293L;

    private T value;

    public ConstantExpression(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public T getValue(Environment environment) {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstantExpression<?> that = (ConstantExpression<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
