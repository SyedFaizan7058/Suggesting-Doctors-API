package com.nit.service;

import com.nit.entity.Patient;
import com.nit.exception.PatientNotFoundException;
import com.nit.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Boolean removePatient(Integer id) {

        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Patient getPatient(Integer id) {
        if (patientRepository.existsById(id)) {
            return patientRepository.findById(id)
                    .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        } else
            return null;

    }
}
