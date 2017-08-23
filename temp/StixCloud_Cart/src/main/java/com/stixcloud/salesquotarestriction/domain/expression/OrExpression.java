package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by mango on 17/3/17.
 */
public class OrExpression extends LogicalExpression implements Serializable {

    private static final long serialVersionUID = -4532448836197314953L;

    private List<Expression> expressionList = new ArrayList<>();

    private OrExpression() {
        super(Operator.OR);
    }

    public OrExpression(Expression... expressions) {
        this();
        if (expressions.length > 0)
            this.expressionList = Arrays.asList(expressions);
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public OrExpression or(Expression e) {
        expressionList.add(e);
        return this;
    }

    @Override
    public boolean test(Environment environment) {
        final boolean[] result = new boolean[]{false};
        expressionList.forEach(x -> result[0] = result[0] || x.test(environment));
        return result[0];
    }

    @Override
    public List<Expression> getChildren() {
        return expressionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrExpression that = (OrExpression) o;
        return Objects.equals(expressionList, that.expressionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expressionList);
    }

    @Override
    public String toString() {
        switch (expressionList.size()) {
            case 0:
                return "";
            case 1:
                return expressionList.get(0).toString();
            default:
                return String.format("(%s)", expressionList.stream().map(Object::toString).collect(Collectors.joining(" " + operator + " ")));
        }
    }
}
