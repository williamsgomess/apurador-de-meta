package br.com.centrocar.apurador.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

import br.com.centrocar.apurador.controllers.RelatorioVendasController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * Classe com a responsábilidade de auxiliar a classe
 * {@link RelatorioVendasController}, implementando os métodos que serão usados
 * para manipulação de atibutos e refências.
 * 
 * @author Williams Gomes
 *
 * @see ControllersHelper
 * @see RelatorioVendasController
 */

public class RelatorioVendasHelper extends ControllersHelper {

	@FXML
	private Label lbMeta; // Valor da meta
	@FXML
	private Label lbSVF; // Valor das vendas sem valor fiscal
	@FXML
	private Label lbNF; // Valor das notas ficais eletrônicas
	@FXML
	private Label lbNFC; // Valor das notas fiscais de consumidor eletrônicas
	@FXML
	private Label lbDevolucoes; // Valor das vendas devolvidas e/ou estornadas
	@FXML
	private Label lbTotalDeVendas; // Total de todas as vendas efetivadas
	@FXML
	private PieChart pieVendas; // Gráfico das vendas efetivadas e devoluções
	@FXML
	private PieChart pieMeta; // Gráfico da meta

	/**
	 * Construtor sem parâmetros
	 */
	public RelatorioVendasHelper() {
	}

	/**
	 * Construtor que recebe uma lista de {@link Node} como parâmetro e atribue ao
	 * atributos locais.
	 * 
	 * @param nodes
	 * 
	 * @see Node
	 * @see List
	 * @see Label
	 * @see PieChart
	 */
	public RelatorioVendasHelper(List<Node> nodes) {
		lbDevolucoes = (Label) nodes.get(0); // Atribuindo valores da posição (0) da lista de Nodes ao atributo local.
		lbNF = (Label) nodes.get(1); // Atribuindo valores da posição (1) da lista de Nodes ao atributo local.
		lbNFC = (Label) nodes.get(2); // Atribuindo valores da posição (2) da lista de Nodes ao atributo local.
		lbSVF = (Label) nodes.get(3); // Atribuindo valores da posição (3) da lista de Nodes ao atributo local.
		lbTotalDeVendas = (Label) nodes.get(4); // Atribuindo valores da posição (4) da lista de Nodes ao atributo
												// local.
		lbMeta = (Label) nodes.get(5); // Atribuindo valores da posição (5) da lista de Nodes ao atributo local.
		pieVendas = (PieChart) nodes.get(6); // Atribuindo valores da posição (6) da lista de Nodes ao atributo local.
		pieMeta = (PieChart) nodes.get(7); // Atribuindo valores da posição (7) da lista de Nodes ao atributo local.
	}

	/**
	 * Método responsável por atribuir os valores da meta, vendas e devolução aos
	 * atributos locais, não retornando nada. O método formata os valores recebidos
	 * para {@link String} usando o auxilio da classe {@link NumberFormat} para
	 * formatar os valores aprensentados para a moeda local Brasileira.
	 * <p>
	 * Recebe como parâmetros : {@link NumberFormat}, {@link BigDecimal} para meta,
	 * devolução, nota fiscal, nota fiscal de consumidor, vendas sem valor fiscal e
	 * total das venda efetivadas.
	 * 
	 * @param formatado
	 * @param meta
	 * @param devolucao
	 * @param notaFiscal
	 * @param notaFiscalConsumidor
	 * @param semValorFiscal
	 * @param totalDeVendas
	 * 
	 * @see NumberFormat
	 * @see BigDecimal
	 */
	public void pegaValoresDasVendas(NumberFormat formatado, BigDecimal meta, BigDecimal devolucao,
			BigDecimal notaFiscal, BigDecimal notaFiscalConsumidor, BigDecimal semValorFiscal,
			BigDecimal totalDeVendas) {
		lbNFC.setText(formatado.format(notaFiscalConsumidor)); // Formata o valor recebido do tipo BigDecimal para a
																// valor da moeda local R$.
		lbMeta.setText(formatado.format(meta)); // Formata o valor recebido do tipo BigDecimal para a valor da moeda
												// local R$.
		lbDevolucoes.setText(formatado.format(devolucao)); // Formata o valor recebido do tipo BigDecimal para a valor
															// da moeda local R$.
		lbNF.setText(formatado.format(notaFiscal)); // Formata o valor recebido do tipo BigDecimal para a valor da moeda
													// local R$.
		lbSVF.setText(formatado.format(semValorFiscal)); // Formata o valor recebido do tipo BigDecimal para a valor da
															// moeda local R$.
		lbTotalDeVendas.setText(formatado.format(totalDeVendas)); // Formata o valor recebido do tipo BigDecimal para a
																	// valor da moeda local R$.
	}

