package com.stixcloud.policyagent.util;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * implements the encryption/decryption utilities using Blowfish algorithm. Largely based on
 * Sistic's existing implementation
 *
 * @author Colin Hobday
 */
public class EncryptionBlowfish {
  private static String key = "07082003..sop.ecquaria.com";
  private static byte[] raw = key.getBytes(StandardCharsets.UTF_8);

  // our table for binhex conversion
  final static char[] HEXTAB =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  private static SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");

  /**
   * Instantiates a new old sistic blowfish util.
   */
  public EncryptionBlowfish() {}

  /**
   * Encrypts a clearText and further encode to Binary Hex such as it can be stored in database.
   * 
   * @param clearText String
   * @return encodedCipherText Encoded cipher text in BinHex format
   */
  public static String encrypt(String clearText) {
    String encoded = clearText;
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

  /**
   * Decrypts a BinHex-encoded cipher text. First, the method decode the string from BinHex format
   * to normal String, then further decrypts it
   * 
   * @param encodedCipherText An encoded cipher text.
   * @return clearText Decrypted text, i.e. clear text.
   */
  public static String decrypt(String encodedCipherText) {

    String decoded = null;
    try {
      byte data[] = new byte[encodedCipherText.length()];

      int bytesExtracted = binHexToBytes(encodedCipherText, data, 0, 0, encodedCipherText.length());

      System.out.println("encodedCipherText.length()=" + encodedCipherText.length()
          + ", bytesExtracted=" + bytesExtracted);

      // Cannot use data[] directly coz final block not properly padded!
      byte newData[] = new byte[bytesExtracted];
      for (int i = 0; i < bytesExtracted; i++) {
        newData[i] = data[i];
      }

      Cipher cipher = Cipher.getInstance("Blowfish");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec);

      byte[] decrypted = cipher.doFinal(newData);
      decoded = new String(decrypted);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException("Encryption error", e);
    }
    return decoded;
  }

  /**
   * converts a binhex string back into a byte array (invalid codes will be skipped).
   *
   * @param sBinHex binhex string
   * @param data the target array
   * @param nSrcPos from which character in the string the conversion should begin, remember that
   *        (nSrcPos modulo 2) should equals 0 normally
   * @param nDstPos to store the bytes from which position in the array
   * @param nNumOfBytes number of bytes to extract
   * @return number of extracted bytes
   */
  private static int binHexToBytes(String sBinHex, byte[] data, int nSrcPos, int nDstPos,
      int nNumOfBytes) {
    // check for correct ranges
    int nStrLen = sBinHex.length();
    int nAvailBytes = (nStrLen - nSrcPos) >> 1;
    if (nAvailBytes < nNumOfBytes)
      nNumOfBytes = nAvailBytes;
    int nOutputCapacity = data.length - nDstPos;
    if (nNumOfBytes > nOutputCapacity)
      nNumOfBytes = nOutputCapacity;

    // convert now
    int nResult = 0;
    for (int nI = 0; nI < nNumOfBytes; nI++) {
      byte bActByte = 0;
      boolean blConvertOK = true;
      for (int nJ = 0; nJ < 2; nJ++) {
        bActByte <<= 4;
        char cActChar = sBinHex.charAt(nSrcPos++);
        if ((cActChar >= 'a') && (cActChar <= 'f'))
          bActByte |= (byte) (cActChar - 'a') + 10;
        else if ((cActChar >= '0') && (cActChar <= '9'))
          bActByte |= (byte) (cActChar - '0');
        else
          blConvertOK = false;
      }
      if (blConvertOK) {
        data[nDstPos++] = bActByte;
        nResult++;
      }
    }
    return nResult;
  }

  /**
   * converts a byte array to a binhex string.
   *
   * @param data the byte array
   * @return the binhex string
   */
  private static String bytesToBinHex(byte[] data) {
    return bytesToBinHex(data, 0, data.length);
  }

  /**
   * converts a byte array to a binhex string.
   *
   * @param data the byte array
   * @param nStartPos start index where to get the bytes
   * @param nNumOfBytes number of bytes to convert
   * @return the binhex string
   */
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

  /**
   * Use this to encrypt / decrypt stuff during testing.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    String input = "8c193b4ff66a7e9c1257869b61107e6a";
    System.out.println("input:" + input + "    decrypted:" + decrypt(input));
    input = "ab";
    System.out.println("input:" + input + "    encrypt:" + encrypt(input));
    input = "e";
    System.out.println("input:" + input + "    encrypt:" + encrypt(input));
    input = "password$1";
    System.out.println("input:" + input + "    encrypt:" + encrypt(input));
  }
}
