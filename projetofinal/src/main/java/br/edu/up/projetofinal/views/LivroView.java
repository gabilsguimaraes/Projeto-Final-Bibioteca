package br.edu.up.projetofinal.views;

import br.edu.up.projetofinal.controllers.LivroController;
import br.edu.up.projetofinal.exceptions.LivroNotFoundException;
import br.edu.up.projetofinal.models.Livro;
import br.edu.up.projetofinal.models.LivroDigital;
import br.edu.up.projetofinal.models.LivroFisico;
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
        System.out.println("1 - »Cadastrar«");
        System.out.println("2 - »Alterar«");
        System.out.println("3 - »Remover«");
        System.out.println("4 - »Listar«");
        System.out.println("0 - »Voltar«");
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
    }

    private static void cadastrar(Scanner scanner) {
        try {
            System.out.print("Digite o titulo: ");
            var titulo = scanner.nextLine();

            System.out.print("Digite o autor: ");
            var autor = scanner.nextLine();

            System.out.print("Digite o gênero: ");
            var genero = scanner.nextLine();

            System.out.print("Digite a editora: ");
            var editora = scanner.nextLine();

            System.out.print("O livro é físico ou digital? (F/D): ");
            var tipo = scanner.nextLine().toUpperCase();

            Livro livro;

            if (tipo.equals("F")) {
                System.out.print("Digite o número de páginas: ");
                var numeroPaginas = Integer.parseInt(scanner.nextLine());
                livro = new LivroFisico(titulo, autor, genero, editora, numeroPaginas);
            } else if (tipo.equals("D")) {
                System.out.print("Digite o tamanho do arquivo em MB: ");
                var tamanhoArquivo = Double.parseDouble(scanner.nextLine());
                livro = new LivroDigital(titulo, autor, genero, editora, tamanhoArquivo);
            } else {
                System.out.println("Tipo de livro inválido!");
                return;
            }

            LivroController.cadastrar(livro);
        } catch (Exception ex) {
            logger.error("Ocorreu um erro ao tentar cadastrar um livro.", ex);
        }
    }



    private static void atualizar(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual Livro você deseja atualizar? ");
            var uuid = scanner.nextLine();

            System.out.println("####################################");
            System.out.println("ATUALIZAÇÃO");
            System.out.println("####################################");

            System.out.print("Digite o título: ");
            var titulo = scanner.nextLine();

            System.out.print("Digite o autor: ");
            var autor = scanner.nextLine();

            System.out.print("Digite o gênero: ");
            var genero = scanner.nextLine();

            System.out.print("Digite a editora: ");
            var editora = scanner.nextLine();

            // salvando o objeto
            var livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setGenero(genero);
            livro.setEditora(editora);

            //salvando o objeto
            LivroController.atualizar(UUID.fromString(uuid), livro);
        } catch (LivroNotFoundException ex) {
            Util.showFeedbackMessage(ex.getMessage());
            logger.warn("Ocorreu um erro ao tentar atualizar o livro.", ex);
        } catch (Exception ex) {
            var message = "Ocorreu um erro ao tentar cadastrar um livro.";
            Util.showFeedbackMessage(message);
            logger.error(message, ex);
        }
    }

    private static void remover(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual Livro você deseja remover? ");
            var uuid = scanner.nextLine();

            LivroController.remover(UUID.fromString(uuid));
        } catch (LivroNotFoundException ex) {
            Util.showFeedbackMessage(ex.getMessage(), true);
            logger.warn("Ocorreu um erro ao tentar remover o livro.", ex);
        }
    }

    private static void listar() {
        var livros = LivroController.listar();
        System.out.println("\n######## LIVROS NO CATALOGO ############");
        livros.forEach(livro -> {
            exibirDadosLivro(livro, false);
        });
        System.out.println("########################################\n");
    }

    private static void exibirDadosLivro(Livro livro, boolean exibirDetalhes) {
        System.out.println("UUID: " + livro.getUuid());
        System.out.println("TITULO: " + livro.getTitulo());
        System.out.println("AUTOR: " + livro.getAutor());
        System.out.println("GENERO: " + livro.getGenero());
        System.out.println("EDITORA: " + livro.getEditora());

        if (exibirDetalhes) {
            //aqui tbm colocar se é físico ou digital
            System.out.println("AUTOR: " + livro.getAutor());
            System.out.println("GENERO: " + livro.getGenero());
            System.out.println("EDITORA: " + livro.getEditora());
        }
        System.out.println("-----------------------------------------");

    }
}
