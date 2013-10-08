package com.edition.guard.utils;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Signature {
private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";


/**
* Computes RFC 2104-compliant HMAC signature.
* * @param data
* The data to be signed.
* @param key
* The signing key.
* @return
* The Base64-encoded RFC 2104-compliant HMAC signature.
* @throws
* java.security.SignatureException when signature generation fails
*/

public static String calculateRFC2104HMAC(String data, String key)
throws java.security.SignatureException
{
		String result;
		try {
		
		// get an hmac_sha1 key from the raw key bytes
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
		
		// get an hmac_sha1 Mac instance and initialize with the signing key
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		
		// compute the hmac on input data bytes
		byte[] rawHmac = mac.doFinal(data.getBytes());
		
		// base64-encode the hmac
		result = Base64.encodeBytes(rawHmac);

			} catch (Exception e) {
			throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
			}
			return result;
			}
public static String hmacSha1(String value, String key) {
    try {
        // Get an hmac_sha1 key from the raw key bytes
        byte[] keyBytes = Base64.decode(key.getBytes());           
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

        // Get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        // Compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(value.getBytes());

        // Convert raw bytes to Hex
        byte[] hexBytes = new Hex().encode(rawHmac);

        //  Covert array of Hex bytes to a String
        return new String(hexBytes, "UTF-8");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

public static String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {

    SecretKey secretKey = null;

    byte[] keyBytes = keyString.getBytes();
    secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");

    Mac mac = Mac.getInstance("HmacSHA1");

    mac.init(secretKey);

    byte[] text = baseString.getBytes();

    return new String(Base64.encodeBytes(mac.doFinal(text))).trim();
}
}