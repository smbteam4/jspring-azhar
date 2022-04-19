package com.assignment.robot.service;

import com.assignment.robot.dto.Robot;
import java.util.List;
import java.util.Map;

public interface RobotService {

  Map<String, List<Robot>> getAllRobots();
}
