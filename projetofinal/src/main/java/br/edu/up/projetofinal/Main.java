package br.edu.up.projetofinal;

import br.edu.up.projetofinal.utils.Util;
import br.edu.up.projetofinal.views.LivroView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        int op = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            exibirMenu();
            op = Util.lerMenu(scanner);
            processaEscolhaUsuario(scanner, op);
        } while (op != 0);
    }

    private static void processaEscolhaUsuario(Scanner scanner, int op) {
        switch (op) {
            case 0 -> System.out.println("Programa encerrado");
            case 1 -> LivroView.iniciar(scanner);
            case 99 -> Util.showFeedbackMessage("Informe um valor inteiro.");
            default -> Util.showFeedbackMessage("Opção invalida! Favor escolher opção existente no menu.");
        }
    }

    /**
     * Método responsável por exibir o menu principal
     */
    private static void exibirMenu() {
        System.out.println("╔════════════════════════╗");
        System.out.println("║         »Menu«         ║");
        System.out.println("╚════════════════════════╝");
        System.out.println("0 - »Sair«");
        System.out.println("1 - »Biblioteca«");
    }
}