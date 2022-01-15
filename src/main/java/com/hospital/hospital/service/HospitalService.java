package com.hospital.hospital.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.dto.PatientDto;
import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Patient;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;



    public Doctor createDoctorRecord(DoctorDto doctorDto) {
        Doctor doctorEntity = new Doctor();
        doctorEntity.setAge(doctorDto.getAge());
        doctorEntity.setGender(doctorDto.getGender());
        doctorEntity.setName(doctorDto.getDoctorName());
        doctorEntity.setSpecialization(doctorDto.getSpecialist());

        return doctorRepository.save(doctorEntity);
    }


    public List<DoctorDto> getAllDoctors(){
       return doctorRepository.findAll().stream().map((doctor) -> {
           DoctorDto doc = new DoctorDto();
           doc.setId(doctor.getId());
           doc.setDoctorName(doctor.getName());
           doc.setSpecialist(doctor.getSpecialization());
           doc.setPatientCount(doctor.getPatients().size());
           return doc;
       }).collect(Collectors.toList());
    }


    public PatientDto getPatientById(long id){
        PatientDto patientDto = new PatientDto();
       Patient patient = patientRepository.getById(id);
       patientDto.setName(patient.getName());
        patientDto.setId(patient.getId());
        patientDto.setAge(patient.getAge());
        patientDto.setDoctorName(patient.getDoctor().getName());
        patientDto.setPrescription(patient.getPrescription());
        patientDto.setDateOfVisit(patient.getDateOfVisit());
        return patientDto;
    }

    public Patient createPatientRecord(PatientDto patientDto) {
        Patient patientEntity = new Patient();
        patientEntity.setName(patientDto.getName());
        patientEntity.setDateOfVisit(patientDto.getDateOfVisit());
        patientEntity.setAge(patientDto.getAge());
        patientEntity.setPrescription(patientDto.getPrescription());
        patientEntity.setVisitedDoctor(true);
        Doctor specialist = doctorRepository.getById(patientDto.getVisitedDoctorId());
        patientEntity.setDoctor(specialist);

        return patientRepository.saveAndFlush(patientEntity);
    }

    
}
