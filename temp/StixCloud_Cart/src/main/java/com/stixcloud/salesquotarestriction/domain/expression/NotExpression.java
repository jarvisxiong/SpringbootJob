package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by mango on 17/3/17.
 */
public class NotExpression extends LogicalExpression implements Serializable {

    private static final long serialVersionUID = 6697636048240629853L;

    private Expression expression;

    public NotExpression() {
        super(Operator.NOT);
    }

    public NotExpression(Expression expression) {
        this();
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public List<Expression> getChildren() {
        return Collections.singletonList(expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotExpression that = (NotExpression) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    @Override
    public boolean test(Environment environment) {
        return !expression.test(environment);
    }

    @Override
    public String toString() {
        return String.format("%s%s", operator, expression);
    }
}
