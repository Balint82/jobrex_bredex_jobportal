package com.jobportal.jobrex.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PositionDTO {
    @NotBlank
    @Size(max = 50)
    private String posName;

    @NotBlank
    @Size(max = 50)
    private String location;

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
}

