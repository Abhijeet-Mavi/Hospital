package com.hospital.hospital.controller;

import java.util.List;

import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.dto.PatientDto;
import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Patient;
import com.hospital.hospital.service.HospitalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @PostMapping("/create-doctor")
    public Long createDoctor(@RequestBody DoctorDto doctor) {
        return hospitalService.createDoctorRecord(doctor).getId();
    }


    @GetMapping("/doctors")
    public List<DoctorDto> getAllDoctor() {
        return hospitalService.getAllDoctors();
    }
    

    @GetMapping("/patient/{id}")
    public PatientDto getPatientById(@PathVariable(value = "id") long id){
        return hospitalService.getPatientById(id);
    }
    

    @PostMapping("/create-patient")
    public Long createPatient(@RequestBody PatientDto patient) {
       return hospitalService.createPatientRecord(patient).getId();
        
    }
    
}
