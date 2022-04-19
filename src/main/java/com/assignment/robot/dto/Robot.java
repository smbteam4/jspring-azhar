package com.assignment.robot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Robot {

  private String model;
  private String serialNumber;
  private String manufacturedDate;
  private String category;
}
