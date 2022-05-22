package com.utcn.project.Security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor implements PasswordEncoder {
    private final MessageDigest messageDigest;

    public Encryptor() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("MD5");
    }

    public String encrypt(String password) {
        messageDigest.update(password.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
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
