package com.assignment.robot.controller;

import com.assignment.robot.dto.Robot;
import com.assignment.robot.entities.Survivor;
import com.assignment.robot.service.ReportService;
import com.assignment.robot.service.RobotService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/report")
public class ReportController {


  private final ReportService reportService;

  private final RobotService robotService;

  public ReportController(ReportService reportService,
    RobotService robotService) {
    this.reportService = reportService;
    this.robotService = robotService;
  }


  @GetMapping("/list-robots")
  public Map<String, List<Robot>> getAllRobotCPU() {
    return robotService.getAllRobots();
  }

  @GetMapping("/infected-survivors")
  public List<Survivor> getInfectedSurvivors() {
    return reportService.getInfectedSurvivors();
  }

  @GetMapping("/non-infected-survivors")
  public List<Survivor> getNonInfectedSurvivors() {
    return reportService.getNonInfectedSurvivors();
  }

  @GetMapping("/infected-percentage")
  public Map<String, Double> getInfectedPercentage() {
    return reportService.getInfectedPercentage();
  }


  @GetMapping("/non-infected-percentage")
  public Map<String, Double> getNonInfectedPercentage() {
    return reportService.getNonInfectedPercentage();
  }

}
