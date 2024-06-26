package br.edu.up.projetofinal.views;

import br.edu.up.projetofinal.controllers.UsuarioController;
import br.edu.up.projetofinal.models.Usuario;
import br.edu.up.projetofinal.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.UUID;

public class UsuarioView {

    private static final Logger logger = LogManager.getLogger(UsuarioView.class);

    public static void iniciar(Scanner scanner) {
        int op;
        do {
            exibirMenu();
            op = Util.lerMenu(scanner);
            exibirEscolha(scanner, op);
        } while (op != 0);
    }

    private static void exibirMenu() {
        System.out.println("╔═════════════════════════╗");
        System.out.println("║        »USUÁRIO«        ║");
        System.out.println("╚═════════════════════════╝");
        System.out.println("1 - »Cadastrar«");
        System.out.println("2 - »Alterar«");
        System.out.println("3 - »Remover«");
        System.out.println("4 - »Listar«");
        System.out.println("0 - »Sair«");
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
            System.out.print("Digite o nome do usuário: ");
            var nome = scanner.nextLine();

            var usuario = new Usuario(nome);

            UsuarioController.cadastrar(usuario);

        } catch (Exception ex) {
            logger.error("Ocorreu um erro ao tentar cadastrar um livro.", ex);
        }
    }

    private static void atualizar(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual usuário você deseja atualizar?");
            var uuid = scanner.nextLine();

            System.out.println("####################################");
            System.out.println("ATUALIZAÇÃO");
            System.out.println("####################################");

            System.out.print("Digite o nome atualizado: ");
            var nome = scanner.nextLine();

            var usuario = new Usuario(nome);
            usuario.setNome(nome);

            UsuarioController.atualizar(UUID.fromString(uuid), usuario);
        } catch (UsuarioNotFoundException ex) {

        } catch (Exception ex) {
            var message = "Ocorreu um erro ao tentar atualizar o usuário.";
            Util.showFeedbackMessage(message);
            logger.error(message, ex);
        }
    }

    private static void remover(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual usuário você deseja remover? ");
            var uuid = scanner.nextLine();

            UsuarioController.remover(UUID.fromString(uuid));
        } catch (UsuarioNotFoundException ex) {
            Util.showFeedbackMessage(ex.getMessage(), true);
            logger.warn("Ocorreu um erro ao tentar remover o usuário.", ex);
        }
    }

    private static void listar() {
        var usuarios = UsuarioController.listar();
        System.out.println("######## LIVROS NO CATALOGO ############");
        usuarios.forEach(livro -> {
            exibirDadosLivro(livro, false);
        });
        System.out.println("########################################");
    }

    public static void exibirDadosUsuarios() {
        var usuarios = UsuarioController.listar();
        System.out.println("######## LISTA DE USUARIOS ############");
        usuarios.forEach(usuario -> {
            System.out.println("UUID: " + usuario.getUuid());
            System.out.println("NOME: " + usuario.getNome());
            System.out.println("-----------------------------------------");
        });
        System.out.println("########################################");
    }
}
