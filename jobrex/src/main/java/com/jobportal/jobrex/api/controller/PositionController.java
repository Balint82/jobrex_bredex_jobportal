package com.jobportal.jobrex.api.controller;

import com.jobportal.jobrex.UnifiedExpHandler;
import com.jobportal.jobrex.api.dto.PositionDTO;
import com.jobportal.jobrex.data.model.Position;
import com.jobportal.jobrex.data.service.ClientService;
import com.jobportal.jobrex.data.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
public class PositionController {
    private final PositionService positionService;
    private final ClientService clientService;
    private final UnifiedExpHandler unifiedExpHandler;

    @Autowired
    public PositionController(PositionService positionService, ClientService clientService,UnifiedExpHandler unifiedExpHandler){
        this.positionService = positionService;
        this.clientService = clientService;
        this.unifiedExpHandler = unifiedExpHandler;
    }

    //2.
    @PostMapping("/position")
    public ResponseEntity<String> producePosition(@Valid @RequestBody PositionDTO request, @RequestHeader("API-Key") String apiKey){
        try {
            Position position = positionService.createPosition(request.getPosName(), request.getLocation(), apiKey);
            String positionUrl = "/position/" + position.getPosId();

            return ResponseEntity.ok("Position created succesfully. URL: " + positionUrl);
        } catch(Exception e){
            ResponseEntity<Object> responseEntity = unifiedExpHandler.handleGeneralException(e);

            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }
    }

    //3.
    @GetMapping("/position/{id}")
    public ResponseEntity<Position> getPosition(@PathVariable(value = "id") Long posId, @RequestHeader("Api-Key") String apiKey){
        try {
            if (!clientService.isValidApiKey(apiKey)) {

                return ResponseEntity.status(401).body(null); //Unauthorized status code.
            } else {
                Position position = positionService.getPositionById(posId);

                return ResponseEntity.ok(position);
            }
        } catch (Exception e){
            ResponseEntity<Object> responseEntity = unifiedExpHandler.handleGeneralException(e);

            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }
    }

    @GetMapping("/position/search")
    public ResponseEntity<List<String>> searchPosition(@RequestHeader("API-key") String apiKey,
                                                       @RequestParam(name = "Job title") @Size(max = 50) String keyword,
                                                       @RequestParam(name = "Location") @Size(max = 50) String location){
        try{
            List<String> positionURLs = positionService.searchPositions(apiKey, keyword, location);

            return ResponseEntity.ok(positionURLs);
        }catch (IllegalArgumentException e){

            return ResponseEntity.status(401).body(null);
        }catch (Exception e){
            ResponseEntity<Object> responseEntity = unifiedExpHandler.handleGeneralException(e);

            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }
    }
}

