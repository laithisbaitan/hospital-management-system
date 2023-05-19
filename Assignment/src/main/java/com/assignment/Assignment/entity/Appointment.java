package com.assignment.Assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table
public class Appointment {
    @Id
    @GeneratedValue
    private long Aid;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "Did")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_id",referencedColumnName = "Pid")
    private Patient patient;

    @OneToMany(mappedBy = "appointment")
    private Set<Prescription> prescriptions;
}
