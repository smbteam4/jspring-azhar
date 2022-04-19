package com.assignment.robot.service;

import com.assignment.robot.client.RobotClient;
import com.assignment.robot.dto.Robot;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RobotServiceImpl implements RobotService {

  private final RobotClient robotClient;

  @Autowired
  public RobotServiceImpl(RobotClient robotClient) {
    this.robotClient = robotClient;
  }

  @Override
  public Map<String, List<Robot>> getAllRobots() {
    List<Robot> robots = robotClient.getRobots();
    return robots.stream().collect(Collectors.groupingBy(Robot::getCategory));
  }
}
