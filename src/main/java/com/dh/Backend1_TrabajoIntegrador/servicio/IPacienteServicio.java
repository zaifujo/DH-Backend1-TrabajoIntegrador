package com.dh.Backend1_TrabajoIntegrador.servicio;

import com.dh.Backend1_TrabajoIntegrador.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {
    Paciente Listar(Integer id);
    List<Paciente> listarTodos();
    Paciente guardar(Paciente paciente);
    void modificar(Paciente paciente);
    void eliminar(Integer id);
}
