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
public class Patient {
    @Id
    @GeneratedValue
    private Long Pid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private Long phoneNum;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;

}
