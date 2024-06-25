package br.edu.up.projetofinal.models;

public class LivroDigital extends Livro {
    private double tamanhoArquivo;

    public LivroDigital(String titulo, String autor, String genero, String editora, double tamanhoArquivo) {
        super(titulo, autor, genero, editora);
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    @Override
    public String toString() {
        return "LivroDigital{" +
                "uuid=" + getUuid() +
                ", titulo='" + getTitulo() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", genero='" + getGenero() + '\'' +
                ", editora=" + getEditora() + '\'' +
                ", tamanho=" + tamanhoArquivo +
                '}';
    }
}
