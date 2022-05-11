package com.utcn.assignment3.Util;

import com.utcn.assignment3.Model.Admin;
import com.utcn.assignment3.Model.Client;
import com.utcn.assignment3.Security.Encryption.EncryptorProxy;

public final class TestUtils {
    public static Client setupClient(Long id, String username, String password, Boolean encrypt) {
        Client client = new Client();
        if (id != null)
            client.setId(id);
        client.setUsername(username);

        if (encrypt) {
            EncryptorProxy encryptor = new EncryptorProxy();
            client.setPassword(encryptor.encrypt(password));
        }
        else
            client.setPassword(password);

        return client;
    }

    public static Admin setupAdmin(Long id, String username, String password, Boolean encrypt) {
        Admin client = new Admin();
        if (id != null)
            client.setId(id);
        client.setName(username);

        if (encrypt) {
            EncryptorProxy encryptor = new EncryptorProxy();
            client.setPassword(encryptor.encrypt(password));
        }
        else
            client.setPassword(password);

        return client;
    }
}
