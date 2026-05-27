package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime criadoEM;

    @NotNull(message = "O status e obrigatorio")
    @Enumerated(EnumType.STRING)
    private Cargo status;


    private Integer sessao_id;


    private Integer usuario_id;
}
