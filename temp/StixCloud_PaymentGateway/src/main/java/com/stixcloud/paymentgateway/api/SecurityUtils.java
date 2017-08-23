/**
 * This class is used for generating random password using MD5 algorithm
 * @author Bala
 */
package com.stixcloud.paymentgateway.api;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("restriction")
public final class SecurityUtils {
  private static final Logger logger = LogManager.getLogger(SecurityUtils.class);
  private static final int MASK_START_INDEX = 6;
  private static final int MASK_END_INDEX = 4;
  private static final int MASK_IC_ENDING_INDEX = 3;
  //#4225 - PDPA - Mask NRIC - added by Than
  public static final char MASK_CHAR = 'x';
  private static final char DASH = '-';
  private static final char FORWARD_SLASH = '\\';
  private static final char BACKWARD_SLASH = '/';
  private static final char SPACE = ' ';

  /**
   * Mask the data information, except for the first starting chars and last ending chars,
   * defined by constants MASK_START_INDEX and MASK_END_INDEX<br>
   * The string data passed in should be in decrypted format<br>
   */
  public static String maskPartial(String data) {
    if (StringUtils.isEmpty(data)) {
      return data;
    }
    StringBuilder maskedData = new StringBuilder(data);
    for (int i = MASK_START_INDEX; i < maskedData.length() - MASK_END_INDEX;
         i++) { //start from starting index and end at ending index
      if (!skipCharacter(maskedData.charAt(i))) {
        maskedData.setCharAt(i, MASK_CHAR); //else mask using the mask_char
      }
    }
    return maskedData.toString();
  }

  /**
   * Check if the input char can be skipped<br>
   * Currently checks for dash, space, \, /<br>
   */
  private static boolean skipCharacter(char data) {
    if (data == DASH || data == SPACE || data == FORWARD_SLASH || data == BACKWARD_SLASH) {
      return true;
    } else {
      return false;
    }
  }


}