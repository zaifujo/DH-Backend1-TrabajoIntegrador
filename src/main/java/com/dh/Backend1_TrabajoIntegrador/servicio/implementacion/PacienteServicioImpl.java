package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.repositorio.IPacienteRepositorio;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServicioImpl implements IPacienteServicio {
    private IPacienteRepositorio iPacienteRepositorio;

    public PacienteServicioImpl(IPacienteRepositorio iPacienteRepositorio) {
        this.iPacienteRepositorio = iPacienteRepositorio;
    }

    @Override
    public Paciente consultarPorId(Long id) {
        Optional<Paciente> pacienteBuscado =  iPacienteRepositorio.findById(id);
        return pacienteBuscado.orElse(null);
    }

    @Override
    public List<Paciente> consultarTodos() {
        return iPacienteRepositorio.findAll();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return iPacienteRepositorio.save(paciente);
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return iPacienteRepositorio.save(paciente);
    }

    @Override
    public void eliminar(Long id) {
        iPacienteRepositorio.deleteById(id);
    }
}
