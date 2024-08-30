package com.dh.Backend1_TrabajoIntegrador.controlador;

import com.dh.Backend1_TrabajoIntegrador.entidad.Odontologo;
import com.dh.Backend1_TrabajoIntegrador.servicio.IOdontologoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoControlador {
    private IOdontologoServicio odontologoServicio;

    public OdontologoControlador(IOdontologoServicio odontologoServicio) {
        this.odontologoServicio = odontologoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoServicio.consultarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> consultarTodos() {
        return ResponseEntity.ok(odontologoServicio.consultarTodos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoServicio.guardar(odontologo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> modificar(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoServicio.modificar(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
