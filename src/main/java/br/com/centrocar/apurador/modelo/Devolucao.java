package br.com.centrocar.apurador.modelo;

import java.math.BigDecimal;

/**
 * Classe filha da super classe {@link Venda} Responsável por todas as
 * devoluções de vendas não efetivadas com sucesso pelo vendedor.
 * 
 * @author Williams Gomes
 * @version 1.1.0
 * 
 * @see Venda
 */
public class Devolucao extends Venda {

	/**
	 * Construtor sem parâmetros com o super do construtor da super classe.
	 */
	public Devolucao() {
		super();
	}

	/**
	 * Construtor que recebe um {@link BigDecimal} como parâmentro. Recebe o super
	 * do construtor da super classe {@link Venda}.
	 * 
	 * @param total
	 */
	public Devolucao(BigDecimal total) {
		super(total);
		if (this.total == BigDecimal.ZERO || this.total == null) {
			this.total = BigDecimal.ZERO;
		} else {
			this.total = total;
		}
	}
}
