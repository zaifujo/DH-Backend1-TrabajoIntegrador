package com.dh.Backend1_TrabajoIntegrador.dao.implementacion;

import com.dh.Backend1_TrabajoIntegrador.dao.IDao;
import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoImpl implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoImpl.class);

    @Override
    public Odontologo listar(Integer id) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }
}
