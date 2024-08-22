package com.dh.Backend1_TrabajoIntegrador.servicio;

import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    Paciente consultarPorId(Integer id);
    List<Paciente> consultarTodos();
    Paciente guardar(Paciente paciente);
    Paciente modificar(Paciente paciente);
    void eliminar(Integer id);
}
