package com.hospital.hospital.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Patient {

    @Id
    @SequenceGenerator(name = "patientSeqGen", sequenceName = "patientSeq", initialValue = 100)
    @GeneratedValue(generator = "patientSeqGen")
    private Long id;

    private String name;

    private boolean visitedDoctor;

    private Date dateOfVisit;

    private int age;


    private String prescription;

    

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    @ManyToOne
    private Doctor doctor;

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

    public boolean isVisitedDoctor() {
        return visitedDoctor;
    }

    public void setVisitedDoctor(boolean visitedDoctor) {
        this.visitedDoctor = visitedDoctor;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
