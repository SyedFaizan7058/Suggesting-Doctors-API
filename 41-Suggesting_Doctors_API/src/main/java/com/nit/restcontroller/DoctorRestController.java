package com.nit.restcontroller;

import com.nit.entity.Doctor;
import com.nit.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "/api/doctor")
public class DoctorRestController {

    private final DoctorService doctorService;

    //Constructor Injection
    @Autowired
    public DoctorRestController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //Method to add doctor
    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Doctor> addDoctor(@RequestBody @Valid Doctor doctor) {

        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    //Method to delete doctor
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public ResponseEntity<String> removeDoctor(@PathVariable("id") Integer id) {

        Boolean removedDoctor = doctorService.removeDoctor(id);
        if (!removedDoctor) {
            return new ResponseEntity<>("Doctor with given id " + id + " is not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Doctor with given id " + id + " is deleted successfully", HttpStatus.OK);
    }
}
