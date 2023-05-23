package com.hospital.hospitalmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {
    private Long id;
    private int dosage;
    private String medication;
    private Long appointmentid;

    public PrescriptionDto(int dosage, String medication, Long appointmentid) {
        this.dosage = dosage;
        this.medication = medication;
        this.appointmentid = appointmentid;
    }
}
