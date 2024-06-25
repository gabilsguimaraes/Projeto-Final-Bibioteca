package br.edu.up.projetofinal.views;

import br.edu.up.projetofinal.controllers.UsuarioController;

public class UsuarioView {
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
