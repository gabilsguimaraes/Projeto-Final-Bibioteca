package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Livro {
    private UUID uuid;
    private String titulo;
    private Autor autor;
    private Genero genero;
    private Editora editora;

    public Livro() { }

    public Livro(String titulo,Autor autor, Genero genero, Editora editora) {
        this.uuid = UUID.randomUUID();
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public Editora getEditora() {
        return editora;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "uuid=" + uuid +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", editora=" + editora +
                '}';
    }
}