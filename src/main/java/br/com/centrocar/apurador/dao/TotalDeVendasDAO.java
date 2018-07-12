package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Venda;

/**
 * Classe que gerencia as consultas ao banco de dados que buscam o {@link TotalDeVendas}.
 * SELECT, UPDATE, INSERT, DELETE <br>
 * Implementa o método {@code seleciona} da interface {@link VendasDAO}.
 * 
 * @author Williams Gomes
 * @version 1.1.0
 */

public class TotalDeVendasDAO implements VendaDAO {

	private final GerenciadorDeConexao g;		//Referência para um Gerenciado de Conexão.

	/**
	 * Construtor que referencia um {@link GerenciadorDeConexao} recebendo um {@code GerenciadorDeConexaoImpl.getGerenciador}.
	 */
	public TotalDeVendasDAO() {
        this.g = GerenciadorDeConexaoImpl.getGerenciador();
    }

	/**
	 * Método recebe como parâmetro uma {@link Venda} e retorna o total de todas as vendas efetivadas.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados 
	 * retornando as soma das todas as vendas efetivadas de um vendedor.
	 * 
	 * @throws Lança uma {@link SQLException}.
	 * @param v <br> Recebe uma instância do tipo {@link Venda}.
	 * @return {@link SemValorFiscal}.
	 */
	@Override
	public Venda seleciona(Venda v) throws SQLException {
		
		TotalDeVendas tv = (TotalDeVendas) v;	//Cria uma referência para a classe TotalDeVendas e atribue a Venda a ela usando casting.
		Connection c = g.openConnection();
		TotalDeVendas totalVendas = null;
		String sql = "SELECT SUM(TOTALLIQUIDO) AS SOMA FROM MOVIMENTO " + "WHERE (DATAEMISSAO BETWEEN '"
				+ tv.getDataInicial() + "' AND '" + tv.getDataFinal() + "') "
				+ "AND (CODIGOOPERACAO IN ('0201022','0201021','0210001','0210003',"
				+ "'0202060','0202005','0210004','0210005','0202030','0210110','0210111'," + "'0210112','0210113') "
				+ "AND STATUS<>'X') ";
		if (tv.getVendedor().getFilial() != null && tv.getVendedor().getFilial().equals("") == false) {
			sql += "AND CODIGOFILIALCONTABIL = '" + tv.getVendedor().getFilial().trim() + "'";
		}
		if (tv.getVendedor().getCodigo() != null && tv.getVendedor().getCodigo().equals("") == false) {
			sql += "AND CODIGOFUNCIONARIO = '" + tv.getVendedor().getCodigo().trim() + "'";
		}

		try (PreparedStatement stmt = c.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					BigDecimal totalVenda = rs.getBigDecimal("SOMA");
					totalVendas = new TotalDeVendas(totalVenda);
				}
			}
		}
		return totalVendas;
	}
}
