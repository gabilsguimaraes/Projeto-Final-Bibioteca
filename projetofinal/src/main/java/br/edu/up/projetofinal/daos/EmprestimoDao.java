package br.edu.up.projetofinal.daos;

import br.edu.up.projetofinal.controllers.LivroController;
import br.edu.up.projetofinal.controllers.UsuarioController;
import br.edu.up.projetofinal.exceptions.LivroNotFoundException;
import br.edu.up.projetofinal.exceptions.UsuarioNotFoundException;
import br.edu.up.projetofinal.models.Emprestimo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class EmprestimoDao extends BaseDao{

    private static final Logger logger = LogManager.getLogger(EmprestimoDao.class);

    public static List<Emprestimo> listarEmprestimos(String fileName) throws LivroNotFoundException, UsuarioNotFoundException {
        logger.info("Iniciando a leitura dos dados de Emprestimos");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linha = null;
            List<Emprestimo> emprestimos = new ArrayList<>();
            while ((linha = reader.readLine()) != null) {
                var emprestimo = parse(linha);
                emprestimos.add(emprestimo);
            }
            return emprestimos;
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de emprestimos");
            return null;
        }
    }

    public static Emprestimo parse(String linha) throws LivroNotFoundException, UsuarioNotFoundException {
        var dados = linha.split(";");

        var uuid = UUID.fromString(dados[0].toString());
        var livroUUID = UUID.fromString(dados[1].toString());
        var livro = LivroController.buscarPorUuid(livroUUID);
        var usuarioUUID = UUID.fromString(dados[2].toString());
        var usuario = UsuarioController.buscarUsuarioPorUUID(usuarioUUID);

        var emprestimo = new Emprestimo(usuario, livro);
        emprestimo.setUuid(uuid);

        return emprestimo;
    }

}
