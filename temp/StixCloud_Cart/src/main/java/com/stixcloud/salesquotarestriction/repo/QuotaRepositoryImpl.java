package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.salesquotarestriction.domain.ItemQuantityTuple;
import com.stixcloud.salesquotarestriction.domain.Quota;
import com.stixcloud.salesquotarestriction.domain.converters.RootExpressionToSQLStringConverter;
import com.stixcloud.salesquotarestriction.domain.expression.ConstantExpression;
import com.stixcloud.salesquotarestriction.domain.expression.Expression;
import com.stixcloud.salesquotarestriction.domain.expression.OrExpression;
import com.stixcloud.salesquotarestriction.domain.expression.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mango on 28/3/17.
 */
@Repository
public class QuotaRepositoryImpl implements QuotaRepositoryCustom {

    private static final int SQL_MAX_NUMBER_OFCLAUSES = 500;
    private final EntityManager                      entityManager;
    private final RootExpressionToSQLStringConverter rootExpressionToSQLStringConverter;


    @Autowired
    public QuotaRepositoryImpl(EntityManager entityManager, RootExpressionToSQLStringConverter rootExpressionToSQLStringConverter) {
        this.entityManager = entityManager;
        this.rootExpressionToSQLStringConverter = rootExpressionToSQLStringConverter;
    }

    private Stream<ItemQuantityTuple> paginate(BiFunction<Expression, Object[], Stream<ItemQuantityTuple>> processFunction, Quota... quotas) {

        class MethodLocalHolder {
            private Stream<ItemQuantityTuple> resultStream = Stream.of();
        }

        MethodLocalHolder holder = new MethodLocalHolder();

        // break quota into sub expressions with count of clauses
        Stream.of(quotas)
                .collect(
                        Collectors.groupingBy(
                                Function.identity()
                                , Collectors.collectingAndThen(
                                        Collectors.toList()
                                        , l -> l.stream()
                                                .map(Quota::getCriteria)
                                                .map(Expression::getChildren)
                                                .flatMap(Collection::stream)
                                                .collect(
                                                        Collectors.groupingBy(
                                                                Function.identity()
                                                                , LinkedHashMap::new
                                                                , Collectors.reducing(
                                                                        0L
                                                                        , e -> e.leafStream().filter(ee -> ee instanceof ConstantExpression).count()
                                                                        , Long::sum
                                                                )
                                                        )
                                                )
                                )
                        )
                )
                // perform pagination based on SQL_MAX_NUMBER_OFCLAUSES
                .forEach((k, v) -> {
                            long         clauseCount[] = new long[1];
                            OrExpression root          = new OrExpression();

                            v.forEach((kk, vv) -> {

                                        // process any non empty page and full page
                                        if (clauseCount[0] > 0 && clauseCount[0] + vv > SQL_MAX_NUMBER_OFCLAUSES) {

                                            //process full page
                                            holder.resultStream = Stream.concat(holder.resultStream, processFunction.apply(root, new Object[]{k.getScope().getParameter()}));

                                            // reset page
                                            root.getChildren().clear();
                                            clauseCount[0] = 0;

                                        }

                                        // accumulate
                                        root.or(kk);
                                        clauseCount[0] += vv;

                                    }

                            );

                            // process any non empty page
                            if (clauseCount[0] > 0) {

                                //process page
                                holder.resultStream = Stream.concat(holder.resultStream, processFunction.apply(root, new Object[]{k.getScope().getParameter()}));

                                // reset page
                                root.getChildren().clear();
                                clauseCount[0] = 0;

                            }
                        }
                );

        return holder.resultStream;

    }

    public Stream<ItemQuantityTuple> findQuantityByPatronPrimaryContactNumber(Quota... quotas) {
        if (quotas.length == 0)
            return Stream.of();
        else if (!quotas[0].getScope().getParameter().isPresent())
            return Stream.of();

        /*
         * perform pagination if necessary
         * as a single quota may contain too many clauses for sql to handle
         */
        return paginate(this::findQuantityByPatronPrimaryContactNumber, quotas);
    }

    public Stream<ItemQuantityTuple> findQuantityByPatronEmailAddress(Quota... quotas) {
        if (quotas.length == 0)
            return Stream.of();
        else if (!quotas[0].getScope().getParameter().isPresent())
            return Stream.of();

        /*
         * perform pagination if necessary
         * as a single quota may contain too many clauses for sql to handle
         */
        return paginate(this::findQuantityByPatronEmailAddress, quotas);
    }

    public Stream<ItemQuantityTuple> findQuantity(Quota... quotas) {
        if (quotas.length == 0)
            return Stream.of();

        /*
         * perform pagination if necessary
         * as a single quota may contain too many clauses for sql to handle
         */
        return paginate(this::findQuantity, quotas);

    }

