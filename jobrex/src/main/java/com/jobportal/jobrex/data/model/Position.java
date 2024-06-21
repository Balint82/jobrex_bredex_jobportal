package com.jobportal.jobrex.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posId;
    @NotBlank
    @Size(max = 50)
    private String posName;
    @NotBlank
    @Size(max = 50)
    private String location;

    @ManyToOne
    @JoinColumn()
    private Client client;


    public Position() {
    }

    public Position(String posName, String location, Client client) {
        this.posName = posName;
        this.location = location;
        this.client = client;
    }


    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
