package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.repositorio.IOdontologoRepositorio;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicioImpl implements IOdontologoServicio {
    private IOdontologoRepositorio iOdontologoRepositorio;

    public OdontologoServicioImpl(IOdontologoRepositorio iOdontologoRepositorio) {
        this.iOdontologoRepositorio = iOdontologoRepositorio;
    }

    @Override
    public Odontologo consultarPorId(Long id) {
        Optional<Odontologo> odontologoBuscado =  iOdontologoRepositorio.findById(id);
        return odontologoBuscado.orElse(null);
    }

    @Override
    public List<Odontologo> consultarTodos() {
        return iOdontologoRepositorio.findAll();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iOdontologoRepositorio.save(odontologo);
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return iOdontologoRepositorio.save(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        iOdontologoRepositorio.deleteById(id);
    }
}
