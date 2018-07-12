package br.com.centrocar.apurador.modelo;

import java.math.BigDecimal;

/**
 * Classe filha da super classe {@link Venda} responsável pelas vendas
 * realizadas através de NF-e (Nota Fiscal Eletrônica).
 * 
 * @author Williams Gomes
 * @version 1.1.0
 * 
 * @see Venda
 */
public class NotaFiscal extends Venda {

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
	public NotaFiscal() {
		super();
	}

	/**
	 * Construtor que recebe um {@link BigDecimal} como parâmentro. Recebe o super
	 * do construtor da super classe {@link Venda}.
	 * 
	 * @param total
	 */
	public NotaFiscal(BigDecimal total) {
		super(total);
		if (this.total == BigDecimal.ZERO || this.total == null) {
			this.total = BigDecimal.ZERO;
		} else {
			this.total = total;
		}
	}
}
