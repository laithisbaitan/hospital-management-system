package com.hospital.hospitalmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private Long phoneNum;

    public PatientDto(String name, Long phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }
}
