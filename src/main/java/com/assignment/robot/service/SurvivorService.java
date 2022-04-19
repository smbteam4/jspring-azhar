package com.assignment.robot.service;


import com.assignment.robot.entities.InfectedLog;
import com.assignment.robot.entities.Location;
import com.assignment.robot.entities.ResourceInventory;
import com.assignment.robot.entities.Survivor;
import java.util.List;

public interface SurvivorService {

  void createSurvivor(Survivor survivor);

  void createSurvivorList(List<Survivor> survivor);

  boolean updateInventory(String survivorId, ResourceInventory resource);

  boolean updateLocation(String survivorId, Location location);

  boolean reportInfectedSurvivor(InfectedLog reportInfected);

  List<Survivor> getAllSurvivors();
}
