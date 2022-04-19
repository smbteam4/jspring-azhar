package com.assignment.robot.client;

import com.assignment.robot.dto.Robot;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RobotClient {

  private final RestTemplate restTemplate;
  private final String ROBOT_URL_ENDPOINT = "robotcpu";

  @Autowired
  public RobotClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<Robot> getRobots() {
    ResponseEntity<Robot[]> response = restTemplate.getForEntity(
      ROBOT_URL_ENDPOINT,
      Robot[].class);
    return Arrays.asList(response.getBody());
  }
}
