package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OdontologoControlador(IOdontologoServicio odontologoServicio) {
        this.odontologoServicio = odontologoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> consultarPorId(@PathVariable Long id) {

        Odontologo odontologoBuscado = odontologoServicio.consultarPorId(id);

        if (odontologoBuscado != null) {
            LOGGER.info("Odontólogo encontrado: " + odontologoBuscado.toString());
            return ResponseEntity.ok(odontologoBuscado);
        } else {
            LOGGER.info("No se encontró odontólogo con ID:" + id);
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
            LOGGER.info("Odontólogo registrado con éxito: " + odontologoGuardado.toString());
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
            Odontologo odontologoModificado = odontologoServicio.modificar(odontologo);
            LOGGER.info("Se actualizó correctamente el odontólogo: " + odontologoModificado.toString());
            return ResponseEntity.ok("Odontólogo actualizado: " + odontologo.getId());
        } else {
            LOGGER.info("No se pudo actualizar odontólogo, ID no encontrado: " + odontologo.getId());
            return ResponseEntity.badRequest().body("Odontólogo no encontrado: " + odontologo.getId());
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
