package com.jobportal.jobrex.data.repository;

import com.jobportal.jobrex.data.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PositionRepository extends JpaRepository<Position, Long> {
    //3.
    List<Position> findByPosNameContaining(String posName);

    List<Position> findByPosNameContainingAndLocationContaining(String posName, String location);
}
