package com.sistic.be.app.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PaymentUtil {

	private static final Logger logger = LoggerFactory.getLogger(PaymentUtil.class);
	
	 // This is an array for creating hex chars
    static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	
	public static String calculateSecureHash(String secret, TreeMap<String, String> fieldsMap) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
	
		StringBuffer strBuf = new StringBuffer();
		
//		fieldsMap.forEach((k, v) -> strBuf.append(k + "=" + v));
		
		/**
		 * Refactor this block of code to iterate efficiently
		 */
		for (Map.Entry<String, String> element : fieldsMap.entrySet()) {
			strBuf.append(element.getKey() + "=" + element.getValue());
			if (element.getKey() != fieldsMap.lastKey()) {
				strBuf.append("&");
			}
		}
		
		byte[]  b = fromHexString(secret, 0, secret.length());
		Mac sha256 = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(b, "HmacSHA256");
		sha256.init(secretKey);
		
		byte[] ba = sha256.doFinal(strBuf.toString().getBytes("ISO-8859-1"));
		String secureHash = hex(ba);
		
		return secureHash;
	}
	
	public static byte[] fromHexString(String s, int offset, int length) {
		if ((length%2) != 0)
			return null;
		byte[] byteArray = new byte[length/2];
		int j = 0;
		int end = offset+length;
		for (int i = offset; i < end; i += 2)
			{
			  int high_nibble = Character.digit(s.charAt(i), 16);
			  int low_nibble = Character.digit(s.charAt(i+1), 16);
			  if (high_nibble == -1 || low_nibble == -1)
			  {
				// illegal format
				return null;
			  }
			  byteArray[j++] = (byte)(((high_nibble << 4) & 0xf0) | (low_nibble & 0x0f));
			}
		return byteArray;
	}
	
	/**
	 * Legacy logic copied from BE
	 * TODO: remove this way of get card type. Looks like a bomb.
	 */
	public static String getCardType(String cardNum) {
		
		String s = "";

		if(cardNum.startsWith("4")) {
			s = "Visa";
		} else if(cardNum.startsWith("5") || cardNum.startsWith("2")) {
			s = "Mastercard";
		} else if(cardNum.startsWith("34") || cardNum.startsWith("37")) {
			s = "Amex";
		}

		//20161201 JCB enhancement
		else if (cardNum.startsWith("2131") || cardNum.startsWith("1800") || cardNum.startsWith("35")) {
			s = "Jcb";
		}

		return s;
	}
	
	public static String getVpcCardType(String creditCardType) {
		
		switch (creditCardType) {
		case "visa":
			return "Visa";
		case "mastercard":
			return "Mastercard";
		case "jcb":
			return "Jcb";
		case "amex":
			return "Amex";
		default:
			return "";
		}
	}

	public static String getDisplayPaymentMethod(String creditCardType) {
		switch (creditCardType) {
		case "visa":
			return "Visa";
		case "mastercard":
			return "MasterCard";
		case "jcb":
			return "JCB";
		case "unionpay":
			return "Union Pay";
		case "amex":
			return "American Express";
		case "diners":
			return "Diners";
		default:
			return "";
		}
	}
	
	public static String getCreditCardType(String paymentMethodCode) {
		if (!paymentMethodCode.contains("E_VOUCHER")) {
			if (paymentMethodCode.contains("MASTER")) {
				return "mastercard";
			}
			else if (paymentMethodCode.contains("VISA")) {
				return "visa";
			}
			else if (paymentMethodCode.contains("DINERS")) {
				return "diners";
			}
			else if (paymentMethodCode.contains("CUP")) {
				return "unionpay";
			}
			else if (paymentMethodCode.contains("JCB")) {
				return "jcb";
			}
			else if (paymentMethodCode.contains("AMEX")) {
				return "amex";
			}
		}
		return null;
	}

	/*
	 * This function is called to display payment method in order confirmation page
	 */
	public static String getOrderPaymentDisplayType(String paymentMethodCode) {
		
		if (paymentMethodCode.contains("MASTER")) {
			return "MasterCard";
		}
		else if (paymentMethodCode.contains("VISA")) {
			return "Visa";
		}
		else if (paymentMethodCode.contains("DINERS")) {
			return "Diners";
		}
		else if (paymentMethodCode.contains("CUP")) {
			return "Union Pay";
		}
		else if (paymentMethodCode.contains("JCB")) {
			return "JCB";
		}
		else if (paymentMethodCode.contains("AMEX")) {
			return "American Express";
		}
		else if (paymentMethodCode.contains("E_VOUCHER")) {
			return "eVoucher";
		}
		return "";
	}
	
	/**
	 * Mapping function for MASTERPASS
	 * no support for CUP unionpay
	 */
	public static String getMasterpassCreditCardType(String brandId) {
		if (brandId.equals("master")) {
			return "mastercard";
		}
		else if (brandId.equals("visa")) {
			return "visa";
		}
		else if (brandId.equals("diners")) {
			return "diners";
		}
		else if (brandId.equals("jcb")) {
			return "jcb";
		}
		else if (brandId.equals("amex")) {
			return "amex";
		}
		return null;
	}
	
	static String hex(byte[] input) {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }
	
    public static  String getResponseErrCode(String vResponseCode) {

        String result = "";

        // check if a single digit response code
        //if (vResponseCode.length() != 1) {

            // Java cannot switch on a string so turn everything to a char
            char input = vResponseCode.charAt(0);

            switch (input){
               //case '0' :
                case '1' :
                case '2' :
                case '3' :
                case '4' :
                case '5' :
                case '6' :
                case '7' :
                case '8' :
                case '9' :
                case 'A' :
                case 'C' :
                case 'D' :
                case 'E' :
                case 'F' :
                case 'I' :
                case 'L' :
               //case 'N' :
                case 'P' :
                case 'R' :
                case 'S' :
                case 'T' :
                case 'U' :
                case 'V' :
             	   	result = String.valueOf(input); break;
            }

        //}
        return result;
    }
    
    public static String get3DSErrCode(String vStatus) {

        String result = "";
        if (vStatus != null && !vStatus.equals("")) {

            if (vStatus.equalsIgnoreCase("Unsupported")  || vStatus.equals("No Value Returned")) {
                result = "1"; //"3DS not supported or there was no 3DS data provided";
            } else {

                // Java cannot switch on a string so turn everything to a character
                char input = vStatus.charAt(0);

                switch (input){
                   //case 'Y'  :
                   //case 'E'  :
                    case 'N'  :
                    case 'U'  :
                    case 'F'  :
                    case 'A'  :
                    case 'D'  :
                   //case 'C'  :
                    case 'S'  :
                    case 'T'  :
                    case 'P'  :
                    case 'I'  :
                  	  result = String.valueOf(input); break;
                }
            }
        }
        return result;
    }

}
