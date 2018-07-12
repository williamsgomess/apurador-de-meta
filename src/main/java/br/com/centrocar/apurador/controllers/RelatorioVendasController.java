package br.com.centrocar.apurador.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import br.com.centrocar.apurador.helper.RelatorioVendasHelper;
import br.com.centrocar.apurador.main.Main;
import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.Meta;
import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Vendedor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * 
 * @author Williams Gomes
 *
 * @version 1.1.1
 * @param <T>
 */
public class RelatorioVendasController implements Initializable {

	@FXML
	private Label lbMeta;
	@FXML
	private Label lbSVF;
	@FXML
	private Label lbNF;
	@FXML
	private Label lbNFC;
	@FXML
	private Label lbDevolucoes;
	@FXML
	private Label lbTotalDeVendas;
	@FXML
	private Label lbFuncionario;
	@FXML
	private Label lbTotalGeral;
	@FXML
	private JFXButton btVoltar;
	@FXML
	private JFXButton btFechar;
	@FXML
    private JFXButton btMinimizar;
	@FXML
	private PieChart pieVendas;
	@FXML
	private PieChart pieMeta;
	@FXML
	private Label lbValorEmFalta;
	@FXML
	private Label lbCodigo;
	@FXML
	public DatePicker dtInicial;
	@FXML
	public DatePicker dtFinal;

	private String login;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btVoltar.setOnAction((ActionEvent event) -> {
			new Main().abreTela(event, "Login.fxml");
		});

		btMinimizar.setOnAction(event -> {
			Stage stage = (Stage) btMinimizar.getScene().getWindow();
		    stage.setIconified(true);
		});
		
