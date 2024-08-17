package com.dh.Backend1_TrabajoIntegrador.servicio;

import com.dh.Backend1_TrabajoIntegrador.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    Odontologo Listar(Integer id);
    List<Odontologo> listarTodos();
    Odontologo guardar(Odontologo odontologo);
    void modificar(Odontologo odontologo);
    void eliminar(Integer id);
}
