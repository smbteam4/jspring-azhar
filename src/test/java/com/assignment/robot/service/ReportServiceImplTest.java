package com.assignment.robot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.assignment.robot.entities.Gender;
import com.assignment.robot.entities.Survivor;
import com.assignment.robot.repository.SurvivorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

  @Mock
  SurvivorRepository survivorRepository;
  private ReportServiceImpl reportService;

  @BeforeEach
  void init() {
    reportService = new ReportServiceImpl(survivorRepository);
  }


  @Test
  void testShouldReturnInfectedSurvivors() {
    // Given
    List<Survivor> survivors = new ArrayList<>();
    Survivor survivor1 = Survivor.builder()
      .name("Azhar")
      .isInfected(true)
      .gender(Gender.MALE)
      .build();
    Survivor survivor2 = Survivor.builder()
      .name("Amal")
      .gender(Gender.MALE)
      .isInfected(true).build();
    survivors.add(survivor1);
    survivors.add(survivor2);

    when(survivorRepository.findByIsInfectedTrue()).thenReturn(survivors);

    // When
    List<Survivor> actualResult = reportService.getInfectedSurvivors();

    // Then
    assertEquals(survivors, actualResult);
  }

  @Test
  void testShouldReturnNonInfectedSurvivors() {
    // Given
    List<Survivor> survivors = new ArrayList<>();
    Survivor survivor1 = Survivor.builder()
      .name("Azhar")
      .isInfected(false)
      .gender(Gender.MALE)
      .build();
    Survivor survivor2 = Survivor.builder()
      .name("Amal")
      .gender(Gender.MALE)
      .isInfected(false).build();
    survivors.add(survivor1);
    survivors.add(survivor2);

    when(survivorRepository.findByIsInfectedFalse()).thenReturn(survivors);

    // When
    List<Survivor> actualResult = reportService.getNonInfectedSurvivors();

    // Then
    assertEquals(survivors, actualResult);
  }

  @Test
  void testShouldReturnInfectedPercentage() {
    // Given
    when(survivorRepository.count()).thenReturn(10L);
    when(survivorRepository.countByIsInfectedTrue()).thenReturn(7L);

    // When
    Map<String, Double> actualResult = reportService.getInfectedPercentage();

    // Then
    assertEquals(70.00, actualResult.get("infectedPercentage"));
  }

  @Test
  void testShouldReturnNonInfectedPercentage() {
    // Given
    when(survivorRepository.count()).thenReturn(10L);
    when(survivorRepository.countByIsInfectedFalse()).thenReturn(7L);

    // When
    Map<String, Double> actualResult = reportService.getNonInfectedPercentage();

    // Then
    assertEquals(70.00, actualResult.get("nonInfectedPercentage"));
  }


}
