package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoControlador {
    private ITurnoServicio turnoServicio;

    @Autowired
    public TurnoControlador(ITurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> consultarPorId(@PathVariable Long id) {
        return ResponseEntity
                .ok(turnoServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> consultarTodos() {

        List<Turno> turnos = turnoServicio.consultarTodos();

        if (turnos.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build(); // 204 No Content
        } else {
            return ResponseEntity
                    .ok(turnos); // 200 OK
        }
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(turnoServicio.guardar(turno));
    }

    @PutMapping
    public ResponseEntity<Turno> modificar(@RequestBody Turno turno) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turnoServicio.modificar(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        turnoServicio.eliminar(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
