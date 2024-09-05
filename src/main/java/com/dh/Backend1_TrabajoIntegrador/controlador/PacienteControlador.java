package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControlador {
    private IPacienteServicio pacienteServicio;
    private static final Logger LOGGER = Logger.getLogger(PacienteControlador.class);

    @Autowired
    public PacienteControlador(IPacienteServicio pacienteServicio) {
        this.pacienteServicio = pacienteServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {

        Paciente pacienteBuscado = pacienteServicio.consultarPorId(id);

        if (pacienteBuscado != null) {
            LOGGER.info("Paciente encontrado: " + pacienteBuscado.toString());
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            LOGGER.info("No se encontró paciente con ID:" + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> consultarTodos() {

        List<Paciente> pacientes = pacienteServicio.consultarTodos();

        if (pacientes.isEmpty()) {
            LOGGER.info("No se encontraron pacientes.");
            return ResponseEntity.noContent().build();
        } else {
            LOGGER.info("Consulta de pacientes exitosa.");
            return ResponseEntity.ok(pacientes);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {

        Paciente pacienteGuardado = pacienteServicio.guardar(paciente);

        if (pacienteGuardado != null) {
            LOGGER.info("Paciente registrado con éxito: " + pacienteGuardado.toString());
            return ResponseEntity.created(URI.create("/pacientes/" + pacienteGuardado.getId()))
                    .body(pacienteGuardado);
        } else {
            LOGGER.warn("No se pudo registrar el paciente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody Paciente paciente) {

        Paciente pacienteBuscado = pacienteServicio.consultarPorId(paciente.getId());

        if (pacienteBuscado != null) {
            Paciente pacienteModificado = pacienteServicio.modificar(paciente);
            LOGGER.info("Se actualizó correctamente el paciente: " + pacienteModificado.toString());
            return ResponseEntity.ok("Paciente actualizado: " + paciente.getId());
        } else {
            LOGGER.info("No se pudo actualizar el paciente, ID no encontrado: " + paciente.getId());
            return ResponseEntity.badRequest().body("Paciente no encontrado: " + paciente.getId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Paciente pacienteBuscado = pacienteServicio.consultarPorId(id);

        if (pacienteBuscado != null) {
            pacienteServicio.eliminar(id);
            LOGGER.info("Paciente eliminado con éxito, ID: " + id);
            return ResponseEntity.ok("Paciente eliminado con éxito: " + id);
        } else {
            LOGGER.warn("Paciente no encontrado, ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado con ID: " + id);
        }
    }
}
