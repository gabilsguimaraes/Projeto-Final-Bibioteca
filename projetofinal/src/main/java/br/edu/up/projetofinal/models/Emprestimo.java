package br.edu.up.projetofinal.models;

import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {
    private UUID uuid;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(UUID uuid, Usuario usuario, Livro livro, LocalDate dataDevolucao) {
        this.uuid = UUID.randomUUID();
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = calcDevolucao(dataEmprestimo);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate calcDevolucao(LocalDate dataEmprestimo) {
        return dataEmprestimo.plusDays(7);
    }

    @Override
    public String toString() {
        return "LivroDigital{" +
                "uuid=" + getUuid() +
                ", livro='" + livro + '\'' +
                ", usuario='" + usuario + '\'' +
                ", emprestimo=" + dataEmprestimo + '\'' +
                ", devolucao=" + dataDevolucao +
                '}';
    }
}
