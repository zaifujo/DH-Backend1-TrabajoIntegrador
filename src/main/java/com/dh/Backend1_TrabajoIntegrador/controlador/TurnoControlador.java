package com.dh.Backend1_TrabajoIntegrador.controlador;
import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.entidad.Turno;
import com.dh.Backend1_TrabajoIntegrador.servicio.ITurnoServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoControlador {

    @Autowired
    private ITurnoServicio iTurnoServicio;
    private static final Logger LOGGER = Logger.getLogger(TurnoControlador.class);

    @GetMapping("/{id}")
    public ResponseEntity<Turno> consultarPorId(@PathVariable Long id) {

        Turno turnoBuscado = iTurnoServicio.consultarPorId(id);

        if (turnoBuscado != null) {
            LOGGER.info("Buscando turno por ID con éxito");
            return ResponseEntity.ok(turnoBuscado);
        } else {
            LOGGER.info("No se encontró turno por ID");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> consultarTodos() {

        List<Turno> turnos = iTurnoServicio.consultarTodos();

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
        Turno turnoGuardado = iTurnoServicio.guardar(turno);

        if (turnoGuardado != null) {
            LOGGER.info("Turno registrado con éxito, ID: " + turnoGuardado.getId());
            return ResponseEntity.created(URI.create("/odontologos/" + turnoGuardado.getId()))
                    .body(turnoGuardado);
        } else {
            LOGGER.warn("No se pudo registrar el odontólogo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable Long id, @RequestBody Turno turno) {
        Turno turnoBuscado = iTurnoServicio.consultarPorId(turno.getId());
        if (turnoBuscado != null) {
            iTurnoServicio.modificar(turno);
            LOGGER.info("Se actualizó correctamente el turno");
            return ResponseEntity.ok("Turno actualizado: " + turnoBuscado.getFecha());
        } else {
            LOGGER.info("No se pudo actualizar turno, ID no encontrado");
            return ResponseEntity.badRequest().body("Turno no encontrado: " + turnoBuscado.getFecha());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Turno turnoBuscado = iTurnoServicio.consultarPorId(id);

        if (turnoBuscado != null) {
            iTurnoServicio.eliminar(id);
            LOGGER.info("Turno eliminado con éxito, ID: " + id);
            return ResponseEntity.ok("Turno eliminado con éxito: " + id);
        } else {
            LOGGER.warn("Turno no encontrado, ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Turno no encontrado con ID: " + id);
        }
    }
}
