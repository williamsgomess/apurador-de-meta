package br.com.centrocar.apurador.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.Venda;

/**
 * Classe responsável por gerenciar consultas de {@link NotaFiscalConsumidor} ao banco de dados,
 * SELECT, UPDATE, INSERT, DELETE. <br>
 * Implementa o método {@code seleciona} da interface {@link VendasDAO}.
 * 
 * @author Williams Gomes
 * 
 * @version 1.1.0
 *
 */

public class NotaFiscalConsumidorDAO implements VendaDAO {

	/**
	 * Instância de um {@link GerenciadorDeConexao}.
	 */
	private final GerenciadorDeConexao g;

	/**
	 * Construtor da classe sem parâmetros que inicializa a instância do {@link GerenciadorDeConexao} <br>
	 * recebendo como valor o retorno do método {@code GerenciadorDeConexaoImpl.getGerenciador()} da classe {@link GerenciadorDeConexaoImpl}.
	 */
    public NotaFiscalConsumidorDAO() {
        this.g = GerenciadorDeConexaoImpl.getGerenciador();
    }
    
    /**
	 * Método recebe como parâmetro uma instância do tipo {@link Venda} e retorna uma {@link NotaFiscalConsumidor}.
	 * Abre uma conexão usando as classe {@link Connection} que em sua instância recebe o método <code>openConnection()</code>
	 * da classe {@link GerenciadorDeConexaoImpl}, depois faz um SELECT no banco de dados retornando as soma das Notas Fiscais de Consumidor de um vendedor.
	 * 
	 * @throws SQLException <br> Lança uma {@link SQLException}.
	 * @param v <br> Recebe uma instância do tipo {@link Venda}.
	 * @return {@link NotaFiscalConsumidor}.
	 */
    @Override
    public Venda seleciona(Venda v) throws SQLException {
        
    	NotaFiscalConsumidor nfc = (NotaFiscalConsumidor) v;		//Cria uma referência para a classe TotalDeVendas e atribue a Venda a ela usando casting.
        Connection c = g.openConnection();
        NotaFiscalConsumidor vendasNFC = null;
        String sql = "SELECT SUM(TOTALLIQUIDO) AS SOMA FROM MOVIMENTO "
                + "WHERE (DATAEMISSAO BETWEEN '"+ nfc.getDataInicial() +"' AND '"+ nfc.getDataFinal() +"') "
                + "AND (CODIGOOPERACAO IN ('0210001','0210003','0210004','0210005','0202062',"
                + "'0210110','0210111','0210112','0210113') "
                + "AND STATUS<>'X') ";
        if (nfc.getVendedor().getFilial() != null && nfc.getVendedor().getFilial().equals("") == false) {
            sql += "AND CODIGOFILIALCONTABIL = '" + nfc.getVendedor().getFilial().trim() + "'";
	}
        if (nfc.getVendedor().getCodigo()!= null && nfc.getVendedor().getCodigo().equals("") == false) {
            sql += "AND CODIGOFUNCIONARIO = '" + nfc.getVendedor().getCodigo() + "'";
	}
        
        try(PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.execute();
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    BigDecimal total = rs.getBigDecimal("SOMA"); 
                    vendasNFC = new NotaFiscalConsumidor(total);                    
                }
            }
        }
        return vendasNFC;
    }
}
