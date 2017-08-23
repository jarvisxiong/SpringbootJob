package com.stixcloud.barcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;

public class BarcodeGenerationUtil {

  private static List<String> CHARREPLIST = new ArrayList<String>();
  private static List<String> KEYARRAYLIST = new ArrayList<String>();
  private static List<String> CHARLIST = new ArrayList<String>();

  public static String formatValue(String value, int length, int padPrefix, String padValue) {
    if (length == -1) {
      return value;
    }
    if (value.length() > length) {
      // truncate the value
      return value.substring(0, length);
    } else {
      if (padPrefix == 1L) {
        // Prefix
        return padLeft(value, length, padValue);
      } else {
        // Suffix
        return padRight(value, length, padValue);
      }
    }
  }

  public static String getEncryptedBarcode(String barcodeStr, int encryptedType, boolean checkSum) {
    String eBarcodeStr = "";
    if (StringUtils.equals("", barcodeStr)) {
      return null;
    }

    try {
      // Final check for length - Must be 19 before encryption
      switch (encryptedType) {
        case 1: {
          eBarcodeStr = shiftArrayEncryptBarcode(barcodeStr, checkSum);
          break;
        }
        default: {
          eBarcodeStr = barcodeStr;
        }
      }

      // Final check for length
      long checkLength = barcodeStr.length();
      if (checkSum) {
        checkLength = checkLength + 1;
      }
      if (eBarcodeStr.length() != checkLength) {
        return null;
      }
    } catch (Exception Ex) {
      eBarcodeStr = null;
    }

    return eBarcodeStr;

  }

  private static String padLeft(String value, int length, String padValue) {
    StringBuffer buff = new StringBuffer();

    int diff = length - value.length();
    for (int i = 0; i < diff; i++) {
      buff.append(padValue);
    }
    buff.append(value);
    return buff.toString();
  }

  private static String padRight(String value, int length, String padValue) {

    StringBuffer buff = new StringBuffer();
    buff.append(value);

    int diff = length - value.length();
    for (int i = 0; i < diff; i++) {
      buff.append(padValue);
    }
    return buff.toString();
  }

  private static String shiftArrayEncryptBarcode(String orgBarcodeStr, boolean checkSum) {
    initialData();
    String encryptedStr = "";
    if (StringUtils.equals("", orgBarcodeStr)) {
      return encryptedStr;
    }

    try {

      StringBuffer result = new StringBuffer();

      for (int i = 0; i < orgBarcodeStr.length(); i++) {
        // Get the array to compare the key
        String currentArray = KEYARRAYLIST.get(i + 1);

        // Get the character to encrypt
        char a = orgBarcodeStr.charAt(i);
        String checkStr = Character.toString(a);

        // Find the matching position of the character in the array
        int checkIndex = -1;
        if (!CHARLIST.contains(checkStr.toLowerCase())) {
          checkIndex = currentArray.indexOf(checkStr);

          // Default 0 for unfound info
          if (checkIndex == -1) {
            checkIndex = 0;
          }

          // Append to the barcode string
          result.append(checkIndex);
        } else {
          checkIndex = CHARLIST.indexOf(checkStr.toLowerCase());

          // Default 0 for unfound info
          if (checkIndex == -1) {
            checkIndex = 0;
          }

          // Append to the barcode string
          if (CHARREPLIST.get(checkIndex) != null) {
            result.append(CHARREPLIST.get(checkIndex));
          }
        }

      }
      // result is now the barcode starting without the checksum

      String midString = result.toString();

      encryptedStr = "";
      if (checkSum) {
        int evenSum = 0;
        int oddSum = 0;
        int checkSumValue = 0;
        for (int k = 0; k < midString.length(); k++) {
          char a = midString.charAt(k);
          int t = k + 1;

          int asciiValue = (int) a;

          if (t % 2 == 1) {
            // Odd
            oddSum = oddSum + (asciiValue - 48);
          } else {
            // even
            evenSum = evenSum + (asciiValue - 48);
          }
        }

        // Calculate the checksum
        checkSumValue = (10 - (((oddSum * 3) + evenSum) % 10)) % 10;
        int finalCheckSum = checkSumValue + 48;

        // Get the actual integer value of the asciiValue
        char charCS = (char) finalCheckSum;

        encryptedStr = midString + charCS;

      } else {
        encryptedStr = midString;
      }

    } catch (Exception Ex) {
      encryptedStr = "";
    }

    return encryptedStr;
  }

  private static void initialData() {
    String[] charRepArray = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5"};
    String[] charArray = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    CHARREPLIST = Arrays.asList(charRepArray);
    CHARLIST = Arrays.asList(charArray);

    String array1 = "1376258094";
    String array2 = "8432065719";
    String array3 = "4321605789";
    String array4 = "3807945621";
    String array5 = "2135460978";
    String array6 = "2314765809";
    String array7 = "3412587690";
    String array8 = "2153647089";
    String array9 = "3214865790";
    String array10 = "3740896512";
    String array11 = "8750413269";
    String array12 = "1325486970";
    String array13 = "8097236514";
    String array14 = "6574938201";
    String array15 = "1246539780";
    String array16 = "4213875609";
    String array17 = "1324569870";
    String array18 = "1234567890";
    String array19 = "0987654321";
    String array20 = "3674821509";
    String array21 = "2413567890";
    String array22 = "3518790462";
    String array23 = "4786523091";
    String array24 = "5876123409";
    String array25 = "6987234015";
    String array26 = "7369820145";

    KEYARRAYLIST.add(array1);
    KEYARRAYLIST.add(array2);
    KEYARRAYLIST.add(array3);
    KEYARRAYLIST.add(array4);
    KEYARRAYLIST.add(array5);
    KEYARRAYLIST.add(array6);
    KEYARRAYLIST.add(array7);
    KEYARRAYLIST.add(array8);
    KEYARRAYLIST.add(array9);
    KEYARRAYLIST.add(array10);
    KEYARRAYLIST.add(array11);
    KEYARRAYLIST.add(array12);
    KEYARRAYLIST.add(array13);
    KEYARRAYLIST.add(array14);
    KEYARRAYLIST.add(array15);
    KEYARRAYLIST.add(array16);
    KEYARRAYLIST.add(array17);
    KEYARRAYLIST.add(array18);
    KEYARRAYLIST.add(array19);
    KEYARRAYLIST.add(array20);
    KEYARRAYLIST.add(array21);
    KEYARRAYLIST.add(array22);
    KEYARRAYLIST.add(array23);
    KEYARRAYLIST.add(array24);
    KEYARRAYLIST.add(array25);
    KEYARRAYLIST.add(array26);
  }

}
