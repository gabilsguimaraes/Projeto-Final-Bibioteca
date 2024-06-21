package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Genero {
    private UUID uuid;
    private String nome;

    public Genero(UUID uuid, String nome) {
        this.uuid = uuid;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
