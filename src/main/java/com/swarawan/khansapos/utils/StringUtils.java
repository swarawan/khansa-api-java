package com.swarawan.khansapos.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {

    public static final String BLANK = "";

    private StringUtils() {
        throw new IllegalStateException("String utility class");
    }

    public static String encrypt(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(plain.getBytes());
            StringBuilder builder = new StringBuilder();

            for (byte b : bytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            return BLANK;
        }
    }
}