		btFechar.setOnAction(event -> {
			System.exit(0);
		});
	}

	private void bateuMeta(Vendedor vendedor1, BigDecimal valorMeta, BigDecimal totalGeral) {
		String[] nomeSeparado = vendedor1.getNome().split(" ");
		String first = nomeSeparado[0];
		String two = nomeSeparado[1];
		if (totalGeral.compareTo(valorMeta) >= 0) {
			JOptionPane.showConfirmDialog(null,
					"Parabéns " + first.toUpperCase() + " " + two.toUpperCase() + "! Você bateu a meta!",
					"Congratulations", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			lbTotalGeral.setTextFill(Color.GREEN);
		}
	}

	private List<Node> listaDeNodes() {
		List<Node> nodes = new ArrayList<>();
		nodes.add(lbDevolucoes);
		nodes.add(lbNF);
		nodes.add(lbNFC);
		nodes.add(lbSVF);
		nodes.add(lbTotalDeVendas);
		nodes.add(lbMeta);
		nodes.add(pieVendas);
		nodes.add(pieMeta);
		return nodes;
	}

	private void valores(NumberFormat nbf, NotaFiscalConsumidor nfc, NotaFiscal nf, Devolucao dev,
			SemValorFiscal svfValor, TotalDeVendas totalVendas) {
		BigDecimal vNFC = nfc.getTotal();
		BigDecimal vNota = nf.getTotal();
		BigDecimal vDevolucoes = dev.getTotal();
		BigDecimal vSVF = svfValor.getTotal();
		BigDecimal vTotal = totalVendas.getTotal();

		lbNFC.setText(nbf.format(vNFC));
		lbDevolucoes.setText(nbf.format(vDevolucoes));
		lbNF.setText(nbf.format(vNota));
		lbSVF.setText(nbf.format(vSVF));
		lbTotalDeVendas.setText(nbf.format(vTotal));
		BigDecimal totalGeral1 = vTotal.subtract(vDevolucoes);
		lbTotalGeral.setText(nbf.format(totalGeral1));
	}

	public void pegaValoresDasVendas(NumberFormat nf, BigDecimal meta, BigDecimal devolucao, BigDecimal notaFiscal,
			BigDecimal notaFiscalConsumidor, BigDecimal semValorFiscal, BigDecimal totalDeVendas) {
		lbNFC.setText(nf.format(notaFiscalConsumidor));
		lbMeta.setText(nf.format(meta));
		lbDevolucoes.setText(nf.format(devolucao));
		lbNF.setText(nf.format(notaFiscal));
		lbSVF.setText(nf.format(semValorFiscal));
		lbTotalDeVendas.setText(nf.format(totalDeVendas));
	}
	
	public void setLabelText(Vendedor logado, Meta meta, Devolucao devolucao, NotaFiscal notaFiscal, NotaFiscalConsumidor notaFiscalConsumidor, 
			SemValorFiscal semValorFiscal, TotalDeVendas totalDeVendas, LocalDate dataInicial, LocalDate dataFinal) {

		this.login = logado.getDescricao();
		this.dtInicial.setValue(dataInicial);
		this.dtFinal.setValue(dataFinal);

		lbFuncionario.setText(logado.getNome());
		lbCodigo.setText(logado.getCodigo());
		
		NumberFormat nbf = NumberFormat.getCurrencyInstance();
		
		BigDecimal valorMeta = meta.getValorMeta();
		BigDecimal valorDevolucao = devolucao.getTotal();
		BigDecimal valorNotaFiscal = notaFiscal.getTotal();
		BigDecimal valorNotaFiscalConsumidor = notaFiscalConsumidor.getTotal();
		BigDecimal valorSemValorFiscal = semValorFiscal.getTotal();
		BigDecimal valorTotalDeVendas = totalDeVendas.getTotal();

		List<Node> nodes = listaDeNodes();
		RelatorioVendasHelper helper = new RelatorioVendasHelper(nodes);
		
		helper.pegaValoresDasVendas(nbf, valorMeta, valorDevolucao, valorNotaFiscal, valorNotaFiscalConsumidor,
				valorSemValorFiscal, valorTotalDeVendas);

		BigDecimal totalGeral = valorTotalDeVendas.subtract(valorDevolucao);
		lbTotalGeral.setText(nbf.format(totalGeral));

		bateuMeta(logado, valorMeta, totalGeral);

		BigDecimal falta = valorMeta.subtract(totalGeral);
		BigDecimal zero = BigDecimal.ZERO;
		try {
			if (falta.doubleValue() > 0) {
				lbValorEmFalta.setText(nbf.format(falta));
				helper.graficoMeta(valorMeta, falta, totalGeral);

			} else {
				lbValorEmFalta.setText(nbf.format(zero));
				lbMeta.setTextFill(Color.GREEN);
				lbValorEmFalta.setTextFill(Color.BLACK);
				helper.graficoMeta(valorMeta, zero, totalGeral);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		helper.graficoVenda(valorDevolucao, valorNotaFiscalConsumidor, valorNotaFiscal, valorSemValorFiscal, totalGeral);

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(180), ev -> {
			//final RelatorioVendasHelper help = new RelatorioVendasHelper();
			try {
				Vendedor v = helper.pegaVendedor(login);								//Recupera um vendedor do banco
                NotaFiscalConsumidor nfc = helper.pegaNotaFiscalConsumidor(v, dataInicial, dataFinal);				//Recupera o total de vendas como nota fiscal de consumidor
                NotaFiscal nf = helper.pegaNotaFiscal(v, dataInicial, dataFinal);							//Recupera o total de vendas como nota fiscal
                Devolucao dev = helper.pegaDevolucao(v, dataInicial, dataFinal);				//Recupera o total de devoluções de um vendedor
                SemValorFiscal svfValor = helper.pegaSemValorFiscal(v, dataInicial, dataFinal);					//Recupera o total de vendas sem valor fiscal
                TotalDeVendas totalVendas = helper.pegaTotalDeVendas(v, dataInicial, dataFinal);	//Recupera o total de todas as vendas efetivadas

				valores(nbf, nfc, nf, dev, svfValor, totalVendas);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
