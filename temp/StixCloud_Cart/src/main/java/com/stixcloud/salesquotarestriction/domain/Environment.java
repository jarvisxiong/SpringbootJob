package com.stixcloud.salesquotarestriction.domain;

import com.stixcloud.salesquotarestriction.domain.expression.Variable;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by mango on 22/3/17.
 */
public class Environment extends EnumMap<Variable, Object> implements Serializable {

    private static final long serialVersionUID = 8007831482606141347L;

    public Environment(Class<Variable> keyType) {
        super(keyType);
    }

    public Environment(EnumMap<Variable, ?> m) {
        super(m);
    }

    public Environment(Map<Variable, ?> m) {
        super(m);
    }

    public Environment() {
        this(Variable.class);
    }
}
