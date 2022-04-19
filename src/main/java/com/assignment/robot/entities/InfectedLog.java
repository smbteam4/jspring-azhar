package com.assignment.robot.entities;

import java.time.LocalDateTime;
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
@Document(collection = "infected_log")
public class InfectedLog {

  @Id
  private String id;
  @NotNull(message = "Infected Survivor's Id is required.")
  private String infectedSurvivorId;
  private LocalDateTime timestamp;
  @NotNull(message = "Reporting Survivor's Id is required.")
  private String reporterId;

}
