package br.edu.up.projetofinal.models;

import java.util.UUID;

public class Livro {
    private UUID uuid;
    private String titulo;
    private String autor;
    private String genero;
    private String editora;

    public Livro() { }

    public Livro(String titulo,String autor, String genero, String editora) {
        this.uuid = UUID.randomUUID();
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
    }

    public Livro(String dado, String dado1, String dado2) {
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

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEditora(String editora) {
        this.editora = editora;
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