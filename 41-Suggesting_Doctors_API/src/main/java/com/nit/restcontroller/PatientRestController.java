package com.nit.restcontroller;

import com.nit.entity.Doctor;
import com.nit.entity.Patient;
import com.nit.service.DoctorService;
import com.nit.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patient")
public class PatientRestController {

    private final PatientService patientService;

    //Field Injection
    @Autowired
    private DoctorService doctorService;

    //Constructor Injection
    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Endpoint to add patient
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {

        Patient savedPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    //Method to delete patient
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public ResponseEntity<String> removePatient(@PathVariable Integer id) {

        Boolean removedPatient = patientService.removePatient(id);
        if (!removedPatient) {
            return new ResponseEntity<>("Patient with given " + id + " is not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Patient with given id " + id + " is deleted successfully", HttpStatus.OK);
    }

    //Method to suggest doctors
    @GetMapping(value = "/{id}/suggest-doctors",produces = "application/json")
    public ResponseEntity<?> suggestDoctors(@PathVariable("id") Integer id) {

        Patient patient = patientService.getPatient(id);
        if (patient == null) {
            return new ResponseEntity<>("Patient with given id " + id + " is not found", HttpStatus.NOT_FOUND);
        }
        List<Doctor> suggestedDoctors = doctorService.suggestDoctors(patient.getCity(), patient.getSymptom());
        if (suggestedDoctors.isEmpty()) {
            return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(suggestedDoctors, HttpStatus.OK);
    }
}
