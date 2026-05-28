package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "assentos")


public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @NotBlank(message = "A fileira do assento e obrigatoria")
    private String fileira;

    @NotNull(message ="O numero do assento e obrigatorio!")
    @Min(value = 1, message = "O numero deve ser maior que 0")
    private Integer numero;

}
