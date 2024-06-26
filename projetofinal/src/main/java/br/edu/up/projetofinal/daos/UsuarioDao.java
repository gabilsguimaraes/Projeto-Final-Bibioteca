package br.edu.up.projetofinal.daos;

import br.edu.up.projetofinal.models.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class UsuarioDao extends BaseDao{

    private static final Logger logger = LogManager.getLogger(UsuarioDao.class);

    public static List<Usuario> listarUsuarios(String fileName) {
        logger.info("Iniciando a leitura dos dados de Usuarios");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linha = null;
            List<Usuario> usuarios = new ArrayList<>();
            while ((linha = reader.readLine()) != null) {
                var usuario = parse(linha);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de usuarios");
            return null;
        }
    }
    
    public static Usuario parse(String linha) {
        var dados = linha.split(";");
        var uuid = UUID.fromString(dados[0].toString());
        
        var usuario = new Usuario(dados[1]);
        usuario.setUuid(uuid);
        
        return usuario;
    }

}
