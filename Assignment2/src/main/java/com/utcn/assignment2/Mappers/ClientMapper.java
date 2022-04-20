package com.utcn.assignment2.Mappers;

import com.utcn.assignment2.DTO.ClientDTO;
import com.utcn.assignment2.DTO.LogInDTO;
import com.utcn.assignment2.Model.Client;

public class ClientMapper {
    private static final ClientMapper clientMapper = new ClientMapper();

    private ClientMapper() {}

    public static ClientMapper getInstance() {
        return clientMapper;
    }
    public Client convertFromLogInDTO(LogInDTO dto) {
        Client client = new Client();
        client.setUsername(dto.getCredential());
        client.setPassword(dto.getPassword());
        return client;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setUsername(client.getUsername());

        return dto;
    }
}
