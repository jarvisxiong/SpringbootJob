/**
 * This class is used for generating random password using MD5 algorithm
 * @author Bala
 */
package com.stixcloud.cart;

import com.stixcloud.cart.rules.commit.CreatePayment;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("restriction")
public final class SecurityUtils {
  private static final Logger logger = LogManager.getLogger(CreatePayment.class);

  public enum EncryptionAlgorithm {
    AES,
    Blowfish,
    DES,
    DESede, //Triple DES
    RC2,
    RC5,
    RC6,
    twofish,
    RSA
  }

  private static final EncryptionAlgorithm ALGO = EncryptionAlgorithm.AES;

  private static final byte[] keyValue = "YOH0Oi44%dpx!M^S".getBytes();

  //Constants defining the starting and ending index for masking of a string
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

  /**
   * Generate key.
   * @return the key
   * @throws Exception the exception
   */
  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(keyValue, ALGO.toString());
    return key;
  }

  /**
   * Encrypts a data and the algorithm used is AES algorithm.
   * @param data the data
   * @return the string
   */
  public static String encrypt(String data) {
    String encryptedValue = data;
    try {
      Key key = generateKey();
      Cipher c = Cipher.getInstance(ALGO.toString());
      c.init(Cipher.ENCRYPT_MODE, key);
      byte[] encVal = c.doFinal(data.getBytes());
      encryptedValue = new BASE64Encoder().encode(encVal);
      return encryptedValue;
    } catch (Exception e) {
      encryptedValue = data;
    }
    return encryptedValue;
  }

  public static String getSHA3Hash(final String input) {
    final SHA3.DigestSHA3 sha3 = new SHA3.Digest512();

    sha3.update(input.getBytes());

    return new String(Hex.encode(sha3.digest()));
  }

  public static String generateSalt() {
    byte[] saltByte = new byte[64]; //as we are using SHA-512 for PBKDF2, 512bits / 8 = 64 bytes
    SecureRandom sr = new SecureRandom();
    sr.nextBytes(saltByte);

    return new String(Hex.encode(saltByte));
  }

  //PBKDF2 implementation
  public static String getPBKDF2(final String input, final String salt) {
    PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA512Digest());
    gen.init(PBEParametersGenerator.PKCS5PasswordToBytes(
        input.toCharArray()),//input
        Hex.decode(salt),//salt
        10000);//iterations

    byte[] hash = ((KeyParameter) gen.generateDerivedParameters(512)).getKey();
    return (hash != null ? new String(Hex.encode(hash)) : null);
  }
}