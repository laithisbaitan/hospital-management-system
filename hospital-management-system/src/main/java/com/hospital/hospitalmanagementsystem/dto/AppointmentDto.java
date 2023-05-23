package com.hospital.hospitalmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date date;
    private Long doctorid;
    private Long patientid;

    public AppointmentDto(Date date, Long doctorid, Long patientid) {
        this.date = date;
        this.doctorid = doctorid;
        this.patientid = patientid;
    }
}
