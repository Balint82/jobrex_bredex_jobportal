package com.jobportal.jobrex.data.init;

import com.jobportal.jobrex.data.model.Client;
import com.jobportal.jobrex.data.model.Position;
import com.jobportal.jobrex.data.repository.ClientRepository;
import com.jobportal.jobrex.data.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class DataInitializer implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public DataInitializer(ClientRepository clientRepository, PositionRepository positionRepository){
        this.clientRepository = clientRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Client> initClientList = Arrays.asList(
                new Client("Thomas Anderson", "tom.anderson@metacortex.com","550e8400-e29b-41d4-a716-446655440000"),
                new Client("Tiffany Smith", "tiff.doe@example.com","660e8400-e29b-41d4-a716-446655440111"),
                new Client("Mark Twain", "mark.twain@bookseller.com", "770e8400-e29b-41d4-a716-446655440222")
        );

        for (Client client : initClientList) {
            Optional<Client> clientOpt = clientRepository.findByEmail(client.getEmail());
            if (clientOpt.isEmpty()) {
                clientRepository.save(client);
            }
        }

        if (positionRepository.count() == 0) {
            insertInitialPositions();
        }
    }
    private void insertInitialPositions() {
        // Get a sample client to associate with positions
        Optional<Client> clientOpt = clientRepository.findByEmail("tom.anderson@metacortex.com");

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            Position posFirst = new Position("Software Developer", "Dobos", client);
            Position posSec = new Position("Hardware Developer", "Pilis", client);
            Position posThird = new Position("Software Engineer", "Dobos", client);
            Position posFourth = new Position("IT Developer", "Szántód", client);

            positionRepository.saveAll(Arrays.asList(posFirst, posSec, posThird, posFourth));
        }

    }
}

