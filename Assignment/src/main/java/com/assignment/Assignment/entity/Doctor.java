package com.assignment.Assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table
public class Doctor {
    @Id
    @GeneratedValue
    private Long Did;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "speciality", nullable = false)
    private String speciality;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
}
