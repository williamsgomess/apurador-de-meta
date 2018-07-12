package br.com.centrocar.apurador.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface de Gerenciamento de Conexão ao Banco de Dados
 * 
 * @author Williams Gomes
 * 
 * @version 1.0.0
 */
public interface GerenciadorDeConexao {
	
	/**
	 * Escopo do método que abre uma conexão.
	 * @return Retorna um Connection
	 * @throws SQLException Lança uma {@link SQLException}
	 */
	Connection openConnection() throws SQLException;
	
	/**
	 * Escopo do método que fecha uma conexão, se a mesma estiver aberta.
	 * Recebe como parâmetro um {@link Connection}
	 * @param conn
	 * @throws Lança uma {@link SQLException}
	 */
    void close(Connection conn) throws SQLException;
}
