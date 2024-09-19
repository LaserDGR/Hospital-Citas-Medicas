package com.hospital.citasmedicas.service;

import com.hospital.citasmedicas.model.Cita;
import com.hospital.citasmedicas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita crearCita(Cita cita) {

        if (validarCita(cita)) {
            return citaRepository.save(cita);
        } else {
            throw new IllegalArgumentException("Cita no válida");
        }
    }

    public boolean validarCita(Cita cita) {
        return validarConsultorio(cita) &&
                validarDoctor(cita) &&
                validarPaciente(cita) &&
                validarCitasMaximasPorDoctor(cita);
    }

    private boolean validarConsultorio(Cita cita) {
        LocalDateTime horaCita = cita.getHorario();
        List<Cita> citasConsultorio = citaRepository.findByConsultorioNumeroConsultorioAndHorarioBetween(
                cita.getConsultorio().getNumeroConsultorio(),
                horaCita.withMinute(0),
                horaCita.withMinute(59)
        );
        return citasConsultorio.isEmpty();
    }

    private boolean validarDoctor(Cita cita) {
        LocalDateTime horaCita = cita.getHorario();
        List<Cita> citasDoctor = citaRepository.findByDoctorNombreAndHorarioBetween(
                cita.getDoctor().getNombre(),
                horaCita.withMinute(0),
                horaCita.withMinute(59)
        );
        return citasDoctor.isEmpty();
    }

    private boolean validarPaciente(Cita cita) {
        LocalDateTime horaCita = cita.getHorario();
        LocalDateTime inicioRango = horaCita.minusHours(2);
        LocalDateTime finRango = horaCita.plusHours(2);

        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorarioBetween(
                cita.getNombrePaciente(),
                inicioRango,
                finRango
        );
        return citasPaciente.isEmpty();
    }

    private boolean validarCitasMaximasPorDoctor(Cita cita) {
        LocalDateTime horaCita = cita.getHorario();
        LocalDateTime inicioDia = horaCita.with(LocalTime.MIN);
        LocalDateTime finDia = horaCita.with(LocalTime.MAX);

        long totalCitasDelDia = citaRepository.countByDoctorNombreAndHorarioBetween(
                cita.getDoctor().getNombre(),
                inicioDia,
                finDia
        );
        return totalCitasDelDia < 8;
    }
}
