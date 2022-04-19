package com.assignment.robot.repository;

import com.assignment.robot.entities.InfectedLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfectedLogRepository extends MongoRepository<InfectedLog, String> {

  Long countByInfectedSurvivorIdAndReporterId(String infectedSurvivorId, String reporterId);

  Long countByInfectedSurvivorId(String infectedSurvivorId);
}
