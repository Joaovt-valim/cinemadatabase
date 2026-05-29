package com.Senai.Filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessoes")
@NoArgsConstructor

public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @NotNull
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    @NotNull
    private Sala sala;

    @NotNull(message = "O horario do inicio e obrigatorio")
    private LocalDateTime inicio;

    @NotNull(message = "O horario do fim e obrigatorio")
    private LocalDateTime fim;

    @NotNull(message = "O preco e obrigatorio")
    private BigDecimal preco;

}
