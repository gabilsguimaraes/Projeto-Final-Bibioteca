package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.daos.EmprestimoDao;
import br.edu.up.projetofinal.exceptions.EmprestimoNotFoundException;
import br.edu.up.projetofinal.exceptions.LivroNotFoundException;
import br.edu.up.projetofinal.exceptions.UsuarioNotFoundException;
import br.edu.up.projetofinal.models.Emprestimo;
import br.edu.up.projetofinal.models.FormatacaoEscrita;
import br.edu.up.projetofinal.models.LivroDigital;
import br.edu.up.projetofinal.models.LivroFisico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmprestimoController {

    private  static  final Logger logger = LogManager.getLogger(EmprestimoController.class);
    private static final String EMPRESTIMO_FILE_NAME = "emprestimo.txt";

    public static List<Emprestimo> listar() throws UsuarioNotFoundException {
        try {
            return EmprestimoDao.listarEmprestimos(EMPRESTIMO_FILE_NAME);
        } catch (LivroNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Emprestimo buscarEmprestimoPorUUID(UUID uuid) throws EmprestimoNotFoundException, UsuarioNotFoundException {
        var listarEmprestimos = listar();
        Optional<Emprestimo> emprestimo = listarEmprestimos.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findFirst();

        if (emprestimo.isEmpty()) {
            throw new EmprestimoNotFoundException("Não foi encontrado nenhum emprestimo com o UUID: " + uuid);
        }

        return emprestimo.get();
    }

    public static void cadastrar(Emprestimo emprestimo) {
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, List.of(emprestimo), true);
    }

    /*public static void atualizar(UUID uuid, Emprestimo emprestimoAtualizado) throws EmprestimoNotFoundException {
        var emprestimo = buscarEmprestimoPorUUID(uuid);
        emprestimo.atualizarDados(emprestimoAtualizado);

        var novaListaEmprestimos = removerPorUuid(uuid);
        novaListaEmprestimos.add(emprestimo);
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, novaListaEmprestimos, false);
    } */

    public static void remover(UUID uuid) throws EmprestimoNotFoundException, UsuarioNotFoundException {
        var emprestimo = buscarEmprestimoPorUUID(uuid);
        var dados = removerEmprestimoPorUuid(uuid);
        EmprestimoDao.escrever(EMPRESTIMO_FILE_NAME, dados, false);
    }

    private static List<FormatacaoEscrita> removerEmprestimoPorUuid(UUID uuid) throws UsuarioNotFoundException {
        List<FormatacaoEscrita> dados = new ArrayList<>();
        var emprestimos = listar();
        emprestimos.forEach(t -> {
            if (!t.getUuid().equals(uuid)) {
                dados.add((FormatacaoEscrita) t);
            }
        });
        return dados;
    }
    private static void exibirDadosEmprestimo(Emprestimo emprestimo) {
        System.out.println("UUID: " + emprestimo.getUuid());
        System.out.println("USUÁRIO: " + emprestimo.getUsuario().getNome());
        System.out.println("LIVRO: " + emprestimo.getLivro().getTitulo());

        if (emprestimo.getLivro() instanceof LivroFisico) {
            System.out.println("TIPO DE LIVRO: Físico");
            System.out.println("NÚMERO DE PÁGINAS: " + ((LivroFisico) emprestimo.getLivro()).getNumeroPaginas());
        } else if (emprestimo.getLivro() instanceof LivroDigital) {
            System.out.println("TIPO DE LIVRO: Digital");
            System.out.println("TAMANHO DO ARQUIVO: " + ((LivroDigital) emprestimo.getLivro()).getTamanhoArquivo() + " MB");
        }


    }
}

