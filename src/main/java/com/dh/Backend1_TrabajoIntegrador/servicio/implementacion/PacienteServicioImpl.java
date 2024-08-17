package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.dao.implementacion.OdontologoImpl;
import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;

import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {
    private IDao<Paciente> pacienteIDao;
    public PacienteServicioImpl() {
        this.pacienteIDao = new PacienteImpl();
    }

    @Override
    public Paciente Listar(Integer id) {
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return null;
    }

    @Override
    public void modificar(Paciente paciente) {

    }

    @Override
    public void eliminar(Integer id) {

    }
}
