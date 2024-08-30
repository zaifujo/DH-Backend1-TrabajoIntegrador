package com.dh.Backend1_TrabajoIntegrador.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    @Id
    private Long id;
    @ManyToOne //(cascade = CascadeType.ALL)
    private Odontologo odontologo;
    @ManyToOne
    private Paciente paciente;
    private LocalDate fecha;
}
