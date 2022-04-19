package com.assignment.robot.entities;

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
  private String name;
  private Integer age;
  private Gender gender;
  private Location lastKnownLocation;
  private ResourceInventory resource;
  private boolean isInfected;
}
