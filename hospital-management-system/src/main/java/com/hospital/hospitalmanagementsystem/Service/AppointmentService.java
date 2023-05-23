package com.hospital.hospitalmanagementsystem.Service;

import com.hospital.hospitalmanagementsystem.Entity.Appointment;
import com.hospital.hospitalmanagementsystem.Entity.Doctor;
import com.hospital.hospitalmanagementsystem.Entity.Patient;
import com.hospital.hospitalmanagementsystem.Exception.CustomException;
import com.hospital.hospitalmanagementsystem.Repository.AppointmentRepository;
import com.hospital.hospitalmanagementsystem.Repository.DoctorRepository;
import com.hospital.hospitalmanagementsystem.Repository.PatientRepository;
import com.hospital.hospitalmanagementsystem.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository _appointmentRepository;
    @Autowired
    private DoctorRepository _doctorRepository;
    @Autowired
    private PatientRepository _patientRepository;

    public List<AppointmentDto> getAllAppointments() {
//        List<Appointment> appointments = _appointmentRepository.findAll();
//        return appointments.stream().map(appointment -> mapToDTO(appointment)).collect(Collectors.toList());
        try {
            List<Appointment> appointments = _appointmentRepository.findAll();
            return appointments.stream()
                    .map(appointment -> mapToDTO(appointment))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve Appointments.", e);
        }
    }

    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        try {
            Doctor doctor = _doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Doctor not found for the given ID"));
            List<Appointment> appointments = _appointmentRepository.findByDoctor(doctor);
            if (appointments.isEmpty()) {
                throw new IllegalArgumentException("No appointments found for the given doctor ID");
            }
            return appointments;
        } catch (IllegalArgumentException e) {
            throw new CustomException("Failed to retrieve appointments for the doctor. Please check the input and try again.");
        } catch (Exception e) {
            throw new CustomException("An error occurred while retrieving appointments for the doctor. Please try again later.");
        }
    }

    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        try {
            // Convert DTO to entity
            Appointment appointment = mapToEntity(appointmentDto);

            // Save the new appointment
            Appointment newAppointment = _appointmentRepository.save(appointment);

            // Convert entity to DTO
            AppointmentDto appointmentResponse = mapToDTO(newAppointment);
            return appointmentResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a Appointment.", e);
        }
    }

    public Object getAppointmentById(long id) {
        try {
            Optional<Appointment> appointment = _appointmentRepository.findById(id);
            if (appointment.isPresent()) {
                return mapToDTO(appointment.get());
            } else {
                throw new RuntimeException("Appointment not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to retrieve Appointment with ID: " + id, e);
        }
    }

    public void deleteAppointmentById(long id) {
        try {
            Optional<Appointment> appointment = _appointmentRepository.findById(id);
            if (appointment.isPresent()) {
                _appointmentRepository.delete(appointment.get());
                throw new CustomException("Appointment deleted successfully.");
            } else {
                throw new RuntimeException("Appointment not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to delete Appointment with ID: " + id, e);
        }
    }

    public AppointmentDto updateAppointment(AppointmentDto appointmentDto, long id) {
        try {
            Optional<Appointment> optionalAppointment = _appointmentRepository.findById(id);
            if (optionalAppointment.isPresent()) {
                Appointment appointment = optionalAppointment.get();
                appointment.setDate(appointmentDto.getDate());
                appointment.setDoctor(_doctorRepository.findById(appointmentDto.getDoctorid()).orElse(null));
                appointment.setPatient(_patientRepository.findById(appointmentDto.getPatientid()).orElse(null));

                Appointment updatedAppointment = _appointmentRepository.save(appointment);
                return mapToDTO(updatedAppointment);
            } else {
                throw new RuntimeException("Appointment not found with ID: " + id);
            }
        } catch (Exception e) {
            // Throw a custom exception
            throw new RuntimeException("Failed to update Appointment with ID: " + id, e);
        }
    }

    //convert DTO to entity
    private Appointment mapToEntity(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setDate(appointmentDto.getDate());

        Doctor doctor = _doctorRepository.findById(appointmentDto.getDoctorid()).get();
        appointment.setDoctor(doctor);
        Patient patient = _patientRepository.findById(appointmentDto.getPatientid()).get();
        appointment.setPatient(patient);
        return appointment;
    }
    //convert Entity into DTO
    private AppointmentDto mapToDTO(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDate());

        Doctor doctor = appointment.getDoctor();
        appointmentDto.setDoctorid(doctor.getId());
        Patient patient = appointment.getPatient();
        appointmentDto.setPatientid(patient.getId());
        return appointmentDto;
    }


}
