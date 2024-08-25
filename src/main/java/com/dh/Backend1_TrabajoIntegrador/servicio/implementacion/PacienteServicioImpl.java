package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.dao.implementacion.PacienteImpl;
import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServicioImpl implements IPacienteServicio {
    private IDao<Paciente> pacienteIDao;

    public PacienteServicioImpl(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente consultarPorId(Integer id) {
        return pacienteIDao.consultarPorId(id);
    }

    @Override
    public List<Paciente> consultarTodos() {
        return pacienteIDao.consultarTodos();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return pacienteIDao.modificar(paciente);
    }

    @Override
    public void eliminar(Integer id) {
        pacienteIDao.eliminar(id);
    }
}
