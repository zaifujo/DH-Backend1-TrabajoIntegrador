package com.dh.Backend1_TrabajoIntegrador.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "turnos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate fecha;

    // hora
}