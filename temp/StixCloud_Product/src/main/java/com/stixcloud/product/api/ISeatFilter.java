package com.stixcloud.product.api;

import java.util.List;

public interface ISeatFilter<T> {

  List<T> filter();

}
