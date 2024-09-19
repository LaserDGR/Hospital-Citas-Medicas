package com.hospital.citasmedicas.repository;

import com.hospital.citasmedicas.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
