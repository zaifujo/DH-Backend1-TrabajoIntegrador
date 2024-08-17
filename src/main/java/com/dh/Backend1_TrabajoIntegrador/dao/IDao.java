package com.dh.Backend1_TrabajoIntegrador.dao;

import java.util.List;

public interface IDao<T> {
    T listar(Integer id);
    List<T> listarTodos();
    T agregar(T t);
    T modificar(T t);
    void eliminar(Integer id);
}
