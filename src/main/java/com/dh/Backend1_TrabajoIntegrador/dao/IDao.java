package com.dh.Backend1_TrabajoIntegrador.dao;

import java.util.List;

public interface IDao<T> {
    T consultarPorId(Integer id);
    List<T> consultarTodos();
    T guardar(T t);
    T modificar(T t);
    void eliminar(Integer id);
}
