package br.edu.up.projetofinal.models;

public class LivroFisico extends Livro{
    private int numeroPaginas;

    public LivroFisico(String titulo, String autor, String genero, String editora, int numeroPaginas) {
        super(titulo, autor, genero, editora);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return "LivroFisico{" +
                "uuid=" + getUuid() +
                ", titulo='" + getTitulo() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", genero='" + getGenero() + '\'' +
                ", editora=" + getEditora() + '\'' +
                ", paginas=" + numeroPaginas +
                '}';
    }
}
