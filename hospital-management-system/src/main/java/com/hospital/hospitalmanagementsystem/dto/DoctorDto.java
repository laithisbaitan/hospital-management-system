package com.hospital.hospitalmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDto {
    
    private Long id;
    private String name;
    private String speciality;

    public DoctorDto(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }
}
