package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Autor {
    private UUID uuid;
    private String nome;

    public Autor() { }

    public Autor(String nome) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
