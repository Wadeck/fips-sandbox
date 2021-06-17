package ch.wadeck.fips.libb;

import ch.wadeck.fips.liba.LibAClass1;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class LibBClass1 {
    public void hellFromB() {
        int fromA = new LibAClass1().computeValue();
        String nameMd5 = new LibAClass1().md5AndBase64("My name");
        System.out.println("hello from B with " + fromA + ", " + nameMd5);
    }

    public String encodeMd5(String value) {
        LibAClass1 a1 = new LibAClass1();
        byte[] bytes = a1.messageDigest(value, "MD5", null);
        return a1.base64Encode(bytes);
    }

    public String encodeSha1(String value) {
        LibAClass1 a1 = new LibAClass1();
        byte[] bytes = a1.messageDigest(value, "SHA-1", null);
        return a1.base64Encode(bytes);
    }

    public String encodeSha256(String value) {
        LibAClass1 a1 = new LibAClass1();
        byte[] bytes = a1.messageDigest(value, "SHA-256", null);
        return a1.base64Encode(bytes);
    }

    public String encodeSha256_sun(String value) {
        LibAClass1 a1 = new LibAClass1();
        byte[] bytes = a1.messageDigest(value, "SHA-256", "SUN");
        return a1.base64Encode(bytes);
    }

    public String encryptAndBase64_AES_ECB_PKCS5Padding(String value) {
        LibAClass1 a1 = new LibAClass1();
        // ECB is ignored
        Cipher cipher = a1.createCipher("AES/ECB/PKCS5Padding", null);

        byte[] keyBytes = a1.getFixedKey(128);
        SecretKey secretKey = a1.createSecretKey(keyBytes, "AES");
        a1.configureEncryptMode(cipher, secretKey);

        byte[] result = a1.encryptWith(value.getBytes(StandardCharsets.UTF_8), cipher);

        return a1.base64Encode(result);
    }

    public String encryptAndBase64_AES_CBC_PKCS5Padding(String value) {
        LibAClass1 a1 = new LibAClass1();
        Cipher cipher = a1.createCipher("AES/CBC/PKCS5Padding", null);

        byte[] keyBytes = a1.getFixedKey(128);
        SecretKey secretKey = a1.createSecretKey(keyBytes, "AES");
        a1.configureEncryptMode(cipher, secretKey);

        byte[] result = a1.encryptWith(value.getBytes(StandardCharsets.UTF_8), cipher);

        return a1.base64Encode(result);
    }
}
