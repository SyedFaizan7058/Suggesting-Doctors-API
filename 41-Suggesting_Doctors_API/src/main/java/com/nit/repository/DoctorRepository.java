package com.nit.repository;

import com.nit.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public List<Doctor> findByCityAndSpeciality(String city, String speciality);
}
