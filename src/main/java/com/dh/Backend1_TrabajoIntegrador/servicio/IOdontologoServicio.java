package com.dh.Backend1_TrabajoIntegrador.servicio;

import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    Odontologo consultarPorId(Integer id);
    List<Odontologo> consultarTodos();
    Odontologo guardar(Odontologo odontologo);
    Odontologo modificar(Odontologo odontologo);
    void eliminar(Integer id);
}
