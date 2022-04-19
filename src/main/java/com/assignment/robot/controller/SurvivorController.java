package com.assignment.robot.controller;

import com.assignment.robot.dto.Response;
import com.assignment.robot.entities.InfectedLog;
import com.assignment.robot.entities.Location;
import com.assignment.robot.entities.ResourceInventory;
import com.assignment.robot.entities.Survivor;
import com.assignment.robot.service.SurvivorService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(path = "/api/survivor")
public class SurvivorController {

  private final SurvivorService survivorService;

  @Autowired
  public SurvivorController(SurvivorService survivorService) {
    this.survivorService = survivorService;
  }

  @GetMapping("/")
  public List<Survivor> getAllSurvivors() {
    return survivorService.getAllSurvivors();
  }


  @PostMapping("/add")
  public ResponseEntity<Response> addSurvivor(@Valid @RequestBody Survivor survivor) {
    survivorService.addSurvivor(survivor);
    return new ResponseEntity<>(Response.builder()
      .message("Added Survivor Successfully")
      .success(true).build(), HttpStatus.CREATED);
  }


  @PostMapping("/add-all")
  public ResponseEntity<Response> addSurvivorList(@Valid @RequestBody List<Survivor> survivors) {
    survivorService.addMultipleSurvivors(survivors);
    return new ResponseEntity<>(Response.builder()
      .message("Added All Survivor Successfully")
      .success(true).build(), HttpStatus.CREATED);
  }


  @PatchMapping("/update-inventory")
  public ResponseEntity<Response> updateInventory(@RequestParam String survivorId,
    @Valid @RequestBody ResourceInventory resource) {
    survivorService.updateInventory(survivorId, resource);
    return new ResponseEntity<>(Response.builder()
      .message("Survivor inventory Updated")
      .success(true).build(), HttpStatus.OK);
  }


  @PatchMapping("/update-location")
  public ResponseEntity<Response> updateLocation(@RequestParam String survivorId,
    @Valid @RequestBody Location location) {
    survivorService.updateLocation(survivorId, location);
    return new ResponseEntity<>(Response.builder()
      .message("Survivor Location Updated")
      .success(true).build(), HttpStatus.OK);
  }

  @PatchMapping("/report-infected-survivor")
  public ResponseEntity<Response> reportInfectedSurvivor(
    @Valid @RequestBody InfectedLog infectedLog) {
    survivorService.reportInfectedSurvivor(infectedLog);
    return new ResponseEntity<>(Response.builder()
      .message("Successfully reported")
      .success(true).build(), HttpStatus.OK);
  }

}
