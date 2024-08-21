package com.nit.service;

import com.nit.entity.Doctor;
import com.nit.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Boolean removeDoctor(Integer id) {

        if(doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Doctor> suggestDoctors(String city, String symptom) {

        String specialityBySymptom = getSpecialityBySymptom(symptom);
        if(specialityBySymptom != null) {
            return doctorRepository.findByCityAndSpeciality(city, specialityBySymptom);
        }
        return Collections.emptyList();
    }

    public String getSpecialityBySymptom(String symptom) {

        return switch (symptom) {
            case "Arthritis", "Back Pain", "Tissue injuries" -> "Orthopedic";
            case "Dysmenorrhea" -> "Gynecology";
            case "Skin infection", "Skin burn" -> "Dermatology";
            case "Ear pain" -> "ENT specialist";
            default -> null;
        };
    }
}
