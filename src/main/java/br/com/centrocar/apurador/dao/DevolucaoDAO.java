package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.Venda;

/**
 * Classe responsável por gerenciar consultas de {@link Devolucao} ao banco de dados,
 * SELECT, UPDATE, INSERT, DELETE. <br>
 * Impleta o método seleciona da interface {@link VendasDAO}.
 * 
 * @author Williams Gomes
 * 
 * @version 1.1.0
 *
 */

public class DevolucaoDAO implements VendaDAO {

	/**
	 * Instância de um {@link GerenciadorDeConexao}.
	 */
	private final GerenciadorDeConexao g;

	/**
	 * Construtor que inicializa a instância do {@link GerenciadorDeConexao} <br>
	 * recebendo como valor o retorno do método {@code GerenciadorDeConexaoImpl.getGerenciador()} da classe {@link GerenciadorDeConexaoImpl}.
	 */
	public DevolucaoDAO() {
		this.g = GerenciadorDeConexaoImpl.getGerenciador();
	}
	
	/**
	 * Método recebe como parâmetro uma instância do tipo {@link Venda} e retorna uma {@link Devolucao}.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados retornando as soma das devoluções de um vendedor.
	 * 
	 * @throws SQLException <br> Lança uma {@link SQLException}.
	 * @param v <br> Recebe uma instância do tipo {@link Venda}.
	 * @return {@link Devolucao}.
	 */
	@Override
	public Venda seleciona(Venda v) throws SQLException {
		Devolucao d = (Devolucao) v;		//Cria uma referência para a classe TotalDeVendas e atribue a Venda a ela usando casting.
		Connection c = g.openConnection();
		Devolucao devolucaoVendas = null;
		String sql = "SELECT SUM(TOTALLIQUIDO) AS SOMA FROM MOVIMENTO " + "WHERE (DATAEMISSAO BETWEEN '"
				+ d.getDataInicial() + "' AND '" + d.getDataFinal() + "') "
				+ "AND (CODIGOOPERACAO IN ('0206001','0206002','0206003','0206004','0206006','0206008','0206015') "
				+ "AND STATUS<>'X') ";
		if (d.getVendedor().getFilial() != null && d.getVendedor().getFilial().equals("") == false) {
			sql += "AND CODIGOFILIALCONTABIL = '" + d.getVendedor().getFilial().trim() + "'";
		}
		if (d.getVendedor().getCodigo() != null && d.getVendedor().getCodigo().equals("") == false) {
			sql += "AND CODIGOFUNCIONARIO = '" + d.getVendedor().getCodigo().trim() + "'";
		}

		try (PreparedStatement stmt = c.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					BigDecimal total = rs.getBigDecimal("SOMA");
					devolucaoVendas = new Devolucao(total);
				}
			}
		}
		return devolucaoVendas;
	}
}
