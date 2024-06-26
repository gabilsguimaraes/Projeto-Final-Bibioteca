package br.edu.up.projetofinal.controllers;

import br.edu.up.projetofinal.daos.UsuarioDao;
import br.edu.up.projetofinal.exceptions.UsuarioNotFoundException;
import br.edu.up.projetofinal.models.FormatacaoEscrita;
import br.edu.up.projetofinal.models.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsuarioController {

    private  static  final Logger logger = LogManager.getLogger(UsuarioController.class);
    private static final String USUARIO_FILE_NAME = "usuario.txt";
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

    public static void cadastrar(Usuario usuario) {
        UsuarioDao.escrever(USUARIO_FILE_NAME, List.of((FormatacaoEscrita) usuario), true);
    }

    public static void atualizar(UUID uuid, Usuario usuarioAtualizado) throws UsuarioNotFoundException {
        var usuario = buscarUsuarioPorUUID(uuid);
        usuario.atualizarDadosUsuario(usuarioAtualizado);

        var novaListaUsuarios = removerUsuarioPorUuid(uuid);
        novaListaUsuarios.add((FormatacaoEscrita) usuario);
        UsuarioDao.escrever(USUARIO_FILE_NAME, novaListaUsuarios, false);
    }

    public static void remover(UUID uuid) throws UsuarioNotFoundException {
        var usuario = buscarUsuarioPorUUID(uuid);
        var dados = removerUsuarioPorUuid(uuid);
        UsuarioDao.escrever(USUARIO_FILE_NAME, dados, false);
    }

    private static List<FormatacaoEscrita> removerUsuarioPorUuid(UUID uuid) {
        List<FormatacaoEscrita> dados = new ArrayList<>();
        var usuarios = listar();
        usuarios.forEach(t -> {
            if (!t.getUuid().equals(uuid)) {
                dados.add((FormatacaoEscrita) t);
            }
        });
        return dados;
    }

}