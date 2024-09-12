package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.excepcion.BadRequestException;
import com.dh.Backend1_TrabajoIntegrador.excepcion.ResourceNotFoundException;
import com.dh.Backend1_TrabajoIntegrador.repositorio.ITurnoRepositorio;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicioImpl implements ITurnoServicio {
    private ITurnoRepositorio iTurnoRepositorio;
    private IPacienteServicio iPacienteServicio;
    private IOdontologoServicio iOdontologoServicio;
    private static final Logger LOGGER = Logger.getLogger(TurnoServicioImpl.class);

    @Autowired
    public TurnoServicioImpl(ITurnoRepositorio iTurnoRepositorio, IPacienteServicio iPacienteServicio, IOdontologoServicio iOdontologoServicio) {
        this.iTurnoRepositorio = iTurnoRepositorio;
        this.iPacienteServicio = iPacienteServicio;
        this.iOdontologoServicio = iOdontologoServicio;
    }

    @Override
    public Turno consultarPorId(Long id) throws ResourceNotFoundException {
        LOGGER.info("--> CONSULTAR TURNO");

        Optional<Turno> turnoBuscado =  iTurnoRepositorio.findById(id);
        if (turnoBuscado.isEmpty()) {
            LOGGER.error("No existe turno, ID: " + id);
            throw new ResourceNotFoundException("No existe turno, ID: " + id);
        }

        LOGGER.info("Turno consultado con éxito: " + turnoBuscado.get().toString());
        return turnoBuscado.get();
    }

    @Override
    public List<Turno> consultarTodos() {
        LOGGER.info("--> CONSULTAR TODOS LOS TURNOS");

        List<Turno> turnos = iTurnoRepositorio.findAll();
        if (turnos.isEmpty()) {
            LOGGER.warn("No se encontraron turnos");
        } else {
            LOGGER.info("Consulta de turnos exitosa. Cantidad de registros: " + turnos.size());
        }

        return turnos;
    }

    @Override
    public Turno guardar(Turno turno) throws ResourceNotFoundException, BadRequestException {
        LOGGER.info("--> GUARDAR TURNO");

        LOGGER.info("- Verificando que turno no tenga un ID asignado ...");
        if (turno.getId() != null) {
            LOGGER.error("No se pudo guardar turno: " + turno.toString());
            throw new BadRequestException("El turno tiene un ID asignado: " + turno.toString());
        }

        LOGGER.info("- Verificando que odontólogo existe ...");
        iOdontologoServicio.consultarPorId(turno.getOdontologo().getId());

        LOGGER.info("- Verificando que paciente existe ...");
        iPacienteServicio.consultarPorId(turno.getPaciente().getId());

        LOGGER.info("- Verificando que fecha sea a partir de hoy ...");
        if (turno.getFecha().isBefore(LocalDate.now())) {
            LOGGER.error("La fecha debe ser a partir de hoy");
            throw new BadRequestException("La fecha debe ser a partir de hoy");
        }

        Turno turnoGuardado = iTurnoRepositorio.save(turno);
        LOGGER.info("Turno guardado con éxito: " + turnoGuardado.toString());
        return turnoGuardado;
    }

    @Override
    public Turno modificar(Turno turno) throws ResourceNotFoundException, BadRequestException {
        LOGGER.info("--> MODIFICAR TURNO");

        LOGGER.info("- Verificando que turno existe ...");
        consultarPorId(turno.getId());

        LOGGER.info("- Verificando que odontólogo existe ...");
        iOdontologoServicio.consultarPorId(turno.getOdontologo().getId());

        LOGGER.info("- Verificando que paciente existe ...");
        iPacienteServicio.consultarPorId(turno.getPaciente().getId());

        LOGGER.info("- Verificando que fecha sea a partir de hoy ...");
        if (turno.getFecha().isBefore(LocalDate.now())) {
            LOGGER.error("La fecha debe ser a partir de hoy");
            throw new BadRequestException("La fecha debe ser a partir de hoy");
        }

        Turno turnoModificado = iTurnoRepositorio.save(turno);
        LOGGER.info("Turno modificado con éxito: " + turnoModificado.toString());
        return turnoModificado;
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        LOGGER.info("--> ELIMINAR TURNO");

        LOGGER.info("- Verificando que turno existe ...");
        consultarPorId(id);

        iTurnoRepositorio.deleteById(id);
        LOGGER.info("Turno eliminado con éxito, ID: " + id);
    }
}
