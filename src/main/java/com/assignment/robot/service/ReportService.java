package com.assignment.robot.service;

import com.assignment.robot.entities.Survivor;
import java.util.List;
import java.util.Map;

public interface ReportService {

  List<Survivor> getInfectedSurvivors();

  List<Survivor> getNonInfectedSurvivors();

  Map<String, Double> getInfectedPercentage();

  Map<String, Double> getNonInfectedPercentage();
}
