package com.utcn.assignment3.Security.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorHeavy implements Encryptor {
    private MessageDigest messageDigest;

    public EncryptorHeavy() throws NoSuchAlgorithmException {
        heavyInitialConfig();
    }

    @Override
    public String encrypt(String password) {
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
    }

    private void heavyInitialConfig() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("MD5");
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encrypt(rawPassword.toString()).equals(encodedPassword);
    }
}
