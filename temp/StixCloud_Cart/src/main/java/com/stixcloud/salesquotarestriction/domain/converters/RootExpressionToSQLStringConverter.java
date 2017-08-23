package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.salesquotarestriction.domain.expression.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mango on 27/3/17.
 */
@Component
public class RootExpressionToSQLStringConverter implements TypeConverter<Expression, String> {

    protected RootExpressionToSQLStringConverter chain;

    private RootExpressionToSQLStringConverter chain(RootExpressionToSQLStringConverter chain) {
        if (this.chain != null)
            this.chain.chain = chain;
        return this.chain = chain;
    }

    @PostConstruct
    public void init() {

        // this is the final root and the final root is this
        final RootExpressionToSQLStringConverter root = this;
        
        root


                // OrExpression <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof OrExpression) {
                            List<Expression> expressionList = expression.getChildren();
                            switch (expressionList.size()) {
                                case 0:
                                    return "";
                                case 1:
                                    return root.convert(expressionList.get(0));
                                default:
                                    return String.format("(%s)", expressionList.stream().map(root::convert).collect(Collectors.joining(" OR ")));
                            }
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })


                // AndExpression <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof AndExpression) {
                            List<Expression> expressionList = expression.getChildren();
                            switch (expressionList.size()) {
                                case 0:
                                    return "";
                                case 1:
                                    return root.convert(expressionList.get(0));
                                default:
                                    return String.format("(%s)", expressionList.stream().map(root::convert).collect(Collectors.joining(" AND ")));
                            }
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })


                // NotExpression <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof NotExpression) {
                            return String.format("NOT %s", ((NotExpression) expression).getExpression());
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })


                // ConstantExpression <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof ConstantExpression) {
                            return ((ConstantExpression) expression).getValue().toString();
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })


                // RelationalExpression equals <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof RelationalExpression && ((RelationalExpression) expression).getOperator() == RelationalExpression.Operator.EQUALS) {
                            RelationalExpression relationalExpression = ((RelationalExpression) expression);
                            return String.format("%s=%s", root.convert(relationalExpression.getLHS()), root.convert(relationalExpression.getRHS()));
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })


                // VariableExpression <=> sql converter
                .chain(new RootExpressionToSQLStringConverter() {
                    @Override
                    public String convert(Expression expression) {
                        if (expression instanceof VariableExpression) {
                            VariableExpression variableExpression = ((VariableExpression) expression);
                            return String.format("${%s}", variableExpression.getVariable().name());
                        } else
                            return chain == null ? "" : chain.convert(expression);
                    }
                })
        ;

    }

    @Override
    public String convert(Expression expression) {
        return chain.convert(expression);
    }
}
