package com.assignment.robot.service;


import com.assignment.robot.entities.InfectedLog;
import com.assignment.robot.entities.Location;
import com.assignment.robot.entities.ResourceInventory;
import com.assignment.robot.entities.Survivor;
import com.assignment.robot.repository.InfectedLogRepository;
import com.assignment.robot.repository.SurvivorRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SurvivorServiceImpl implements SurvivorService {

  private final SurvivorRepository survivorRepository;
  private final InfectedLogRepository infectedLogRepository;
  private final Integer REPORTED_THRESHOLD = 3;

  @Autowired
  public SurvivorServiceImpl(SurvivorRepository survivorRepository,
    InfectedLogRepository infectedLogRepository) {
    this.survivorRepository = survivorRepository;
    this.infectedLogRepository = infectedLogRepository;
  }

  @Override
  public void createSurvivor(Survivor survivor) {
    survivorRepository.save(survivor);
  }

  @Override
  public void createSurvivorList(List<Survivor> survivors) {
    survivorRepository.saveAll(survivors);
  }


  @Override
  public boolean updateInventory(String survivorId, ResourceInventory resource) {
    Optional<Survivor> survivorById = survivorRepository.findById(survivorId);

    if (survivorById.isEmpty()) {
      throw new RuntimeException("Survivor Not exist");
    }
    Survivor survivorToUpdate = survivorById.get();
    survivorToUpdate.setResource(new ResourceInventory(resource.getWater(),
      resource.getFood(),
      resource.getMedication(),
      resource.getAmmunition()));
    survivorRepository.save(survivorToUpdate);
    return true;
  }

  @Override
  public boolean updateLocation(String survivorId, Location location) {
    Optional<Survivor> survivorById = survivorRepository.findById(survivorId);

    if (survivorById.isEmpty()) {
      throw new RuntimeException("Survivor Not exist");
    }
    Survivor survivorToUpdate = survivorById.get();
    survivorToUpdate.setLastKnownLocation(new Location(location.getLatitude(),
      location.getLongitude()));
    survivorRepository.save(survivorToUpdate);
    return true;
  }


  @Override
  public boolean reportInfectedSurvivor(InfectedLog infectedLog) {
    Optional<Survivor> survivorById = survivorRepository
      .findById(infectedLog.getInfectedSurvivorId());
    if (survivorById.isEmpty()) {
      throw new RuntimeException("Survivor Not exist");
    }
    Long reportedCount = infectedLogRepository
      .countByInfectedSurvivorIdAndReporterId(infectedLog.getInfectedSurvivorId(),
        infectedLog.getReporterId());

    if (reportedCount != 0) {
      throw new RuntimeException("Already reported");
    }
    infectedLog.setTimestamp(LocalDateTime.now());
    infectedLogRepository.save(infectedLog);

    Long infectedCount = infectedLogRepository
      .countByInfectedSurvivorId(infectedLog.getInfectedSurvivorId());

    if (infectedCount >= REPORTED_THRESHOLD) {
      Survivor survivorToUpdate = survivorById.get();
      survivorToUpdate.setInfected(true);
      survivorRepository.save(survivorToUpdate);
    }
    return true;
  }

  @Override
  public List<Survivor> getAllSurvivors() {
    return survivorRepository.findAll();
  }

}
