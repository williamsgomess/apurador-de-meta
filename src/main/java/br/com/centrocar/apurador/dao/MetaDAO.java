package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.Meta;

/**
 * Classe responsável por gerenciar consultas de {@link Meta} ao banco de dados,
 * SELECT, UPDATE, INSERT, DELETE entre outros... <br>
 * 
 * @author Williams Gomes
 * @version 1.1.0
 */

public class MetaDAO {

	/**
	 * Instância de um {@link GerenciadorDeConexao}.
	 */
	private final GerenciadorDeConexao g;

	/**
	 * Construtor da classe, que inicializa a instância do {@link GerenciadorDeConexao} <br>
	 * recebendo como valor o retorno do método {@code GerenciadorDeConexaoImpl.getGerenciador()} da classe {@link GerenciadorDeConexaoImpl}.
	 */
    public MetaDAO() {
        this.g = GerenciadorDeConexaoImpl.getGerenciador();
    }

    /**
	 * Método recebe como parâmetro uma instância do tipo {@link Meta} e retorna uma {@link Meta}.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados retornando a meta de um vendedor.
	 * 
	 * @throws Lança uma {@link SQLException}.
	 * @param meta <br> Recebe uma instância do tipo {@link Meta}.
	 * @return {@link Meta}.
     * @throws Exception 
	 */
    public Meta seleciona(Meta meta) throws Exception {

        Connection conn = g.openConnection();
        Meta metaAtual = null;

        String sql = "SELECT C.CODIGOMETA AS COD_META, C.DATA AS DATA, "
                + "M.CODIGOFUNCIONARIO AS FUNCIONARIO, M.CODIGOMETA AS FILIAL, M.META AS META "
                + "FROM CADMETA AS C INNER JOIN METAFUNCIONARIO AS M "
                + "ON C.CODIGOMETA=M.CODIGOMETA "
                + "WHERE C.CODIGOMETA IS NOT NULL ";
        if (meta.getVendedor().getCodigo() != null && meta.getVendedor().getCodigo().equals("") == false) {
            sql += "AND M.CODIGOFUNCIONARIO = '" + meta.getVendedor().getCodigo() + "' ";
        }
        if (meta.getDataMeta() != null) {
            sql += "AND SUBSTRING(CONVERT(CHAR(8) ,C.DATA , 112),1,6) = '" + meta.getDataMeta() + "'";
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal valorMeta = rs.getBigDecimal("META");
                    metaAtual = new Meta(valorMeta);
                }
            }
        }
        return metaAtual;
    }
}