    @SuppressWarnings("unchecked")
    private Stream<ItemQuantityTuple> findQuantityByPatronPrimaryContactNumber(Expression root, Object... parameters) {

        // build SQL WHERE clause from expression
        String criteria = Stream.of(root)
                .map(rootExpressionToSQLStringConverter::convert)
                .map(s -> s.replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_GROUP_ID), "PL.PRODUCT_GROUP_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_ID), "TP.PRODUCT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CATEGORY_ID), "TP.PRICE_CAT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CLASS_ID), "TP.PRICE_CLASS_ID"))
                .collect(Collectors.joining(") OR (", "((", "))"));


        Query q = entityManager.createNativeQuery(String.format("SELECT \n" +
                "  PL.PRODUCT_GROUP_ID \n" +
                "  , TP.PRODUCT_ID \n" +
                "  , TP.PRICE_CAT_ID \n" +
                "  , TP.PRICE_CLASS_ID \n" +
                "  , SUM(CASE MCT.MASTERCODEID \n" +
                // count purchase as 1
                "        WHEN 11 THEN 1 \n" +
                // count return as -1
                "        WHEN 12 THEN -1 \n" +
                // don't care
                "        ELSE 0 \n" +
                "        END * 1) TICKET_QTY \n" +
                "FROM TRANSACTION T \n" +
                "  JOIN ( \n" +
                "    SELECT PP.PATRON_PROFILE_ID PATRONPROFILEID FROM PATRON_PHONE PP WHERE PP.ISPRIMARY='T' AND PP.PHONENUMBER = :primaryContactNumber \n" +
                ") PP ON PP.PATRONPROFILEID=T.PATRON_ID \n" +
                "  JOIN TRANSACTION_LINE_ITEM_PRODUCT TLIP ON TLIP.TRANSACTIONREFNUMBER=T.TRANSACTIONREFNUMBER \n" +
                "  JOIN TRANSACTION_PRODUCT TP ON TP.TXNPRODUCTID=TLIP.TXN_PRODUCT_ID \n" +
                "  JOIN PRODUCT_LIVE PL ON PL.PRODUCTID=TP.PRODUCT_ID \n" +
                "  JOIN TRANSACTION_CODE TC ON TC.TRANSACTIONCODEID=T.TXN_CODE_ID \n" +
                "  JOIN MASTER_CODE_TABLE MCT ON MCT.MASTERCODEID=TC.TXN_CODE_MCT_ID AND MCT.CATEGORYCODE='Transaction Type' \n" +
                "WHERE T.ORDERSTATUS=1 \n" +
                "AND %s \n" +
                "GROUP BY PL.PRODUCT_GROUP_ID, TP.PRODUCT_ID, TP.PRICE_CAT_ID, TP.PRICE_CLASS_ID \n", criteria));

        q.setParameter("primaryContactNumber", ((Optional<String>) parameters[0]).get());


