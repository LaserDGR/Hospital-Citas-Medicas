package com.hospital.citasmedicas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Consultorio consultorio;

    private LocalDateTime horario;
    private String nombrePaciente;

    public Cita() {}

    public Cita(Doctor doctor, Consultorio consultorio, LocalDateTime horario, String nombrePaciente) {
        this.doctor = doctor;
        this.consultorio = consultorio;
        this.horario = horario;
        this.nombrePaciente = nombrePaciente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", consultorio=" + consultorio +
                ", horario=" + horario +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return id != null && id.equals(cita.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
