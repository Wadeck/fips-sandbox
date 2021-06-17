package ch.wadeck.fips.liba;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class LibAClass1 {
    public void hellFromA() {
        System.out.println("hello from A");
    }

    public int computeValue() {
        return 42;
    }

    public String md5AndBase64(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md.update(value.getBytes(StandardCharsets.UTF_8));

        byte[] bytesResult = md.digest();
        return base64Encode(bytesResult);
    }

    public String md5AndBase64_step2(String value) {
        return md5AndBase64(value);
    }

    public String md5AndBase64_step3(String value) {
        return md5AndBase64_step2(value);
    }

    public String md5AndBase64_step4(String value) {
        return md5AndBase64_step3(value);
    }

    public byte[] messageDigest(String value, String algorithm, String provider) {
        MessageDigest md;
        try {
            if (provider == null) {
                md = MessageDigest.getInstance(algorithm);
            } else {
                md = MessageDigest.getInstance(algorithm, provider);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

        md.update(value.getBytes(StandardCharsets.UTF_8));

        byte[] bytesResult = md.digest();
        return bytesResult;
    }

    public String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] base64Decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    public byte[] encryptWith(byte[] plainTextBytes, Cipher cipher) {
        try {
            return cipher.doFinal(plainTextBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public Cipher createCipher(String algorithm, String provider) {
        Cipher cipher = null;
        try {
            if (provider == null) {
                cipher = Cipher.getInstance(algorithm);

            } else {
                cipher = Cipher.getInstance(algorithm, provider);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }

        return cipher;
    }

    public SecretKey createSecretKey(byte[] keyBytes, String keyAlgorithm) {
        SecretKey secretKey = new SecretKeySpec(keyBytes, keyAlgorithm);
        return secretKey;
    }

    public void configureEncryptMode(Cipher cipher, SecretKey secretKey) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getFixedKey(int keySizeInBit) {
        if (keySizeInBit % 8 != 0) {
            throw new IllegalArgumentException("Key size must be a multiple of 8.");
        }

        String fixedBase64 = "pO3j2XGiNEImUbZrIGEQUOUeyVOGX25z7RRD7EaCgXLBliPQt0g2rT+TDPJgOUSthNAv7Gj9f6eKQbfyphqQEzh0in5zCSFk1dB0Zzv2t/yPwK5n9uOmAAty2I9sRk+Kcw5PwgojeOOjPoVG++FRoNYkEsdL3Lak5L/QYjKpKsQ=";
        byte[] fixedBytes = base64Decode((fixedBase64));
        System.out.println("Total size of the bytes: " + fixedBytes.length);
        return Arrays.copyOf(fixedBytes, keySizeInBit / 8);
    }

    public byte[] generateRandomKey(int keySizeInBit) {
        if (keySizeInBit % 8 != 0) {
            throw new IllegalArgumentException("Key size must be a multiple of 8.");
        }

        byte[] result = new byte[keySizeInBit / 8];
        try {
            SecureRandom.getInstanceStrong().nextBytes(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public byte[] encryptECB(String value) {
        Cipher cipher = createCipher("AES", null);

        byte[] keyBytes = getFixedKey(128);
        SecretKey secretKey = createSecretKey(keyBytes, "AES");
        configureEncryptMode(cipher, secretKey);

        byte[] result = encryptWith(value.getBytes(StandardCharsets.UTF_8), cipher);

        return result;
    }
}
