package br.edu.up.projetofinal.daos;

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

    public static List<Emprestimo> listarEmprestimos(String fileName) {
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

    public static Emprestimo parse(String linha) {
        var dados = linha.split(";");
        var uuid = UUID.fromString(dados[0].toString());

        var emprestimo = new Emprestimo(dados[1]);
        emprestimo.setUuid(uuid);

        return emprestimo;
    }

}