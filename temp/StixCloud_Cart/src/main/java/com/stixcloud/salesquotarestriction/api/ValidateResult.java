package com.stixcloud.salesquotarestriction.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mango on 14/3/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"result","message"})
public class ValidateResult implements Serializable {

    private static final long serialVersionUID = 5379242141702688803L;

    public enum Result {
        PASS(0, "salesquotarestriction.result.pass")
        , ALERT(1, "salesquotarestriction.result.alert")
        , QUOTA_EXCEEDED_GLOBAL(2, "salesquotarestriction.result.exceeded.global")
        , QUOTA_EXCEEDED_PER_TRANSACTION(3, "salesquotarestriction.result.exceeded.transaction")
        , QUOTA_EXCEEDED_PER_PATRON_EMAIL(4, "salesquotarestriction.result.exceeded.patronemail")
        , QUOTA_EXCEEDED_PER_PATRON_PRIMARY_CONTACT_NUMBER(5, "salesquotarestriction.result.exceeded.patronprimarycontactnumber")
        ;

        private int value;
        private String messageKey;

        Result(int value, String messageKey) {
            this.value = value;
            this.messageKey = messageKey;
        }

        @JsonValue
        public Integer intValue() {
            return value;
        }

        public String getMessageKey() {
            return messageKey;
        }
    }

    @JsonProperty("result")
    private Result result;

    @JsonProperty("message")
    private String message;

    public ValidateResult() {}

    public ValidateResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidateResult that = (ValidateResult) o;
        return result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("result", result)
                .toString();
    }
}
