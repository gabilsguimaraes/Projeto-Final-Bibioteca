package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Usuario {
    private UUID uuid;
    private String nome;

    public Usuario() {
    }

    public Usuario(String nome) {
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

    public void atualizarDadosUsuario(Usuario usuario) {
        this.nome = usuario.getNome();
    }
}
