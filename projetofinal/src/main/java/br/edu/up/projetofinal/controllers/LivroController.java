package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.daos.LivroDao;
import br.edu.up.projetofinal.models.Livro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.UuidUtil;

import java.io.*;
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


    // adicionar throws LivroNotFoundException
    public static Livro buscarPorUuid(UUID uuid) {
        var listaLivros = listar();
        Optional<Livro> livro = listaLivros.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findFirst();
        if (livro.isEmpty()) {
            //throw new LivroNotFoundException("Não foi encontrado nenhum Livro com o UUID: " + uuid)
        }
        return livro.get();
    }

    public static void cadastrar(Livro livro) {
        LivroDao.escrever(LIVRO_FILE_NAME, List.of(livro), true);
    }



}



    /*case 0 -> Util.showFeedbackMessage("");
            case 1 -> cadastar(scanner);
            case 2 -> atualizar(scanner);
            case 3 -> remover(scanner);
            case 4 -> listar();*/


