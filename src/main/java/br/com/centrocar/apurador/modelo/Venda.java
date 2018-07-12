package br.com.centrocar.apurador.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Responsável por modelar os dados do tipo {@link Venda}, como o valor (somado)
 * de um tipo de venda, seja ela uma venda sem valor fiscal, uma venda atravéis
 * de nota fiscal, uma venda feita com nfc (Substituto do cupom fiscal) até
 * mesmo as devoluções de vendas de um determinado vendedor. Serve como escopo
 * para suas classe filhas, onde os métodos, atributos e implementações são
 * comuns.
 * 
 * @see Devolucao
 * @see NotaFiscal
 * @see NotaFiscalConsumidor
 * @see SemValorFiscal
 * @see TotalDeVendas
 * 
 * @author Williams Gomes
 * @version 1.1.0
 */
public abstract class Venda {

	protected BigDecimal total; // Valor total do tipo da venda. Sua implementação varia de acardo com a
								// subclasse.
	private LocalDate dataInicial; // Data inicial, usada na consulta das vendas. Sua implementação varia de acardo
									// com a subclasse.
	private LocalDate dataFinal; // Data final, usada na consulta das vendas. Sua implementação varia de acardo
									// com a subclasse.
	protected Vendedor vendedor; // Referência o vendedor que esteja atrelado a venda.

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public Venda() {
	}

	/**
	 * Construtor que parâmetrizado, que recebe o valor somado do tipo de venda.
	 * 
	 * @param total somado de um tipo de venda.
	 * @see Devolucao
	 * @see NotaFiscal
	 * @see NotaFiscalConsumidor
	 * @see SemValorFiscal
	 * @see TotalDeVendas
	 */
	public Venda(BigDecimal total) {
		this.total = total;
		this.vendedor = new Vendedor();
	}

	/**
	 * 
	 * @return um {@link BigDecimal} referente ao valor total da venda.
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total somado do tipo da venda.
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * 
	 * @return Um {@link LocalDate}.
	 */
	public LocalDate getDataInicial() {
		return dataInicial;
	}

	/**
	 * 
	 * @param dataInicial data para iniciar a pesquisa
	 */
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * 
	 * @return Um {@link LocalDate}.
	 */
	public LocalDate getDataFinal() {
		return dataFinal;
	}

	/**
	 * 
	 * @param dataFinal data final para pesquisar
	 */
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return um {@link Vendedor}.
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * 
	 * @param vendedor vinculado a venda
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "Venda [total=" + total + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", vendedor="
				+ vendedor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		Venda other = (Venda) obj;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}
}
