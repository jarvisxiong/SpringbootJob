package com.stixcloud.salesquotarestriction.domain.converters;

/**
 * Created by mango on 21/3/17.
 */
@FunctionalInterface
public interface TypeConverter<A, B> {

    B convert(A a);

}
