package com.assignment.robot.entities;

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

public class ResourceInventory {

  private String water;
  private String food;
  private String medication;
  private String ammunition;

}
