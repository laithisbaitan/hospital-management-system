package com.hospital.hospitalmanagementsystem.Entity;

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
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private Long phoneNum;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;

}
