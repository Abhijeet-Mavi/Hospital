package com.hospital.hospital.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientDto {
    private Long id;

    private String name;
    
    private Long visitedDoctorId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfVisit;

    private int age;

    private String doctorName;

    private String prescription;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVisitedDoctorId() {
        return visitedDoctorId;
    }

    public void setVisitedDoctorId(Long visitedDoctorId) {
        this.visitedDoctorId = visitedDoctorId;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    
}
