package br.com.centrocar.apurador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.centrocar.apurador.modelo.Vendedor;

/**
 * Classe que gerencia as consultas ao banco de dados de um {@link Vendedor}.
 * 
 * @author Williams Barriquero
 * @version 1.1.0
 */
public class VendedorDAO {

	private final GerenciadorDeConexao g;		//Atributo que representa uma instância de um Gerenciador de Conexão.

	/**
	 * Construtor que referencia um {@link GerenciadorDeConexao} recebendo um {@code GerenciadorDeConexaoImpl.getGerenciador}.
	 */
	public VendedorDAO() {
        this.g = GerenciadorDeConexaoImpl.getGerenciador();
    }

	/**
	 * Abre um conexão e consulta o banco de dados devolvendo os dados de um {@link Vendedor}.
	 * <p>
	 * Nessa consulta, é recebido como parâmetro a descricão (login) do vendedor e verifica e o mesmo existe na base.
	 * <p>
	 * Recebe um {@link Vendedor} como parâmetro.
	 * 
	 * @param v
	 * @return Retorna um {@link Vendedor}.
	 * @throws Lança uma Exception
	 */
	public Vendedor seleciona(Vendedor v) throws Exception {

		Connection conn = g.openConnection();	//Abre uma conexão.
		Vendedor vendedor = null; 				//Cria uma referência a um vendedor.
		
		String sql = "SELECT C.DESCRICAO AS C_DESCRICAO, C.CODIGOUSUARIO, C.CODIGOFILIALCONTABIL, F.DESCRICAO AS F_DESCRICAO, F.CODIGOFUNCIONARIO AS F_CODIGO "
				+ "FROM CADUSUARIO C INNER JOIN CADFUNCIONARIO F ON C.CODIGOFUNCIONARIO = F.CODIGOFUNCIONARIO "
				+ "WHERE CODIGOUSUARIO IS NOT NULL ";
		if (v.getDescricao() != null && v.getDescricao().equals("") == false) {
			sql += "AND C.DESCRICAO = '" + v.getDescricao().trim() + "'";
		}

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String codigo = rs.getString("F_CODIGO");						//Atribui o código
					String filial = rs.getString("CODIGOFILIALCONTABIL");			//Atribui a filial
					String descricao = rs.getString("C_DESCRICAO");				//Atribui a descrição (login)
					String nome = rs.getString("F_DESCRICAO");						//Atribui o nome
					vendedor = new Vendedor(nome, codigo, filial, descricao);		//Cria uma instância de um Vendedor e passa os parâmetros
				}
			}
		}
		return vendedor;		//Retorna um vendedor.
	}
}
