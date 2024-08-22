package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.dao.implementacion.PacienteImpl;
import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;

import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {
    private IDao<Paciente> pacienteIDao;

    public PacienteServicioImpl() {
        pacienteIDao = new PacienteImpl();
    }

    @Override
    public Paciente consultarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Paciente> consultarTodos() {
        return null;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }
}