	/**
	 * Método responsável por mostrar os valor da meta e do valor em falta no PieChart.
	 * <p>
	 * Cria o slice da meta e do valor que falta para a meta ser batida, parseando o valor para double e chamando o toString.
	 * Inicia o gráfico com o ângulo de 90º e faz o  cálculo das porcentagens da Meta e Falta.
	 *  
	 * @param valorMeta
	 * @param falta
	 * @param total
	 * 
	 * @see PieChart
	 * @see Double
	 * @see BigDecimal
	 * @see RoundingMode
	 */
	public void graficoMeta(BigDecimal valorMeta, BigDecimal falta, BigDecimal total) {

		PieChart.Data sliceMeta = new PieChart.Data("Meta", Double.parseDouble(total.toString())); // Cria um slice para
																									// representar o
																									// valor da meta.
		PieChart.Data sliceFalta = new PieChart.Data("Falta", Double.parseDouble(falta.toString())); // Cria um slice
																										// para
																										// representar o
																										// valor que
																										// falta para
																										// bater a meta.
		pieMeta.getData().add(sliceMeta); // Adiciona slice criado ao PieChart
		pieMeta.getData().add(sliceFalta); // Adiciona slice criado ao PieChart
		pieMeta.setStartAngle(90); // Inicia o PieChart com 90º de ângulo

		BigDecimal porcento = new BigDecimal(100); // Valor da porcentagem total
		BigDecimal divideMeta = total.multiply(porcento).divide(valorMeta, 2, RoundingMode.HALF_EVEN); // Enconta
																										// porcentagem
																										// da meta
		BigDecimal divideFalta = falta.multiply(porcento).divide(valorMeta, 2, RoundingMode.HALF_EVEN); // Enconta
																										// porcentagem
																										// do valor em
																										// falta.

		/**
		 * Mostra a porcentagem quando o mouse entra.
		 */
		pieMeta.setOnMouseEntered(event -> {
			sliceMeta.setName(String.valueOf(divideMeta) + "%");
			sliceFalta.setName(String.valueOf(divideFalta) + "%");
		});

		/**
		 * Mostra o tipo da venda quando o mouse sai.
		 */
		pieMeta.setOnMouseExited(e -> {
			sliceMeta.setName("Meta");
			sliceFalta.setName("Falta");
		});
	}

	public void graficoVenda(BigDecimal devolucoes, BigDecimal nfc, BigDecimal nf, BigDecimal svf, BigDecimal total)
			throws NumberFormatException {

		PieChart.Data sliceNFC = new PieChart.Data("NFC-e", Double.parseDouble(nfc.toString()));
		PieChart.Data sliceOutras = new PieChart.Data("Outras", Double.parseDouble(svf.toString()));
		PieChart.Data sliceNF = new PieChart.Data("NF-e", Double.parseDouble(nf.toString()));
		PieChart.Data sliceDevolucoes = new PieChart.Data("Devoluções", Double.parseDouble(devolucoes.toString()));

		pieVendas.getData().add(sliceNFC);
		pieVendas.getData().add(sliceNF);
		pieVendas.getData().add(sliceDevolucoes);
		pieVendas.getData().add(sliceOutras);
		pieVendas.setStartAngle(180);

		try {
			BigDecimal porcento = new BigDecimal(100);
			BigDecimal divideNFC = nfc.multiply(porcento).divide(total, 2, RoundingMode.HALF_EVEN);
			BigDecimal divideDevolucoes = devolucoes.multiply(porcento).divide(total, 2, RoundingMode.HALF_EVEN);
			BigDecimal divideSVF = svf.multiply(porcento).divide(total, 2, RoundingMode.HALF_EVEN);
			BigDecimal divideNF = nf.multiply(porcento).divide(total, 2, RoundingMode.HALF_EVEN);

			pieVendas.setOnMouseEntered(event -> {
				if (divideNFC != BigDecimal.ZERO) {
					sliceNFC.setName(String.valueOf(divideNFC) + "%");
				} else {
					sliceNFC.setName("0%");
				}

				if (divideDevolucoes != BigDecimal.ZERO) {
					sliceDevolucoes.setName(String.valueOf(divideDevolucoes) + "%");
				} else {
					sliceDevolucoes.setName("0%");
				}

				if (divideSVF != BigDecimal.ZERO) {
					sliceOutras.setName(String.valueOf(divideSVF) + "%");
				} else {
					sliceOutras.setName("0%");
				}

				if (divideNF != BigDecimal.ZERO) {
					sliceNF.setName(String.valueOf(divideNF) + "%");
				} else {
					sliceNF.setName("0%");
				}
			});
			pieVendas.setOnMouseExited(e -> {
				sliceNFC.setName("NFC-e");
				sliceOutras.setName("Outras");
				sliceNF.setName("NF-e");
				sliceDevolucoes.setName("Devoluções");
			});
		} catch (Exception e) {
			System.out.println("passa");
		}
	}
}
