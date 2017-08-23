package com.stixcloud.patron.util;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PatronProfileUtil {

  private static String key = "07082003..sop.ecquaria.com";
  private static byte[] raw = key.getBytes(StandardCharsets.UTF_8);
  private static SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
  private static final String PHONE_NUMBER_PATTERN = "^[0-9]*$";
  
  final static char[] HEXTAB =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
  
  private static Pattern pattern;
  private static Matcher matcher;
  
  public static String encrypt(String clearText) {
    String encoded = null;
    Cipher cipher;
    try {
      cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
      byte[] encrypted = cipher.doFinal(clearText.getBytes());

      encoded = bytesToBinHex(encrypted);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException("Encryption error", e);
    }

    return encoded;
  }

  private static String bytesToBinHex(byte[] data) {
    return bytesToBinHex(data, 0, data.length);
  }

  private static String bytesToBinHex(byte[] data, int nStartPos, int nNumOfBytes) {

    StringBuffer sbuf = new StringBuffer();
    sbuf.setLength(nNumOfBytes << 1);

    int nPos = 0;
    for (int nI = 0; nI < nNumOfBytes; nI++) {
      sbuf.setCharAt(nPos++, HEXTAB[(data[nI + nStartPos] >> 4) & 0x0f]);
      sbuf.setCharAt(nPos++, HEXTAB[data[nI + nStartPos] & 0x0f]);
    } ;
    return sbuf.toString();
  }
  
  public static boolean isValidPhoneNumber(String phoneNumber) {
    pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
    matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
  }
}
