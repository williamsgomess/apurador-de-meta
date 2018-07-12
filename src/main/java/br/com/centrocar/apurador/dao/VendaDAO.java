package br.com.centrocar.apurador.dao;

import java.sql.SQLException;

import br.com.centrocar.apurador.modelo.Venda;

/**
 * Interface com contrato de implementação do método seleciona, <br>
 * onde irá se comunicar ao banco de dados e retornar o resultado de {@link Venda}.
 * 
 * @author Williams Gomes
 * 
 * @version 1.1.0
 */
public interface VendaDAO {
	
	/**
	 * Escopo do método que dever ser implementado por toda classe que assinar contrato com a interface {@link VendasDAO}.<br>
	 * Recebe uma {@link Venda} como parâmetro.
	 * 
	 * @param v
	 * @return Uma {@link Venda}
	 * @throws Lança uma {@link SQLException}.
	 */
	Venda seleciona(Venda v) throws SQLException;
}
