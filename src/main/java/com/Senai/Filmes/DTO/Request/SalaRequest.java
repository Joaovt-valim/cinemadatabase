package com.Senai.Filmes.DTO.Request;

public record SalaRequest(
String nomeSala,
Integer totalAssentos,
Integer fileiras,
Integer assentosPorFileira
) {}
