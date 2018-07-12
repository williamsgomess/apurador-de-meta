package br.com.centrocar.apurador.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.centrocar.apurador.controllers.RelatorioVendasController;
import br.com.centrocar.apurador.dao.MetaDAO;
import br.com.centrocar.apurador.main.Main;
import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.Meta;
import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;

public class LoginHelper extends ControllersHelper {
	
	private final Meta meta = new Meta();

	/**
     * Pega o resultado da consulta de uma {@link Meta} no banco de dados e devolve o retorno para umas referência da classe {@link Meta}.
     * recebe como parâmetro uma {@link Meta}.
     * 
     * @param meta
     * @return Retorna uma {@link Meta}.
     * @throws Lança uma Exception
     */
	public Meta pegaMeta(Vendedor vendedor, DatePicker di) throws Exception {
		
		MetaDAO daoMeta = new MetaDAO();
        LocalDate dataMeta = di.getValue();
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("yyyyMM");
        String formatador = myDateFormatter.format(dataMeta);
        this.meta.setDataMeta(formatador);
        this.meta.setVendedor(vendedor);
        this.meta.getVendedor().setCodigo(vendedor.getCodigo());
        
        Meta metaDois = daoMeta.seleciona(meta);
        return metaDois;
	}
	
	public void criaTela(ActionEvent event, Vendedor v, NotaFiscalConsumidor nfc, NotaFiscal nf, Devolucao dev,
			SemValorFiscal svfValor, TotalDeVendas totalVendas, Meta metaDois, LocalDate dataInicial,
			LocalDate dataFinal) throws Exception {

		FXMLLoader loader = new Main().telaRelatorio("RelatorioVendas.fxml", event);
		RelatorioVendasController controller = loader.getController();
		controller.setLabelText(v, metaDois, dev, nf, nfc, svfValor, totalVendas, dataInicial, dataFinal);
		
	}
}
