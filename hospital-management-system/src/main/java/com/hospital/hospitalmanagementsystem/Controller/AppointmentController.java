package com.hospital.hospitalmanagementsystem.Controller;

import com.hospital.hospitalmanagementsystem.Service.AppointmentService;
import com.hospital.hospitalmanagementsystem.dto.AppointmentDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService _appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments(){
        return ResponseEntity.ok().body(_appointmentService.getAllAppointments());
    }
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@Valid @RequestBody AppointmentDto appointmentDto){
        if(appointmentDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity(_appointmentService.createAppointment(appointmentDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok((AppointmentDto) _appointmentService.getAppointmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment
            (@Valid @RequestBody AppointmentDto appointmentDto
                    , @PathVariable(name = "id") long id) {
        if(appointmentDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(_appointmentService.updateAppointment(appointmentDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable(name = "id") long id) {
        _appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
