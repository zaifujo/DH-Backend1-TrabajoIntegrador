package com.dh.Backend1_TrabajoIntegrador.controlador;
import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoControlador {

    @Autowired
    private ITurnoServicio iTurnoServicio;

    @GetMapping("/{id}")
    public ResponseEntity<Turno> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(iTurnoServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> consultarTodos() {
        return ResponseEntity.ok(iTurnoServicio.consultarTodos());
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(iTurnoServicio.guardar(turno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> modificar(@PathVariable Long id, @RequestBody Turno turno) {
        return ResponseEntity.ok(iTurnoServicio.modificar(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        iTurnoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
