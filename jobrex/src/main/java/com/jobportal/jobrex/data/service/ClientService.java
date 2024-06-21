package com.jobportal.jobrex.data.service;

import com.jobportal.jobrex.data.model.Client;
import com.jobportal.jobrex.data.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    @Transactional
    public Client signUpClient(String name, String email){
        String apiKey = UUID.randomUUID().toString();

        if(clientRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("Email address is already exists");
        }

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setApiKey(apiKey);


        return clientRepository.save(client);
    }

    public boolean isValidApiKey(String apiKey) {

        return clientRepository.findByApiKey(apiKey).isPresent();
    }
}
