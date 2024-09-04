package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoControlador {
    private IOdontologoServicio odontologoServicio;
    private static final Logger LOGGER = Logger.getLogger(OdontologoControlador.class);

    public OdontologoControlador(IOdontologoServicio odontologoServicio) {
        this.odontologoServicio = odontologoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> consultarPorId(@PathVariable("id") Long id) {

        Odontologo odontologoBuscado = odontologoServicio.consultarPorId(id);

        if (odontologoBuscado != null) {
            LOGGER.info("Buscando odontologo por ID con éxito");
            return ResponseEntity.ok(odontologoBuscado);
        } else {
            LOGGER.info("No se encontró odontologo por ID");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> consultarTodos() {

        List<Odontologo> odontologos = odontologoServicio.consultarTodos();

        if (odontologos.isEmpty()) {
            LOGGER.info("No se encontraron odontólogos.");
            return ResponseEntity.noContent().build();
        } else {
            LOGGER.info("Consulta de odontólogos exitosa.");
            return ResponseEntity.ok(odontologos);
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {

        Odontologo odontologoGuardado = odontologoServicio.guardar(odontologo);

        if (odontologoGuardado != null) {
            LOGGER.info("Odontólogo registrado con éxito, ID: " + odontologoGuardado.getId());
            return ResponseEntity.created(URI.create("/odontologos/" + odontologoGuardado.getId()))
                    .body(odontologoGuardado);
        } else {
            LOGGER.warn("No se pudo registrar el odontólogo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody Odontologo odontologo) {

        Odontologo odontologoBuscado = odontologoServicio.consultarPorId(odontologo.getId());

        if (odontologoBuscado != null) {
            odontologoServicio.modificar(odontologo);
            LOGGER.info("Se actualizó correctamente el odontólogo");
            return ResponseEntity.ok("Odontólogo actualizado: " + odontologo.getNombre());
        } else {
            LOGGER.info("No se pudo actualizar odontólogo, ID no encontrado");
            return ResponseEntity.badRequest().body("Odontólogo no encontrado: " + odontologo.getNombre());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Odontologo odontologoBuscado = odontologoServicio.consultarPorId(id);

        if (odontologoBuscado != null) {
            odontologoServicio.eliminar(id);
            LOGGER.info("Odontólogo eliminado con éxito, ID: " + id);
            return ResponseEntity.ok("Odontólogo eliminado con éxito: " + id);
        } else {
            LOGGER.warn("Odontólogo no encontrado, ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Odontólogo no encontrado con ID: " + id);
        }
    }
}
