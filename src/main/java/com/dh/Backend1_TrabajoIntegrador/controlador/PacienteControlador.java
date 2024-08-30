package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControlador {
    private IPacienteServicio iPacienteServicio;

    public PacienteControlador(IPacienteServicio iPacienteServicio) {
        this.iPacienteServicio = iPacienteServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(iPacienteServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> consultarTodos() {
        return ResponseEntity.ok(iPacienteServicio.consultarTodos());
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(iPacienteServicio.guardar(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> modificar(@PathVariable Long id, @RequestBody Paciente paciente) {
        return ResponseEntity.ok(iPacienteServicio.modificar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        iPacienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
