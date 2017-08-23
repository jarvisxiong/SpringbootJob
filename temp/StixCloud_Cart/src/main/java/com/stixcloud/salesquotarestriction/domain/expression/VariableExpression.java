package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mango on 20/3/17.
 */
public class VariableExpression<T extends Comparable<? super T>> implements TypedValueExpression<T>, Serializable {

    private static final long serialVersionUID = 1381792284172281788L;

    private Variable variable;

    public VariableExpression(Variable variable) {
        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue(Environment environment) {
        return (T) environment.get(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableExpression<?> that = (VariableExpression<?>) o;
        return Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }

    @Override
    public String toString() {
        return String.format("$%s", variable);
    }
}
