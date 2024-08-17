package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.dao.implementacion.OdontologoImpl;
import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;

import java.util.List;

public class OdontologoServicioImpl implements IOdontologoServicio {
    private IDao<Odontologo> odontologoIDao;
    public OdontologoServicioImpl() {
        this.odontologoIDao = new OdontologoImpl();
    }

    @Override
    public Odontologo Listar(Integer id) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public void modificar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }
}
