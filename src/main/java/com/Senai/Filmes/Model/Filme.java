package com.Senai.Filmes.Model;

import com.Senai.Filmes.Model.Enums.GeneroFilme;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Entity
@Data
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "o titulo e obrigatorio")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String urlPoster;

    @NotNull(message = "O campo de genero e obrigatorio!")
    @Enumerated(EnumType.STRING)
    private GeneroFilme genero;


    @NotNull(message ="O campo de minutos e obrihatorio!")
    @Min(value = 1, message = "A duracao deve ser maior que 0")
    private Integer duracaoMinutos;

}
