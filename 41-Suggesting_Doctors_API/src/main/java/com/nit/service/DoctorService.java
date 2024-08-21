package com.nit.service;

import com.nit.entity.Doctor;

import java.util.List;

public interface DoctorService {

    public Doctor addDoctor(Doctor doctor);
    public Boolean removeDoctor(Integer id);
    public List<Doctor> suggestDoctors(String city, String symptom);
}
