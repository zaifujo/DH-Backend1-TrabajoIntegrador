package com.dh.Backend1_TrabajoIntegrador.servicio.implementacion;

import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.repositorio.ITurnoRepositorio;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicioImpl implements ITurnoServicio {

    private ITurnoRepositorio iTurnoRepositorio;

    public TurnoServicioImpl(ITurnoRepositorio iTurnoRepositorio) {
        this.iTurnoRepositorio = iTurnoRepositorio;
    }

    @Override
    public Turno consultarPorId(Long id) {
        Optional<Turno> turnoBuscado =  iTurnoRepositorio.findById(id);
        return turnoBuscado.orElse(null);
    }

    @Override
    public List<Turno> consultarTodos() {
        return iTurnoRepositorio.findAll();
    }

    @Override
    public Turno guardar(Turno turno) {
        return iTurnoRepositorio.save(turno);
    }

    @Override
    public Turno modificar(Turno turno) {
        return iTurnoRepositorio.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        iTurnoRepositorio.deleteById(id);
    }
}
