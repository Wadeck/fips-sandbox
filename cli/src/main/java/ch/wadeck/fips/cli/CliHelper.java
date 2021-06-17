package ch.wadeck.fips.cli;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CliHelper {
    public static byte[] md5(String value) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(value.getBytes(StandardCharsets.UTF_8));

        byte[] bytesResult = md.digest();
        return bytesResult;
    }
}
