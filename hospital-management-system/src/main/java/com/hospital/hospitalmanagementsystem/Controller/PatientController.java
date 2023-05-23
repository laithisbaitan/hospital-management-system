package com.hospital.hospitalmanagementsystem.Controller;

import com.hospital.hospitalmanagementsystem.Service.PatientService;
import com.hospital.hospitalmanagementsystem.dto.PatientDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService _patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok().body(_patientService.getAllPatients());
    }
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto){
        if(patientDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity(_patientService.createPatient(patientDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok((PatientDto) _patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient
            (@Valid @RequestBody PatientDto patientDto
                    , @PathVariable(name = "id") long id) {
        if(patientDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(_patientService.updatePatient(patientDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable(name = "id") long id) {
        _patientService.deletePatientById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
