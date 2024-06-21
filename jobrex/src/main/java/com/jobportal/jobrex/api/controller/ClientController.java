package com.jobportal.jobrex.api.controller;

import com.jobportal.jobrex.UnifiedExpHandler;
import com.jobportal.jobrex.api.dto.ClientDTO;
import com.jobportal.jobrex.data.model.Client;
import com.jobportal.jobrex.data.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClientController {
    private final ClientService clientService;
    private final UnifiedExpHandler unifiedExpHandler;

    @Autowired
    public ClientController(ClientService clientService, UnifiedExpHandler unifiedExpHandler){
        this.clientService = clientService;
        this.unifiedExpHandler = unifiedExpHandler;
    }


    //1.
    @PostMapping("/client")
    public ResponseEntity<String> regClient(@Valid @RequestBody ClientDTO request){
        try{
            Client client = clientService.signUpClient(request.getName(), request.getEmail());

            return ResponseEntity.ok("Registration success. API Key: " + client.getApiKey());
        } catch (Exception e){
            ResponseEntity<Object> responseEntity = unifiedExpHandler.handleGeneralException(e);

            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }

    }
}

