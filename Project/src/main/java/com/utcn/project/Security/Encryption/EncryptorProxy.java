package com.utcn.project.Security.Encryption;

import java.security.NoSuchAlgorithmException;

public class EncryptorProxy implements Encryptor {
    private static EncryptorHeavy encryptor;

    public EncryptorProxy() {
    }

    private Boolean initHeavyObject() {
        if (encryptor == null) {
            try {
                encryptor = new EncryptorHeavy();
                System.out.println("Encryptor initialized successfully");
                return Boolean.TRUE;
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Encryptor initialization failed!");
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public String encrypt(String password) {
        if (initHeavyObject())
            return encryptor.encrypt(password);
        return password;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (initHeavyObject())
            return encryptor.encode(rawPassword);
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (initHeavyObject())
            return encryptor.matches(rawPassword, encodedPassword);
        return false;
    }
}
