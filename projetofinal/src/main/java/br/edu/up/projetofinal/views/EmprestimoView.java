package br.edu.up.projetofinal.views;

import br.edu.up.projetofinal.controllers.EmprestimoController;
import br.edu.up.projetofinal.controllers.LivroController;
import br.edu.up.projetofinal.controllers.UsuarioController;
import br.edu.up.projetofinal.exceptions.EmprestimoNotFoundException;
import br.edu.up.projetofinal.exceptions.UsuarioNotFoundException;
import br.edu.up.projetofinal.models.Emprestimo;
import br.edu.up.projetofinal.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.UUID;

public class EmprestimoView {

    private static final Logger logger = LogManager.getLogger(EmprestimoView.class);

    public static void iniciar(Scanner scanner) throws UsuarioNotFoundException {
        int op;
        do {
            exibirMenu();
            op = Util.lerMenu(scanner);
            exibirEscolha(scanner, op);
        } while (op != 0);
    }

    private static void exibirMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║       »EMPRESTIMO«       ║");
        System.out.println("╚══════════════════════════╝");
        System.out.println("1 - »Cadastrar«");
        System.out.println("2 - »Remover«");
        System.out.println("3 - »Listar«");
        System.out.println("0 - »Voltar«");
    }

    private static void exibirEscolha(Scanner scanner, int op) throws UsuarioNotFoundException {
        switch (op) {
            case 0 -> Util.showFeedbackMessage("");
            case 1 -> cadastrar(scanner);
            case 2 -> remover(scanner);
            case 3 -> listar();

            case 99 -> Util.showFeedbackMessage("Informe um valor inteiro.");
            default -> Util.showFeedbackMessage("Opção invalida! Favor escolher opção existente no menu.");
        }
    }

    private static void cadastrar(Scanner scanner) {
        try {
            System.out.print("Digite o UUID do usuário que fará o emprestimo: ");
            var uuidUsuario = scanner.nextLine();

            var usuario = UsuarioController.buscarUsuarioPorUUID(UUID.fromString(uuidUsuario));

            System.out.print("Digite o UUID do livro que será emprestado: ");
            var uuidLivro = scanner.nextLine();

            var livro = LivroController.buscarPorUuid(UUID.fromString(uuidLivro));

            var emprestimo = new Emprestimo(usuario, livro);

            EmprestimoController.cadastrar(emprestimo);

        } catch (Exception ex) {
            logger.error("Ocorreu um erro ao tentar cadastrar um emprestimo.", ex);
        }
    }

    /*private static void atualizar(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual emprestimo você deseja atualizar?");
            var uuid = scanner.nextLine();

            System.out.println("####################################");
            System.out.println("ATUALIZAÇÃO");
            System.out.println("####################################");

            System.out.print("Digite o nome atualizado: ");
            var nome = scanner.nextLine();

            var emprestimo = new Emprestimo(nome);
            emprestimo.setNome(nome);

            EmprestimoController.atualizar(UUID.fromString(uuid), emprestimo);
        } catch (EmprestimoNotFoundException ex) {

        } catch (Exception ex) {
            var message = "Ocorreu um erro ao tentar atualizar o usuário.";
            Util.showFeedbackMessage(message);
            logger.error(message, ex);
        }
    } */

    private static void remover(Scanner scanner) throws UsuarioNotFoundException {
        try {
            listar();
            System.out.println("Qual emprestimo você deseja remover? ");
            var uuid = scanner.nextLine();

            EmprestimoController.remover(UUID.fromString(uuid));
        } catch (EmprestimoNotFoundException ex) {
            Util.showFeedbackMessage(ex.getMessage(), true);
            logger.warn("Ocorreu um erro ao tentar remover o usuário.", ex);
        }
    }

    private static void listar() throws UsuarioNotFoundException {
        var emprestimos = EmprestimoController.listar();
        System.out.println("######## EMPRESTIMOS REALIZADOS ############");
        emprestimos.forEach(emprestimo -> {
            exibirDadosEmprestimos(emprestimo);
        });
        System.out.println("########################################");
    }

    public static void exibirDadosEmprestimos(Emprestimo emprestimo) {
            System.out.println("UUID: " + emprestimo.getUuid());
            System.out.println("USUARIO: " + emprestimo.getUsuario().getNome());
            System.out.println("LIVRO: " + emprestimo.getLivro().getTitulo());
            System.out.println("DATA EMPRESTIMO: " + emprestimo.getDataEmprestimo());
            System.out.println("DATA DEVOLUCAO: " + emprestimo.getDataDevolucao());
            System.out.println("-----------------------------------------");
    }
}
