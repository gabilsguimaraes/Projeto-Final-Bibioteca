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

    public Autor getAutor() {
        return autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setEditora(Editora editora) {
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