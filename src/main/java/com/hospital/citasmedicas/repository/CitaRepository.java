package com.hospital.citasmedicas.repository;

import com.hospital.citasmedicas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByConsultorioNumeroConsultorioAndHorarioBetween(int numeroConsultorio, LocalDateTime start, LocalDateTime end);

    List<Cita> findByDoctorNombreAndHorarioBetween(String nombreDoctor, LocalDateTime start, LocalDateTime end);

    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);

    long countByDoctorNombreAndHorarioBetween(String nombreDoctor, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
