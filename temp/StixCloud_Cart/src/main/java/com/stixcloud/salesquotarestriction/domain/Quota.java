package com.stixcloud.salesquotarestriction.domain;

import com.stixcloud.salesquotarestriction.domain.expression.OrExpression;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * Created by mango on 17/3/17.
 */
public class Quota implements Serializable {

    private static final long serialVersionUID = -382924701596915372L;


    public static class Scope implements Serializable {

        private static final long serialVersionUID = 2520733665378836961L;

        public enum Type {GLOBAL, TRANSACTION, PATRON_EMAIL, PATRON_PRIMARYCONTACTNUMBER}

        private Type   type;
        private String parameter;

        public Scope() {
        }

        public Scope(Type type, String parameter) {
            this.type = type;
            this.parameter = parameter;
        }

        public Type getType() {
            return type;
        }

        public Optional<String> getParameter() {
            return Optional.ofNullable(parameter);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Scope scope1 = (Scope) o;
            return type == scope1.type &&
                    Objects.equals(parameter, scope1.parameter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, parameter);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("scope", type)
                    .append("parameter", parameter)
                    .toString();
        }
    }

    private Long         quota;
    private Long         warningThreshold;
    private Scope        scope;
    private OrExpression criteria;

    public Long getQuota() {
        return quota;
    }

    public OptionalLong getWarningThreshold() {
        return warningThreshold == null ? OptionalLong.empty() : OptionalLong.of(warningThreshold);
    }

    public Scope getScope() {
        return scope;
    }

    public OrExpression getCriteria() {
        return criteria;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public void setWarningThreshold(Long warningThreshold) {
        this.warningThreshold = warningThreshold;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public void setCriteria(OrExpression criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quota quota1 = (Quota) o;
        return Objects.equals(quota, quota1.quota) &&
                Objects.equals(warningThreshold, quota1.warningThreshold) &&
                Objects.equals(scope, quota1.scope) &&
                Objects.equals(criteria, quota1.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quota, warningThreshold, scope, criteria);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("quota", quota)
                .append("warningThreshold", warningThreshold)
                .append("scope", scope)
                .append("criteria", criteria)
                .toString();
    }
}
