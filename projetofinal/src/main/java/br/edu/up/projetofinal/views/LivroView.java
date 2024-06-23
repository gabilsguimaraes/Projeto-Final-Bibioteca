package br.edu.up.projetofinal.views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToDoView {
    private static final Logger logger = LogManager.getLogger(ToDoView.class);

    public static void iniciar(Scanner scanner) {
        int op;
        do {
            exibirMenu();
            op = Util.lerOpcoesMenu(scanner);
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
            case 1 -> cadastar(scanner);
            case 2 -> atualizar(scanner);
            case 3 -> remover(scanner);
            case 4 -> listar();

            case 99 -> Util.showFeedbackMessage("Informe um valor inteiro.");
            default -> Util.showFeedbackMessage("Opção invalida! Favor escolher opção existente no menu.");
        }
    }
}