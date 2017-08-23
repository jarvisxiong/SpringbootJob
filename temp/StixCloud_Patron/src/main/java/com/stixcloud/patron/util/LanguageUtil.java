package com.stixcloud.patron.util;

import java.util.Arrays;
import java.util.Locale;

public class LanguageUtil {

  public static boolean checkLanguageCode(String lang) {
    String[] locales = Locale.getISOLanguages();
    return Arrays.asList(locales).contains(lang.toLowerCase());
  }

}
