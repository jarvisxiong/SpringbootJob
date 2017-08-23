package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * Created by mango on 19/3/2017.
 */
public class RelationalExpression<T extends Comparable<? super T>> implements Expression, Serializable {

    private static final long serialVersionUID = 1114809706029637086L;

    public enum Operator {
        EQUALS("=", x -> x == 0);

        private String             shortName;
        private Predicate<Integer> predicate;

        Operator(String shortName, Predicate<Integer> predicate) {
            this.shortName = shortName;
            this.predicate = predicate;
        }

        public Predicate<Integer> getPredicate() {
            return predicate;
        }

        @Override
        public String toString() {
            return shortName;
        }
    }

    private TypedValueExpression<T> LHS;
    private Operator                operator;
    private TypedValueExpression<T> RHS;

    public RelationalExpression(Operator operator) {
        this.operator = operator;
    }

    public RelationalExpression(TypedValueExpression<T> LHS, Operator operator, TypedValueExpression<T> RHS) {
        this.LHS = LHS;
        this.operator = operator;
        this.RHS = RHS;
    }

    public Operator getOperator() {
        return operator;
    }

    public TypedValueExpression<T> getLHS() {
        return LHS;
    }

    public TypedValueExpression<T> getRHS() {
        return RHS;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setLHS(TypedValueExpression<T> LHS) {
        this.LHS = LHS;
    }

    public void setRHS(TypedValueExpression<T> RHS) {
        this.RHS = RHS;
    }

    @Override
    public boolean test(Environment environment) {
        T LHSValue = LHS.getValue(environment);
        T RHSValue = RHS.getValue(environment);
        return (LHSValue == null && RHSValue == null) || ((LHSValue != null && RHSValue != null) && operator.getPredicate().test(LHSValue.compareTo(RHSValue)));
    }

    @Override
    public List<Expression> getChildren() {
        return Arrays.asList(LHS, RHS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationalExpression that = (RelationalExpression) o;
        return operator == that.operator &&
                Objects.equals(LHS, that.LHS) &&
                Objects.equals(RHS, that.RHS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, LHS, RHS);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", LHS, operator, RHS);
    }

}
