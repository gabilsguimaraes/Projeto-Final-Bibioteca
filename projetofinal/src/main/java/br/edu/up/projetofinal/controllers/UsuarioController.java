package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.models.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsuarioController {
    private static List<Usuario> usuarios = List.of(
            new Usuario("João"),
            new Usuario("Anna")
    );

    public static List<Usuario> listar() {
        return usuarios;
    }

    public static Usuario buscarUsuarioPorUUID(UUID uuid) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst();
        return usuario.isPresent() ? usuario.get() : null;
    }

    public static Usuario buscarUsuarioPorNome(String nome) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
        return usuario.isPresent() ? usuario.get() : null;

    }
}