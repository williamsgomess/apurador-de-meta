package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.Venda;

/**
 * Classe responsável por gerenciar consultas de {@link SemValorFiscal} ao banco de dados,
 * SELECT, UPDATE, INSERT, DELETE <br>
 * Implementa o método {@code seleciona} da interface {@link VendasDAO}.
 * 
 * @author Williams Gomes
 * 
 * @version 1.1.0
 *
 */

public class SemValorFiscalDAO implements VendaDAO {

	/**
	 * Instância de um {@link GerenciadorDeConexao}.
	 */
	private final GerenciadorDeConexao g;

	/**
	 * Construtor da classe sem parâmetros que inicializa a instância do {@link GerenciadorDeConexao} <br>
	 * recebendo como valor o retorno do método {@code GerenciadorDeConexaoImpl.getGerenciador()} da classe {@link GerenciadorDeConexaoImpl}.
	 */
	public SemValorFiscalDAO() {
		this.g = GerenciadorDeConexaoImpl.getGerenciador();
	}

	/**
	 * Método recebe como parâmetro uma {@link Venda} e retorna uma venda {@link SemValorFiscal}.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados retornando as soma das vendas sem valor fiscal de um vendedor.
	 * 
	 * @throws SQLException <br> Lança uma {@link SQLException}.
	 * @param v <br> Recebe uma instância do tipo {@link Venda}.
	 * @return {@link SemValorFiscal}.
	 */
	@Override
	public Venda seleciona(Venda v) throws SQLException {

		SemValorFiscal svf = (SemValorFiscal) v;	//Cria uma referência para a classe TotalDeVendas e atribue a Venda a ela usando casting.
		Connection c = g.openConnection();
		SemValorFiscal vendasSVF = null;
		String sql = "SELECT SUM(TOTALLIQUIDO) AS SOMA FROM MOVIMENTO " + "WHERE (DATAEMISSAO BETWEEN '"
				+ svf.getDataInicial() + "' AND '" + svf.getDataFinal() + "') "
				+ "AND (CODIGOOPERACAO IN ('0201022','0201021') " + "AND STATUS<>'X') ";
		if (svf.getVendedor().getFilial() != null && svf.getVendedor().getFilial().equals("") == false) {
			sql += "AND CODIGOFILIALCONTABIL = '" + svf.getVendedor().getFilial().trim() + "'";
		}
		if (svf.getVendedor().getCodigo() != null && svf.getVendedor().getCodigo().equals("") == false) {
			sql += "AND CODIGOFUNCIONARIO = '" + svf.getVendedor().getCodigo().trim() + "'";
		}

		try (PreparedStatement stmt = c.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					BigDecimal total = rs.getBigDecimal("SOMA");
					vendasSVF = new SemValorFiscal(total);
				}
			}
		}
		return vendasSVF;
	}
}
