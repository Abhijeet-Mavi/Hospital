package com.hospital.hospital.repository;

import com.hospital.hospital.entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    
}
