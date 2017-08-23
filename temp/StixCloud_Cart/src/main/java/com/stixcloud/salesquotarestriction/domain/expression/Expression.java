package com.stixcloud.salesquotarestriction.domain.expression;

import com.stixcloud.salesquotarestriction.domain.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mango on 17/3/17.
 */
public interface Expression {

    default boolean test(Environment t) {
        return true;
    }

    default List<Expression> getChildren() { return Collections.emptyList(); }


    /**
     * @return stream of leaves
     */
    default Stream<Expression> leafStream() {

        List<Expression> processList = new ArrayList<>(Collections.singleton(this));
        List<Expression> leafList    = new ArrayList<>();

        while (!processList.isEmpty()) {
            Expression e = processList.remove(0);
            if (e.getChildren().isEmpty())
                leafList.add(e);
            else
                processList.addAll(e.getChildren());
        }

        return leafList.stream();

    }


    /**
     * @return stream of children or itself if no children
     */
    default Stream<Expression> childStream() {
        return (getChildren().isEmpty() ? Stream.of(this) : getChildren().stream());
    }


    /**
     * @return stream of children or empty stream if no children
     */
    default Stream<Expression> stream() {
        return getChildren().stream();
    }
}
