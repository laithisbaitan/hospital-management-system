package com.assignment.Assignment.entity;

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
    private long Presid;

    @Column
    private int dosage;

    @Column
    private String medication;

    @ManyToOne
    @JoinColumn(name="appointment_id", referencedColumnName = "Aid")
    private Appointment appointment;

}
