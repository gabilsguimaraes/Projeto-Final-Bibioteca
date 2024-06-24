package br.edu.up.projetofinal.views;

import br.edu.up.projetofinal.controllers.LivroController;
import br.edu.up.projetofinal.models.Livro;
import br.edu.up.projetofinal.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.UUID;

public class LivroView {
    private static final Logger logger = LogManager.getLogger(LivroView.class);

    public static void iniciar(Scanner scanner) {
        int op;
        do {
            exibirMenu();
            op = Util.lerMenu(scanner);
            exibirEscolha(scanner, op);
        } while (op != 0);

    }
    private static void exibirMenu() {
        System.out.println("╔════════════════════════╗");
        System.out.println("║      »BIBLIOTECA«      ║");
        System.out.println("╚════════════════════════╝");
        System.out.println("0 - »Sair«");
        System.out.println("1 - »Cadastrar«");
        System.out.println("2 - »Alterar«");
        System.out.println("3 - »Remover«");
        System.out.println("4 - »Listar«");
    }

    private static void exibirEscolha(Scanner scanner, int op) {
        switch (op) {
            case 0 -> Util.showFeedbackMessage("");
            case 1 -> cadastrar(scanner);
            case 2 -> atualizar(scanner);
            case 3 -> remover(scanner);
            case 4 -> listar();

            case 99 -> Util.showFeedbackMessage("Informe um valor inteiro.");
            default -> Util.showFeedbackMessage("Opção invalida! Favor escolher opção existente no menu.");
        }

        private static void cadastrar(Scanner scanner) {
            try {
                System.out.println("Digite o titulo: ");
                var titulo = scanner.nextLine();

                System.out.println("Digite o autor: ");
                var autor = scanner.nextLine();

                System.out.println("Digite o gênero: ");
                var genero = scanner.nextLine();

                System.out.println("Digite a editora: ");
                var editora = scanner.nextLine();

                /*Aqui a gente pode fazer igual com a opção de mostrar os dados
                do genero/autor/editora para dai selecionar por uuid:

                UsuarioView.exibirDadosUsuarios();
                System.out.println("Escolha o usuario por UUID: ");
                var uuid = scanner.nextLine();

                // Buscando os dados do usuario
                var usuario = UsuarioController.buscarUsuarioPorUUID(UUID.fromString(uuid));*/

                // criando o objeto
                var livro = new Livro(titulo, autor, genero, editora);

                //salvando o objeto tarefa
                LivroController.cadastrar(livro);

            } catch (Exception ex) {
                logger.error("Ocorreu um erro ao tentar cadastrar um livro.", ex);
            }
        }


        private static void atualizar(Scanner scanner) {
            try {
                listar();
                System.out.println("Qual Livro você deseja atualizar?");
                var uuid = scanner.nextLine();

                System.out.println("####################################");
                System.out.println("ATUALIZAÇÃO");
                System.out.println("####################################");

                System.out.println("Digite o título: ");
                var titulo = scanner.nextLine();

                System.out.println("Digite o autor: ");
                var autor = scanner.nextLine();

                System.out.println("Digite o gênero: ");
                var genero = scanner.nextLine();

                System.out.println("Digite a editora: ");
                var editora = scanner.nextLine();

                // criando o objeto
                var livro = new Livro();
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setGenero(genero);
                livro.setEditora(editora);

                //salvando o objeto tarefa
                TarefaController.atualizar(UUID.fromString(uuid), tarefa);
            } catch (TarefaNotFoundException ex) {
                Util.showFeedbackMessage(ex.getMessage());
                logger.warn("Ocorreu um erro ao tentar atualizar a tarefa.", ex);
            } catch (Exception ex) {
                var message = "Ocorreu um erro ao tentar criar uma tarefa.";
                Util.showFeedbackMessage(message);
                logger.error(message, ex);
            }
        }

        private static void remover(Scanner scanner) {
            try {
                listar();
                System.out.println("Qual Tarefa você deseja remover? ");
                var uuid = scanner.nextLine();

                TarefaController.remover(UUID.fromString(uuid));
            } catch (LivroNotFoundException ex) {
                Util.showFeedbackMessage(ex.getMessage(), true);
                logger.warn("Ocorreu um erro ao tentar remover o livro.", ex);
            }
        }

        private static void listar() {
            var livros = LivroController.listar();
            System.out.println("######## LISTA DE LIVROS ############");
            livros.forEach(livro -> {
                exibirDadosLivro(livro, false);
            });
            System.out.println("########################################");
        }
    }
}