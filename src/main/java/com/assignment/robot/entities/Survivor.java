package com.assignment.robot.entities;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "survivor")
public class Survivor {

  @Id
  private String id;
  @NotNull(message = "Name is required.")
  private String name;
  @NotNull(message = "Age is required.")
  private Integer age;
  @NotNull(message = "Gender is required.")
  private Gender gender;
  private ResourceInventory resource;
  @NotNull(message = "Location is required.")
  private Location lastKnownLocation;
  private boolean isInfected;
}