        return ((List<Object[]>) q.getResultList()).stream()
                // only keep quantity > 0
                .filter(e -> ((Number) e[4]).longValue() > 0)
                .map(e -> new ItemQuantityTuple(((Number) e[4]).longValue(), ((Number) e[0]).longValue(), ((Number) e[1]).longValue(), ((Number) e[3]).longValue(), ((Number) e[2]).longValue()))
                ;

    }

    @SuppressWarnings("unchecked")
    private Stream<ItemQuantityTuple> findQuantityByPatronEmailAddress(Expression root, Object... parameters) {

        // build SQL WHERE clause from expression
        String criteria = Stream.of(root)
                .map(rootExpressionToSQLStringConverter::convert)
                .map(s -> s.replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_GROUP_ID), "PL.PRODUCT_GROUP_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_ID), "TP.PRODUCT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CATEGORY_ID), "TP.PRICE_CAT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CLASS_ID), "TP.PRICE_CLASS_ID"))
                .collect(Collectors.joining(") OR (", "((", "))"));


        Query q = entityManager.createNativeQuery(String.format("SELECT \n" +
                "  PL.PRODUCT_GROUP_ID \n" +
                "  , TP.PRODUCT_ID \n" +
                "  , TP.PRICE_CAT_ID \n" +
                "  , TP.PRICE_CLASS_ID \n" +
                "  , SUM(CASE MCT.MASTERCODEID \n" +
                // count purchase as 1
                "        WHEN 11 THEN 1 \n" +
                // count return as -1
                "        WHEN 12 THEN -1 \n" +
                // don't care
                "        ELSE 0 \n" +
                "        END * 1) TICKET_QTY \n" +
                "FROM TRANSACTION T \n" +
                "  JOIN ( \n" +
                "    SELECT PP.PATRONPROFILEID FROM PATRON_PROFILE PP WHERE UPPER(PP.EMAILADDRESS) = :emailAddress \n" +
                ") PP ON PP.PATRONPROFILEID=T.PATRON_ID \n" +
                "  JOIN TRANSACTION_LINE_ITEM_PRODUCT TLIP ON TLIP.TRANSACTIONREFNUMBER=T.TRANSACTIONREFNUMBER \n" +
                "  JOIN TRANSACTION_PRODUCT TP ON TP.TXNPRODUCTID=TLIP.TXN_PRODUCT_ID \n" +
                "  JOIN PRODUCT_LIVE PL ON PL.PRODUCTID=TP.PRODUCT_ID \n" +
                "  JOIN TRANSACTION_CODE TC ON TC.TRANSACTIONCODEID=T.TXN_CODE_ID \n" +
                "  JOIN MASTER_CODE_TABLE MCT ON MCT.MASTERCODEID=TC.TXN_CODE_MCT_ID AND MCT.CATEGORYCODE='Transaction Type' \n" +
                "WHERE T.ORDERSTATUS=1 \n" +
                "AND %s \n" +
                "GROUP BY PL.PRODUCT_GROUP_ID, TP.PRODUCT_ID, TP.PRICE_CAT_ID, TP.PRICE_CLASS_ID \n", criteria));

        q.setParameter("emailAddress", ((Optional<String>) parameters[0]).get().toUpperCase());


        return ((List<Object[]>) q.getResultList()).stream()
                // only keep quantity > 0
                .filter(e -> ((Number) e[4]).longValue() > 0)
                .map(e -> new ItemQuantityTuple(((Number) e[4]).longValue(), ((Number) e[0]).longValue(), ((Number) e[1]).longValue(), ((Number) e[3]).longValue(), ((Number) e[2]).longValue()))
                ;

    }

    @SuppressWarnings("unchecked")
    private Stream<ItemQuantityTuple> findQuantity(Expression root, Object... parameters) {

        // build WHERE clause from expression
        String criteria = Stream.of(root)
                .map(rootExpressionToSQLStringConverter::convert)
                .map(s -> s.replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_GROUP_ID), "PL.PRODUCT_GROUP_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRODUCT_ID), "TP.PRODUCT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CATEGORY_ID), "TP.PRICE_CAT_ID")
                        .replaceAll(String.format("\\$\\{%s\\}", Variable.PRICE_CLASS_ID), "TP.PRICE_CLASS_ID"))
                .collect(Collectors.joining(") OR (", "((", "))"));


        Query q = entityManager.createNativeQuery(String.format("SELECT \n" +
                "  PL.PRODUCT_GROUP_ID \n" +
                "  , TP.PRODUCT_ID \n" +
                "  , TP.PRICE_CAT_ID \n" +
                "  , TP.PRICE_CLASS_ID \n" +
                "  , SUM(CASE MCT.MASTERCODEID \n" +
                // count purchase as 1
                "        WHEN 11 THEN 1 \n" +
                // count return as -1
                "        WHEN 12 THEN -1 \n" +
                // don't care
                "        ELSE 0 \n" +
                "        END * 1) TICKET_QTY \n" +
                "FROM TRANSACTION T \n" +
                "  JOIN TRANSACTION_LINE_ITEM_PRODUCT TLIP ON TLIP.TRANSACTIONREFNUMBER=T.TRANSACTIONREFNUMBER \n" +
                "  JOIN TRANSACTION_PRODUCT TP ON TP.TXNPRODUCTID=TLIP.TXN_PRODUCT_ID \n" +
                "  JOIN PRODUCT_LIVE PL ON PL.PRODUCTID=TP.PRODUCT_ID \n" +
                "  JOIN TRANSACTION_CODE TC ON TC.TRANSACTIONCODEID=T.TXN_CODE_ID \n" +
                "  JOIN MASTER_CODE_TABLE MCT ON MCT.MASTERCODEID=TC.TXN_CODE_MCT_ID AND MCT.CATEGORYCODE='Transaction Type' \n" +
                "WHERE T.ORDERSTATUS=1 \n" +
                "AND %s \n" +
                "GROUP BY PL.PRODUCT_GROUP_ID, TP.PRODUCT_ID, TP.PRICE_CAT_ID, TP.PRICE_CLASS_ID \n", criteria));


        return ((List<Object[]>) q.getResultList()).stream()
                // only keep quantity > 0
                .filter(e -> ((Number) e[4]).longValue() > 0)
                .map(e -> new ItemQuantityTuple(((Number) e[4]).longValue(), ((Number) e[0]).longValue(), ((Number) e[1]).longValue(), ((Number) e[3]).longValue(), ((Number) e[2]).longValue()))
                ;
    }
}
