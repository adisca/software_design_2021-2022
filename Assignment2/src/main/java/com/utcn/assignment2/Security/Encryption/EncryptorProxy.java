package com.utcn.assignment2.Security.Encryption;

import java.security.NoSuchAlgorithmException;

public class EncryptorProxy implements Encryptor {
    private static EncryptorHeavy encryptor;

    public EncryptorProxy() {
    }

    @Override
    public String encrypt(String password) {
        if (encryptor == null) {
            try {
                encryptor = new EncryptorHeavy();
                System.out.println("Encryptor initialized successfully");
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Encryptor initialization failed!");
                return password;
            }
        }
        return encryptor.encrypt(password);
    }
}
