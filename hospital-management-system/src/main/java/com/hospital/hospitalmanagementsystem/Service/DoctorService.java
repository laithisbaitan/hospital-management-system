package com.hospital.hospitalmanagementsystem.Service;

import com.hospital.hospitalmanagementsystem.Entity.Doctor;
import com.hospital.hospitalmanagementsystem.dto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.hospitalmanagementsystem.Repository.DoctorRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository _doctorRepository;

    public List<DoctorDto> getAllDoctors() {
        try {
            List<Doctor> doctors = _doctorRepository.findAll();
            return doctors.stream().map(this::mapToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve Doctors.", e);
        }
    }

    public DoctorDto createDoctor(DoctorDto doctorDto) {
        try {
            // Convert DTO to entity
            Doctor doctor = mapToEntity(doctorDto);
            Doctor newDoctor = _doctorRepository.save(doctor);

            // Convert entity to DTO
            DoctorDto doctorResponse = mapToDTO(newDoctor);
            return doctorResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a Doctor.", e);
        }
    }


    public DoctorDto getDoctorById(long id) {
        try {
            Optional<Doctor> optionalDoctor = _doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = optionalDoctor.get();
                return mapToDTO(doctor);
            } else {
                throw new RuntimeException("Doctor not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve patient with ID: " + id, e);
        }
    }

    public void deleteDoctorById(long id) {
        try {
            Optional<Doctor> optionalDoctor = _doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = optionalDoctor.get();
                _doctorRepository.delete(doctor);
                System.out.println("Doctor deleted successfully.");
            } else {
                throw new RuntimeException("Doctor not found with ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to delete patient with ID: " + id, e);
        }
    }

    public DoctorDto updateDoctor(DoctorDto doctorDto, long id) {
        try {
            Optional<Doctor> optionalDoctor = _doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = optionalDoctor.get();
                doctor.setName(doctorDto.getName());
                doctor.setSpeciality(doctorDto.getSpeciality());

                Doctor updatedDoctor = _doctorRepository.save(doctor);
                return mapToDTO(updatedDoctor);
            } else {
                throw new RuntimeException("Doctor not found with ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to update Doctor with ID: " + id, e);
        }
    }

    //convert DTO to entity
    private Doctor mapToEntity(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setSpeciality(doctorDto.getSpeciality());
        return doctor;
    }
    //convert Entity into DTO
    private DoctorDto mapToDTO(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSpeciality(doctor.getSpeciality());
        return doctorDto;
    }


}
