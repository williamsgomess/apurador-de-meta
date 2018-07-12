package br.com.centrocar.apurador.modelo;

import java.math.BigDecimal;

/**
 * Classe modelo que armazena os dados das metas de cada {@link Vendedor}.
 * 
 * @author Williams Gomes
 * @version 1.1.0
 */
public class Meta {

	private String dataMeta; // Data de cadastro da meta atual do vendedor.
	private String codigo; // Código da meta.
	private Vendedor vendedor; // Vendedor vinculado a meta.
	private BigDecimal valorMeta; // Valor da meta mensal de um vendedor.

	/**
	 * Construtor que cria uma instância sem parâmetros de uma meta e inicia uma
	 * instância de um vendedor.
	 */
	public Meta() {
		this.vendedor = new Vendedor();
	}

	/**
	 * Construtor que recebe um {@link BigDecimal} como parâmentro. Recebe o super
	 * do construtor da super classe {@link Venda}.
	 * <p>
	 * Verifica se o {@code total} é igual a zero (0) através de uma constante da
	 * classe {@link BigDecimal} ou null, se a cerificação for {@code true} ele
	 * atribui zero ao total através usando a contante {@link BigDecimal#ZERO}, se o
	 * resultado da verificação for false, ele atribui o valor passado pelo cliente
	 * ou consulta ao banco de dados.
	 * 
	 * @param total referente ao valor somado de todos os tipos de venda.
	 */
	public Meta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
		this.vendedor = new Vendedor();
	}

	/**
	 * @return Retorna uma {@link String} referente a data de cadastro da meta.
	 */
	public String getDataMeta() {
		return dataMeta;
	}

	/**
	 * Recebe uma {@link String} como parâmetro.
	 * 
	 * @param dataMeta
	 */
	public void setDataMeta(String dataMeta) {
		this.dataMeta = dataMeta;
	}

	/**
	 * @return Retorna uma {@link String} referente ao código da meta.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Recebe uma {@link String} como parâmetro.
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna um {@link Vendedor} vinculado a meta.
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * recebe um {@link Vendedor} como parâmetro.
	 * 
	 * @param vendedor
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * @return Retorna uma {@link BigDecimal} referente ao valor da meta atual.
	 */
	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	/**
	 * Recebe um {@link BigDecimal} como parâmetro.
	 * 
	 * @param valorMeta
	 */
	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}

	@Override
	public String toString() {
		return "Meta [dataMeta=" + dataMeta + ", codigo=" + codigo + ", vendedor=" + vendedor + ", valorMeta="
				+ valorMeta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataMeta == null) ? 0 : dataMeta.hashCode());
		result = prime * result + ((valorMeta == null) ? 0 : valorMeta.hashCode());
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meta other = (Meta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataMeta == null) {
			if (other.dataMeta != null)
				return false;
		} else if (!dataMeta.equals(other.dataMeta))
			return false;
		if (valorMeta == null) {
			if (other.valorMeta != null)
				return false;
		} else if (!valorMeta.equals(other.valorMeta))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}

}
