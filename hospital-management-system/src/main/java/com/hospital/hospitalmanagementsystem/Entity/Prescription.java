package com.hospital.hospitalmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table
public class Prescription {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int dosage;

    @Column
    private String medication;

    @ManyToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "id")
    private Appointment appointment;

}
