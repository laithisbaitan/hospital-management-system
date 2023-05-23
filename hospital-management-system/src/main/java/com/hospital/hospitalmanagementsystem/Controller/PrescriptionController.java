package com.hospital.hospitalmanagementsystem.Controller;

import com.hospital.hospitalmanagementsystem.Service.PrescriptionService;
import com.hospital.hospitalmanagementsystem.dto.PrescriptionDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService _prescriptionService;

    @GetMapping
    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions(){
        return ResponseEntity.ok().body(_prescriptionService.getAllPrescriptions());
    }
    @PostMapping
    public ResponseEntity<PrescriptionDto> createPrescription(@Valid @RequestBody PrescriptionDto prescriptionDto){
        if(prescriptionDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity(_prescriptionService.createPrescription(prescriptionDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getPrescriptionById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok((PrescriptionDto) _prescriptionService.getPrescriptionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDto> updatePrescription
            (@Valid @RequestBody PrescriptionDto prescriptionDto
                    , @PathVariable(name = "id") long id) {
        if(prescriptionDto.getId() != null){
            throw new RuntimeException();
        }
        return new ResponseEntity<>(_prescriptionService.updatePrescription(prescriptionDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable(name = "id") long id) {
        _prescriptionService.deletePrescriptionById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
