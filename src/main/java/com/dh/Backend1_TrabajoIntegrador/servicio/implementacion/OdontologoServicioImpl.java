package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.dao.implementacion.OdontologoImpl;
import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    private IDao<Odontologo> odontologoDao;

    public OdontologoServicioImpl(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    @Override
    public Odontologo consultarPorId(Integer id) {
        return odontologoDao.consultarPorId(id);
    }

    @Override
    public List<Odontologo> consultarTodos() {
        return odontologoDao.consultarTodos();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoDao.guardar(odontologo);
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return odontologoDao.modificar(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        odontologoDao.eliminar(id);
    }
}
