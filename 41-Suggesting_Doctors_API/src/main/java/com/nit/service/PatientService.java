package com.nit.service;

import com.nit.entity.Patient;

public interface PatientService {

    public Patient addPatient(Patient patient);
    public Boolean removePatient(Integer patient);
    public Patient getPatient(Integer id);
}
