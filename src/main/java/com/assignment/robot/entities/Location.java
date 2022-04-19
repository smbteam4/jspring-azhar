package com.assignment.robot.entities;

import javax.validation.constraints.NotNull;
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

public class Location {

  @NotNull
  private String latitude;
  @NotNull
  private String longitude;
}
