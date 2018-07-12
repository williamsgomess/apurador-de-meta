package br.com.centrocar.apurador.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Implementação da classe que gerencia conexões feitas ao banco de dados.
 * Contrato assinado com a Interface {@link GerenciadorDeConexao}.
 * 
 * @author Williams Gomes
 * @version 1.1.0
 */

public class GerenciadorDeConexaoImpl implements GerenciadorDeConexao{

	private static GerenciadorDeConexaoImpl gerenciador;	//Atributo que referencia uma GerenciadorDeConexaoImpl.
    private final String url;								//Atributo que recebe a url do banco de dados.
    private final String user;								//Atributo que recebe o usuário do banco.
    private final String password;							//Atributo que recebe a senha do usuário do banco de dados.
	
    /**
     * Construtor que usa o {@link ResourceBundle} para acessar um arquico .properties, 
     * que contém a url, o usuário e a senha do banco de dados. 
     */
    private GerenciadorDeConexaoImpl() {
        ResourceBundle rb = ResourceBundle.getBundle("br.com.centrocar.apurador.util.db");   //Pegando o arquivo .properties.
        url = rb.getString("url");															 //Pegando url do banco a partir do arquivo.
        user = rb.getString("usuario");														 //Pegando o usuario a partir do arquivo.
        password = rb.getString("senha");													 //Pegando a senha a partir do arquivo.
    }
    
    /**
     * Método que verifica se existe uma instância null de um {@link GerenciadorDeConexaoImpl} 
     * e retornar um {@link GerenciadorDeConexaoImpl}
     * caso seja essa verificação seja falsa.
     * 
     * @return Retorna um {@link GerenciadorDeConexaoImpl}
     */
    public static GerenciadorDeConexaoImpl getGerenciador() {
        if ( gerenciador == null ) {
            gerenciador = new GerenciadorDeConexaoImpl();
        }
        return gerenciador;
    }
    
    /**
     * Sobrescrita do método openConnection da interface {@link GerenciadorDeConexao}, 
     * responsável por abrir uma conexão com o banco de dados.
     * Abre a conexão usando a url, o usuario e senha do proprietário da base de dados.
     * 
     * @throws Lança uma {@link SQLException}
     */
	@Override
	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Sobrescrita do método close da interface {@link GerenciadorDeConexao}, 
	 * reponsável por fechar uma conexão aberta.
	 * 
	 * @throws Lança uma {@link SQLException}
	 */
	@Override
	public void close(Connection conn) throws SQLException {
		conn.close();
	}

}
