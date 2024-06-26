package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.daos.EmprestimoDao;
import br.edu.up.projetofinal.exceptions.EmprestimoNotFoundException;
import br.edu.up.projetofinal.models.Emprestimo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmprestimoController {
    private static List<Emprestimo> emprestimos = List.of(
            new Emprestimo("João"),
            new Emprestimo("Anna")
    );

    public static List<Emprestimo> listar() {
        return emprestimos;
    }

    public static Emprestimo buscarEmprestimoPorUUID(UUID uuid) {
        Optional<Emprestimo> emprestimo = emprestimos.stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst();
        return emprestimo.isPresent() ? emprestimo.get() : null;
    }

    public static Emprestimo buscarEmprestimoPorNome(String nome) {
        Optional<Emprestimo> emprestimo = emprestimos.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
        return emprestimo.isPresent() ? emprestimo.get() : null;

    }

    public static void cadastrar(Emprestimo emprestimo) {
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, List.of(emprestimo), true);
    }

    public static void atualizar(UUID uuid, Emprestimo emprestimoAtualizado) throws EmprestimoNotFoundException {
        var emprestimo = buscarEmprestimoPorUUID(uuid);
        emprestimo.atualizarDados(emprestimoAtualizado);

        var novaListaEmprestimos = removerPorUuid(uuid);
        novaListaEmprestimos.add(emprestimo);
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, novaListaEmprestimos, false);
    }

    public static void remover(UUID uuid) throws EmprestimoNotFoundException {
        var emprestimo = buscarEmprestimoPorUUID(uuid);
        var dados = removerEmprestimoPorUuid(uuid);
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, dados, false);
    }

}