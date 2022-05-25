package com.utcn.project.Security.Encryption;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface Encryptor extends PasswordEncoder {
    String encrypt(String password);
}
