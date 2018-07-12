package br.com.centrocar.apurador.modelo;

/**
 * Classe que modela os dados de um vendedor
 * 
 * @author Williams Gomes
 * @version 1.0.0
 * 
 * @see Object
 */
public class Vendedor {

	private String nome; // Nome do vendedor.
	private String codigo; // Código do vendedor (código do funcionário).
	private String filial; // Filial do vendedor.
	private String descricao; // Descrição é o 'LOGIN' do vendedor.

	/**
	 * Construtor que inicia uma instância sem parâmetros.
	 */
	public Vendedor() {
	}

	/**
	 * Construtor que recebe os parâmetros nome, codigo, filial e descricao de um
	 * {@link Vendedor}
	 * 
	 * @param nome      referente ao nome do vendedor.
	 * @param codigo    referente ao código do vendedor.
	 * @param filial    referente a filial cadastrada para o vendedor.
	 * @param descricao referente ao login do vendedor.
	 */
	public Vendedor(String nome, String codigo, String filial, String descricao) {
		this.nome = nome;
		this.codigo = codigo;
		this.filial = filial;
		this.descricao = descricao;
	}

	/**
	 * @return Uma {@link String} referente ao nome do vendedor.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome do vendedor
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return Uma {@link String} referente ao codigo do vendedor.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param codigo do vendedor
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Uma {@link String} referente a filial do vendedor.
	 */
	public String getFilial() {
		return filial;
	}

	/**
	 * 
	 * @param filial do vendedor
	 */
	public void setFilial(String filial) {
		this.filial = filial;
	}

	/**
	 * 
	 * @return Uma {@link String} referente a descrição (login) do vendedor.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * 
	 * @param descricao (login) do vendedor
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Sobrescrita do método {@code hashCode()}.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Sobrescrita do método {@code equals()}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Sobrescrita do método {@code toString()}.
	 */
	@Override
	public String toString() {
		return "Vendedor [nome=" + nome + ", codigo=" + codigo + ", filial=" + filial + ", descricao=" + descricao
				+ "]";
	}
}