package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoControlador {
    private IOdontologoServicio odontologoServicio;

    @Autowired
    public OdontologoControlador(IOdontologoServicio odontologoServicio) {
        this.odontologoServicio = odontologoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> consultarPorId(@PathVariable Long id) {
        return ResponseEntity
                .ok(odontologoServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> consultarTodos() {

        List<Odontologo> odontologos = odontologoServicio.consultarTodos();

        if (odontologos.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build(); // 204 No Content
        } else {
            return ResponseEntity
                    .ok(odontologos); // 200 OK
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(odontologoServicio.guardar(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> modificar(@RequestBody Odontologo odontologo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(odontologoServicio.modificar(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        odontologoServicio.eliminar(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
