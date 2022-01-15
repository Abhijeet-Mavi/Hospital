package com.hospital.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.hospital.dto.DoctorDto;
import com.hospital.hospital.dto.PatientDto;
import com.hospital.hospital.entity.Doctor;
import com.hospital.hospital.entity.Patient;
import com.hospital.hospital.service.HospitalService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {HospitalApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class HospitalControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HospitalService hospitalService;

    @Test
    public void testCreateDoctor() {
        DoctorDto doctor = new DoctorDto();
        doctor.setAge(23);
        doctor.setDoctorName("doctorName");
        doctor.setGender("Male");
        doctor.setSpecialist("specialist");

        try {
            mvc.perform(post("/hospital/create-doctor")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctor)))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetDoctors() {
        Doctor expectedDoctor = getDoctorEntity();

        try {
            mvc.perform(get("/hospital/doctors")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].doctorName", is(expectedDoctor.getName())));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testCreatePatient() {
        PatientDto patientDto = getPatientDto();
        try {
            mvc.perform(post("/hospital/create-patient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientDto)))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testCreatePatientWithInvalidDoctor() {
        PatientDto patientDto = getPatientDto();
        patientDto.setVisitedDoctorId(-243L);
        try {
            mvc.perform(post("/hospital/create-patient")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientDto)));

            assertTrue(false);

        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetPatientWithId() {
      Patient patient =  hospitalService.createPatientRecord(getPatientDto());

        try {
            mvc.perform(get("/hospital/patient/" + patient.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("name", is(patient.getName())));
        } catch (Exception e) {
            assertTrue(false);
        }
    }


    @Test
    public void testGetPatientWithInvalidId() {
      

        try {
            mvc.perform(get("/hospital/patient/" + 123812)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    ;
                    assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    private PatientDto getPatientDto() {
        Doctor doctor2 = getDoctorEntity();
        PatientDto patientDto = new PatientDto();
        patientDto.setAge(55);
        patientDto.setDateOfVisit(new Date());
        patientDto.setVisitedDoctorId(doctor2.getId());
        patientDto.setName("name");

        return patientDto;
    }

    private Doctor getDoctorEntity() {
        DoctorDto doctor = new DoctorDto();
        doctor.setAge(23);
        doctor.setDoctorName("doctorNameMock");
        doctor.setGender("Male");
        doctor.setSpecialist("specialist");
        return hospitalService.createDoctorRecord(doctor);
    }

}
