package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.daos.LivroDao;
import br.edu.up.projetofinal.exceptions.LivroNotFoundException;
import br.edu.up.projetofinal.models.FormatacaoEscrita;
import br.edu.up.projetofinal.models.Livro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LivroController {
    private static Logger logger = LogManager.getLogger(LivroController.class);
    private static final String LIVRO_FILE_NAME = "livro.txt";

    public static List<Livro> listar() {
        return LivroDao.listarLivros(LIVRO_FILE_NAME);
    }

    public static Livro buscarPorUuid(UUID uuid) throws LivroNotFoundException {
        var listaLivros = listar();
        Optional<Livro> livro = listaLivros.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findFirst();

        if (livro.isEmpty()) {
            throw new LivroNotFoundException("Não foi encontrado nenhum Livro com o UUID: " + uuid)
        }

        return livro.get();
    }

    public static void cadastrar(Livro livro) {
        LivroDao.escrever(LIVRO_FILE_NAME, List.of(livro), true);
    }

    public static void atualizar(UUID uuid, Livro livroAtualizado)  throws LivroNotFoundException {
        var livro = buscarPorUuid(uuid);
        livro.atualizarDados(livroAtualizado);

        var novaListaLivros = removerPorUuid(uuid);
        novaListaLivros.add(livro);
        LivroDao.escrever(LIVRO_FILE_NAME, novaListaLivros, false);
    }

    public static void remover(UUID uuid)  throws LivroNotFoundException{
        var livro = buscarPorUuid(uuid);
        var dados = removerPorUuid(uuid);
        LivroDao.escrever(LIVRO_FILE_NAME, dados, false);
    }

    private static List<FormatacaoEscrita> removerPorUuid(UUID uuid) {
        List<FormatacaoEscrita> dados = new ArrayList<>();
        var livros = listar();
        livros.forEach(t -> {
            if (!t.getUuid().equals(uuid)) {
                dados.add(t);
            }
        });
        return dados;
    }
}




