package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.entidad.Paciente;
import com.dh.Backend1_TrabajoIntegrador.servicio.IPacienteServicio;
import org.apache.log4j.Logger;
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
    private IPacienteServicio iPacienteServicio;
    private static final Logger LOGGER = Logger.getLogger(PacienteControlador.class);

    public PacienteControlador(IPacienteServicio iPacienteServicio) {
        this.iPacienteServicio = iPacienteServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable("id") Long id) {

        Paciente pacienteBuscado = iPacienteServicio.consultarPorId(id);

        if (pacienteBuscado != null) {
            LOGGER.info("Buscando paciente por ID con éxito");
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            LOGGER.info("No se encontró paciente por ID");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> consultarTodos() {

        List<Paciente> pacientes = iPacienteServicio.consultarTodos();

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

        Paciente pacienteGuardado = iPacienteServicio.guardar(paciente);
        if (pacienteGuardado != null) {
            LOGGER.info("Paciente registrado con éxito, ID: " + pacienteGuardado.getId());
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

        Paciente pacienteBuscado = iPacienteServicio.consultarPorId(paciente.getId());

        if (pacienteBuscado != null) {
            iPacienteServicio.modificar(pacienteBuscado);
            LOGGER.info("Se actualizó correctamente el paciente");
            return ResponseEntity.ok("Paciente actualizado: " + paciente.getNombre());
        } else {
            LOGGER.info("No se pudo actualizar el paciente, ID no encontrado");
            return ResponseEntity.badRequest().body("Paciente no encontrado: " + paciente.getNombre());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Paciente pacienteBuscado = iPacienteServicio.consultarPorId(id);

        if (pacienteBuscado != null) {
            iPacienteServicio.eliminar(id);
            LOGGER.info("Paciente eliminado con éxito, ID: " + id);
            return ResponseEntity.ok("Paciente eliminado con éxito: " + id);
        } else {
            LOGGER.warn("Paciente no encontrado, ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado con ID: " + id);
        }
    }
}
