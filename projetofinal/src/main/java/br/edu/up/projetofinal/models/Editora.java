package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Editora {
    private UUID uuid;
    private String nome;

    public Editora(UUID uuid, String nome) {
        this.uuid = uuid;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
