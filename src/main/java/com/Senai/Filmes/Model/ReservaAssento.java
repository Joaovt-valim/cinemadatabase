package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.Assento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reserva_assentos")
public class ReservaAssento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;


    @ManyToOne
    @JoinColumn(name = "assento_id")
    private Assento assento;;
}
