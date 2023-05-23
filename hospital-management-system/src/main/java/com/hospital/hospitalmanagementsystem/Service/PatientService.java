package com.hospital.hospitalmanagementsystem.Service;

import com.hospital.hospitalmanagementsystem.Entity.Patient;
import com.hospital.hospitalmanagementsystem.Exception.CustomException;
import com.hospital.hospitalmanagementsystem.Repository.PatientRepository;
import com.hospital.hospitalmanagementsystem.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository _patientRepository;

    public List<PatientDto> getAllPatients() {
        try {
            List<Patient> patients = _patientRepository.findAll();
            return patients.stream().map(patient -> mapToDTO(patient)).collect(Collectors.toList());
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to retrieve patients.", e);
        }
    }

    public PatientDto createPatient(PatientDto patientDto) {
        try {
            // Convert DTO to entity
            Patient patient = mapToEntity(patientDto);
            Patient newPatient = _patientRepository.save(patient);

            // Convert entity to DTO
            PatientDto patientResponse = mapToDTO(newPatient);
            return patientResponse;
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to create a patient.", e);
        }
    }

    public PatientDto getPatientById(long id) {
        try {
            Optional<Patient> patient = _patientRepository.findById(id);
            if (patient.isPresent()) {
                return mapToDTO(patient.get());
            } else {
                // Patient with the specified ID not found
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to retrieve patient with ID: " + id, e);
        }
    }

    public void deletePatientById(long id) {
        try {
            Optional<Patient> patient = _patientRepository.findById(id);
            if (patient.isPresent()) {
                _patientRepository.delete(patient.get());
                throw new CustomException("Patient deleted successfully.");
            } else {
                // Patient with the specified ID not found
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to delete patient with ID: " + id, e);
        }
    }

    public PatientDto updatePatient(PatientDto patientDto, long id) {
        try {
            Optional<Patient> optionalPatient = _patientRepository.findById(id);

            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                patient.setName(patientDto.getName());
                patient.setPhoneNum(patientDto.getPhoneNum());

                Patient updatedPatient = _patientRepository.save(patient);
                return mapToDTO(updatedPatient);
            } else {
                // Throw a custom exception
                throw new RuntimeException("Patient not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to update patient with ID: " + id, e);
        }
    }


    //convert DTO to entity
    private Patient mapToEntity(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setPhoneNum(patientDto.getPhoneNum());
        return patient;
    }
    //convert Entity into DTO
    private PatientDto mapToDTO(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setPhoneNum(patient.getPhoneNum());
        return patientDto;
    }


}
