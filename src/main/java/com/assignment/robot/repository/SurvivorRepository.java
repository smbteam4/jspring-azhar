package com.assignment.robot.repository;

import com.assignment.robot.entities.Survivor;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurvivorRepository extends MongoRepository<Survivor, String> {

  List<Survivor> findByIsInfectedTrue();
  List<Survivor> findByIsInfectedFalse();

  Long countByIsInfectedTrue();

  Long countByIsInfectedFalse();
}
