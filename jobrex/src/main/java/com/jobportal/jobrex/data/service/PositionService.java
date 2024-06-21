package com.jobportal.jobrex.data.service;

import com.jobportal.jobrex.data.model.Client;
import com.jobportal.jobrex.data.model.Position;
import com.jobportal.jobrex.data.repository.ClientRepository;
import com.jobportal.jobrex.data.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {
    @Autowired
    private ClientRepository clientRepository; //ApiKey a klienshez tartozik
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ClientService clientService;


    //2. Pozíció létrehozása és klienshez rendelése
    @Transactional
    public Position createPosition(String positionName, String location, String apiKey){
        Optional<Client> clientOpt = clientRepository.findByApiKey(apiKey);
        if(clientOpt.isEmpty()){
            throw new IllegalArgumentException("Invalid API key");
        }

        Client client = clientOpt.get(); //ha van client Obj amikor létrehozza, hozzá is rendeli
        Position position = new Position();
        position.setPosName(positionName);
        position.setLocation(location);
        position.setClient(client);


        return positionRepository.save(position);
    }

    //3. - Position URL-ek listába gyűjtése kifejezés alapján
    public List<String> searchPositions(String apiKey, String keyword, String location){
        List<Position> positions = positionRepository.findByPosNameContainingAndLocationContaining(keyword, location);

        if(!clientService.isValidApiKey(apiKey)){
            throw new IllegalArgumentException("Invalid API key");
        }


        return positions.stream().map(position -> "/position/" + position.getPosId()).collect(Collectors.toList());
    }


    public Position getPositionById(Long id){

        return positionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Position not found."));
    }
}
