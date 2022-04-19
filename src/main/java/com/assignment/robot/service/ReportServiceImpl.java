package com.assignment.robot.service;

import com.assignment.robot.entities.Survivor;
import com.assignment.robot.repository.SurvivorRepository;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

  private final SurvivorRepository survivorRepository;

  @Autowired
  public ReportServiceImpl(SurvivorRepository survivorRepository) {
    this.survivorRepository = survivorRepository;
  }

  @Override
  public List<Survivor> getInfectedSurvivors() {
    return survivorRepository.findByIsInfectedTrue();
  }

  @Override
  public List<Survivor> getNonInfectedSurvivors() {
    return survivorRepository.findByIsInfectedFalse();
  }

  @Override
  public Map<String, Double> getInfectedPercentage() {
    long survivorCount = survivorRepository.count();
    long infectedCount = survivorRepository.countByIsInfectedTrue();
    if (infectedCount == 0) {
      return Map.of("infectedPercentage", 0.0);
    }
    double infectedPercentage = (double) (infectedCount * 100) / survivorCount;
    return Map.of("infectedPercentage", infectedPercentage);

  }

  @Override
  public Map<String, Double> getNonInfectedPercentage() {
    long survivorCount = survivorRepository.count();
    long nonInfectedCount = survivorRepository.countByIsInfectedFalse();
    if (nonInfectedCount == 0) {
      return Map.of("nonInfectedPercentage", 0.0);
    }
    double nonInfectedPercentage = (double) (nonInfectedCount * 100) / survivorCount;
    return Map.of("nonInfectedPercentage", nonInfectedPercentage);
  }
}
