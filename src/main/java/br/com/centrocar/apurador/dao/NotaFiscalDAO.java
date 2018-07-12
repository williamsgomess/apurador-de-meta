package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.Venda;

/**
 * Classe responsável por gerenciar consultas de {@link NotaFiscal} ao banco de dados,
 * SELECT, UPDATE, INSERT, DELETE entre outros... <br>
 * Implementa o método {@code seleciona} da interface {@link VendasDAO}.
 * 
 * @author Williams Gomes
 * 
 * @version 1.1.0
 *
 */

public class NotaFiscalDAO implements VendaDAO {

	/**
	 * Instância de um {@link GerenciadorDeConexao}.
	 */
	private final GerenciadorDeConexao g;

	/**
	 * Construtor da classe sem parâmetros que inicializa a instância do {@link GerenciadorDeConexao} <br>
	 * recebendo como valor o retorno do método {@code GerenciadorDeConexaoImpl.getGerenciador()} da classe {@link GerenciadorDeConexaoImpl}.
	 */
	public NotaFiscalDAO() {
		this.g = GerenciadorDeConexaoImpl.getGerenciador();
	}

	/**
	 * Método recebe como parâmetro uma instância do tipo {@link Venda} e retorna uma {@link NotaFiscal}.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados retornando as soma das Notas Fiscais de um vendedor.
	 * 
	 * @throws SQLException <br> Lança uma {@link SQLException}.
	 * @param v <br> Recebe uma instância do tipo {@link Venda}.
	 * @return {@link NotaFiscal}.
	 */
	@Override
	public Venda seleciona(Venda v) throws SQLException {

		NotaFiscal nf = (NotaFiscal) v;			//Cria uma referência para a classe TotalDeVendas e atribue a Venda a ela usando casting.
		Connection c = g.openConnection();
		NotaFiscal vendasNotaFiscal = null;
		String sql = "SELECT SUM(TOTALLIQUIDO) AS SOMA FROM MOVIMENTO " + "WHERE (DATAEMISSAO BETWEEN '"
				+ nf.getDataInicial() + "' AND '" + nf.getDataFinal() + "') "
				+ "AND (CODIGOOPERACAO IN ('0202005','0202060','0202030') " + "AND STATUS<>'X') ";
		if (nf.getVendedor().getFilial() != null && nf.getVendedor().getFilial().equals("") == false) {
			sql += "AND CODIGOFILIALCONTABIL = '" + nf.getVendedor().getFilial().trim() + "'";
		}
		if (nf.getVendedor().getCodigo() != null && nf.getVendedor().getCodigo().equals("") == false) {
			sql += "AND CODIGOFUNCIONARIO = '" + nf.getVendedor().getCodigo().trim() + "'";
		}

		try (PreparedStatement stmt = c.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					BigDecimal total = rs.getBigDecimal("SOMA");
					vendasNotaFiscal = new NotaFiscal(total);
				}
			}
		}
		return vendasNotaFiscal;
	}
}
