package com.assignment.robot.service;

import static org.junit.jupiter.api.Assertions.*;

import com.assignment.robot.client.RobotClient;
import com.assignment.robot.dto.Robot;
import com.assignment.robot.repository.SurvivorRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RobotServiceImplTest {


  @Mock
  RobotClient robotClient;

  private RobotServiceImpl robotService;

  @BeforeEach
  void init() {
    robotService = new RobotServiceImpl(robotClient);
  }


  @Test
  void testShouldReturnAllRobotsCategoryWise() {
    //Given
    Robot robot1 = Robot.builder().category("Land").model("model").serialNumber("1334").build();
    Robot robot2 = Robot.builder().category("Fly").model("model").serialNumber("13224").build();
    Robot robot3 = Robot.builder().category("Land").model("model").serialNumber("332").build();

    List<Robot> robots = List.of(robot1, robot2, robot3);
    Mockito.when(robotClient.getRobots()).thenReturn(robots);

    //When
    Map<String, List<Robot>> allRobots = robotService.getAllRobots();

    //Then
    assertEquals(1,allRobots.get("Fly").size());
    assertEquals(2,allRobots.get("Land").size());
  }
}
