package com.stixcloud.salesquotarestriction.domain.expression;

/**
 * Created by mango on 17/3/17.
 */
public abstract class LogicalExpression implements Expression {

    private static final long serialVersionUID = 8110881626435740800L;

    public enum Operator {
        AND("&&"), OR("||"), NOT("^");

        private String shortName;

        Operator(String shortName) {
            this.shortName = shortName;
        }

        @Override
        public String toString() {
            return shortName;
        }
    }

    protected Operator operator;

    public LogicalExpression(Operator operator) {
        this.operator = operator;
    }
}
