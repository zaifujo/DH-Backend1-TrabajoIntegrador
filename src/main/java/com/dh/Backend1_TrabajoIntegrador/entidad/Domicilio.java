package com.dh.Backend1_TrabajoIntegrador.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;

    private Integer numero;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private String provincia;
}