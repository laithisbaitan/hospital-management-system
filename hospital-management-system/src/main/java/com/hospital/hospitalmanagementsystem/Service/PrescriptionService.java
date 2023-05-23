package com.hospital.hospitalmanagementsystem.Service;

import com.hospital.hospitalmanagementsystem.Entity.Appointment;
import com.hospital.hospitalmanagementsystem.Entity.Prescription;
import com.hospital.hospitalmanagementsystem.Exception.CustomException;
import com.hospital.hospitalmanagementsystem.Repository.AppointmentRepository;
import com.hospital.hospitalmanagementsystem.Repository.PrescriptionRepository;
import com.hospital.hospitalmanagementsystem.dto.PrescriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository _prescriptionRepository;
    @Autowired
    private AppointmentRepository _appointmentRepository;

    @Autowired
    private AppointmentService _appointmentService;

    public List<PrescriptionDto> getAllPrescriptions() {
        try{
            List<Prescription> prescriptions = _prescriptionRepository.findAll();
            return prescriptions.stream().map(prescription -> mapToDTO(prescription)).collect(Collectors.toList());
        } catch(Exception e){
            throw new RuntimeException("Failed to retrieve Prescriptions.", e);
        }
    }

    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto){
        try{
            //convert DTO to entity
            Prescription prescription = mapToEntity(prescriptionDto);
            Prescription newPrescription = _prescriptionRepository.save(prescription);

            //convert entity to DTO
            PrescriptionDto prescriptionResponse = mapToDTO(newPrescription);
            return prescriptionResponse;
        }catch(Exception e){
            throw new RuntimeException("Failed to create a Prescription.", e);
        }
    }

    public Object getPrescriptionById(long id) {
        try{
            Optional<Prescription> prescription = _prescriptionRepository.findById(id);
            if(prescription.isPresent()){
                return mapToDTO(prescription.get());
            }else {
                throw new RuntimeException("Prescription not found with ID: " + id);
            }
        }catch(Exception e) {
            throw new RuntimeException("Failed to retrieve Prescription with ID: " + id, e);
        }
    }

    public void deletePrescriptionById(long id) {
        try{
            Optional<Prescription> prescription = _prescriptionRepository.findById(id);
            if(prescription.isPresent()){
                _prescriptionRepository.delete(prescription.get());
                throw new CustomException("Prescription deleted successfully.");
            }else {
                throw new RuntimeException("Prescription not found with ID: " + id);
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to delete Prescription with ID: " + id, e);
        }
    }

    public PrescriptionDto updatePrescription(PrescriptionDto prescriptionDto, long id) {
        try{
            Optional<Prescription> optionalPrescription = _prescriptionRepository.findById(id);
            if (optionalPrescription.isPresent()){
                Prescription prescription = optionalPrescription.get();
                prescription.setDosage(prescriptionDto.getDosage());
                prescription.setMedication(prescriptionDto.getMedication());
//              prescription.setAppointmentid(prescriptionDto.getAppointmentid());
                prescription.setAppointment(_appointmentRepository.findById(prescriptionDto.getAppointmentid()).get());

                Prescription updatedPrescription = _prescriptionRepository.save(prescription);
                return mapToDTO(updatedPrescription);

            }else {
                throw new RuntimeException("Prescription not found with ID: " + id);
            }
        }catch(Exception e){
            throw new RuntimeException("Failed to update Prescription with ID: " + id, e);
        }
    }

    //convert DTO to entity
    private Prescription mapToEntity(PrescriptionDto prescriptionDto){
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionDto.getId());
        prescription.setDosage(prescriptionDto.getDosage());
        prescription.setMedication(prescriptionDto.getMedication());

        Appointment appointment = _appointmentRepository.findById(prescriptionDto.getAppointmentid()).get();
        prescription.setAppointment(appointment);
        return prescription;
    }
    //convert Entity into DTO
    private PrescriptionDto mapToDTO(Prescription prescription){
        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setId(prescription.getId());
        prescriptionDto.setDosage(prescription.getDosage());
        prescriptionDto.setMedication(prescription.getMedication());

        Appointment appointment = prescription.getAppointment();
        prescriptionDto.setAppointmentid(appointment.getId());
        return prescriptionDto;
    }
}