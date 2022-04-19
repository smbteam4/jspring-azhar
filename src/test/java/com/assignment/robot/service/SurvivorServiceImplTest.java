package com.assignment.robot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.assignment.robot.entities.Gender;
import com.assignment.robot.entities.Location;
import com.assignment.robot.entities.Survivor;
import com.assignment.robot.repository.InfectedLogRepository;
import com.assignment.robot.repository.SurvivorRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SurvivorServiceImplTest {

  @Mock
  SurvivorRepository survivorRepository;
  @Mock
  InfectedLogRepository infectedLogRepository;

  private SurvivorServiceImpl survivorService;

  @BeforeEach
  void init() {
    survivorService = new SurvivorServiceImpl(survivorRepository, infectedLogRepository);
  }

  @Test
  void testShouldAddSurvivorToDB() {
    // Given
    Survivor survivor = Survivor.builder()
      .name("Azhar")
      .age(30)
      .gender(Gender.MALE)
      .lastKnownLocation(new Location("212142", "124124"))
      .isInfected(false).build();

    when(survivorRepository.save(survivor)).thenReturn(survivor);

    // When
    survivorService.addSurvivor(survivor);

    // Then
    ArgumentCaptor<Survivor> survivorArgumentCaptor = ArgumentCaptor.forClass(Survivor.class);
    verify(survivorRepository).save(survivorArgumentCaptor.capture());
    Survivor actual = survivorArgumentCaptor.getValue();
    assertEquals(survivor, actual);
  }

  @Test
  void testShouldAddMultipleSurvivorToDB() {
    // Given
    List<Survivor> survivorList = new ArrayList<>();

    Survivor survivor1 = Survivor.builder()
      .name("Azhar")
      .age(30)
      .gender(Gender.MALE)
      .lastKnownLocation(new Location("212142", "124124"))
      .isInfected(false).build();

    Survivor survivor2 = Survivor.builder()
      .name("Asha")
      .age(30)
      .gender(Gender.FEMALE)
      .lastKnownLocation(new Location("212142", "124124"))
      .isInfected(false).build();
    survivorList.add(survivor1);
    survivorList.add(survivor2);

    when(survivorRepository.saveAll(survivorList)).thenReturn(survivorList);

    // When
    survivorService.addMultipleSurvivors(survivorList);

    // Then
    ArgumentCaptor<List<Survivor>> survivorArgumentCaptor = ArgumentCaptor.forClass(List.class);
    verify(survivorRepository).saveAll(survivorArgumentCaptor.capture());
    List<Survivor> actual = survivorArgumentCaptor.getValue();
    assertEquals(survivorList, actual);
  }

}
