package com.teamsparta8.company.presentation.controller;

import com.teamsparta8.company.application.dto.CompanyRequestDto;
import com.teamsparta8.company.application.dto.CompanyResponseDto;
import com.teamsparta8.company.application.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final CompanyService companyService;

  @PostMapping
  public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyRequestDto requestDto) {
    CompanyResponseDto responseDto = companyService.createCompany(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}
