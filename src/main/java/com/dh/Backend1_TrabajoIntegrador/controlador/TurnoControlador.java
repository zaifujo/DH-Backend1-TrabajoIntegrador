package com.dh.Backend1_TrabajoIntegrador.controlador;
import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoControlador {
    private ITurnoServicio turnoServicio;
    private static final Logger LOGGER = Logger.getLogger(TurnoControlador.class);

    @Autowired
    public TurnoControlador(ITurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> consultarPorId(@PathVariable Long id) {

        Turno turnoBuscado = turnoServicio.consultarPorId(id);

        if (turnoBuscado != null) {
            LOGGER.info("Turno encontrado: " + turnoBuscado.toString());
            return ResponseEntity.ok(turnoBuscado);
        } else {
            LOGGER.info("No se encontró turno con ID:" + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> consultarTodos() {

        List<Turno> turnos = turnoServicio.consultarTodos();

        if (turnos.isEmpty()) {
            LOGGER.info("No se encontraron turnos.");
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            LOGGER.info("Consulta de turnos exitosa.");
            return ResponseEntity.ok(turnos); // 200 OK con la lista de turnos
        }
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {

        Turno turnoGuardado = turnoServicio.guardar(turno);

        if (turnoGuardado != null) {
            LOGGER.info("Turno registrado con éxito: " + turnoGuardado.toString());
            return ResponseEntity.ok(turnoGuardado);
        } else {
            LOGGER.warn("No se pudo registrar el turno.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Turno> modificar(@RequestBody Turno turno) {

        Turno turnoBuscado = turnoServicio.consultarPorId(turno.getId());

        if (turnoBuscado != null) {
            Turno turnoModificado = turnoServicio.modificar(turno);
            LOGGER.info("Se actualizó correctamente el turno: " + turnoModificado.toString());
            return ResponseEntity.ok(turnoModificado);
        } else {
            LOGGER.info("No se pudo actualizar turno, ID no encontrado: " + turno.getId());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Turno turnoBuscado = turnoServicio.consultarPorId(id);

        if (turnoBuscado != null) {
            turnoServicio.eliminar(id);
            LOGGER.info("Turno eliminado con éxito, ID: " + id);
            return ResponseEntity.ok("Turno eliminado con éxito: " + id);
        } else {
            LOGGER.warn("Turno no encontrado, ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Turno no encontrado con ID: " + id);
        }
    }
}
