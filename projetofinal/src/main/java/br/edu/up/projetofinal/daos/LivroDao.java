package br.edu.up.projetofinal.daos;

import br.edu.up.projetofinal.models.Livro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.UuidUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class LivroDao extends BaseDao {
    private static final Logger logger = LogManager.getLogger(LivroDao.class);

    public static List<Livro> listarLivros(String fileName) {
        logger.info("Iniciando a leitura dos dados de Livros");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linha = null;
            List<Livro> livros = new ArrayList<>();
            while ( (linha = reader.readLine()) != null) {
                var livro = parse(linha);
                livros.add(livro);
            }
            return livros;
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao tentar ler os dados do arquivo de livros");
            return null;
        }
    }
    private static Livro parse(String linha) {
        var dados = linha.split(";");
        var uuid = UUID.fromString(dados[0].toString());


        var livro = new Livro (dados[1], dados[2], dados[3]);
        livro.setUuid(uuid);

        return livro;
    }


}