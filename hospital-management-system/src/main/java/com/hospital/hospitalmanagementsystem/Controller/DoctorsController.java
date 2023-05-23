package com.hospital.hospitalmanagementsystem.Controller;

import com.hospital.hospitalmanagementsystem.Entity.Appointment;
import com.hospital.hospitalmanagementsystem.Service.AppointmentService;
import com.hospital.hospitalmanagementsystem.Service.DoctorService;
import com.hospital.hospitalmanagementsystem.dto.DoctorDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private DoctorService _doctorService;
    @Autowired
    private AppointmentService _appointmentService;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors(){
        return ResponseEntity.ok().body(_doctorService.getAllDoctors());
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = _appointmentService.getAppointmentsForDoctor(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto){
        if(doctorDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity(_doctorService.createDoctor(doctorDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok((DoctorDto) _doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor
            (@Valid @RequestBody DoctorDto doctorDto
                    , @PathVariable(name = "id") long id) {
        if(doctorDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(_doctorService.updateDoctor(doctorDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") long id) {
        _doctorService.deleteDoctorById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
