package br.com.centrocar.apurador.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import br.com.centrocar.apurador.helper.LoginHelper;
import br.com.centrocar.apurador.modelo.Devolucao;
import br.com.centrocar.apurador.modelo.Meta;
import br.com.centrocar.apurador.modelo.NotaFiscal;
import br.com.centrocar.apurador.modelo.NotaFiscalConsumidor;
import br.com.centrocar.apurador.modelo.SemValorFiscal;
import br.com.centrocar.apurador.modelo.TotalDeVendas;
import br.com.centrocar.apurador.modelo.Vendedor;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * 
 * @author Williams Gomes
 * @version 1.1.1
 */
public class LoginController implements Initializable {

	@FXML
	private JFXTextField txUsuario; // Campo para digitar o login
	@FXML
	private JFXButton btEntrar; // Botão entrar
	@FXML
	private JFXButton btCancelar; // Botão cancelar
	@FXML
	public DatePicker dtInicial; // Data inicial
	@FXML
	public DatePicker dtFinal; // Data final
	@FXML
	private AnchorPane pane; // Painel principal da janela
	@FXML
	private JFXCheckBox cbLogado; // CheckBox pra saber se o usuário está logado

	private String usuarioLogado; // String para armazenar o usuário logado
	private final LoginHelper helper = new LoginHelper();

	/**
	 * Método responsável por inicializar a janela de login da aplicação
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		resources = ResourceBundle.getBundle("br.com.centrocar.apurador.util.db"); // Busca o arquivo .properties

		try {
			this.txUsuario.setText(mostraArquivo()); // Pega o usuário através de um arquivo .txt e seta no txtUsuario
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/**
		 * Abre o arquivo onde está localizado o usuário do aplicativo clicando em F10.
		 * E em seguida finaliza a aplicação.
		 */
		pane.setOnKeyPressed((event) -> {
			final KeyCombination kb = new KeyCodeCombination(KeyCode.F10, KeyCombination.CONTROL_DOWN);
			if (kb.match(event)) {
				try {
					Runtime.getRuntime().exec("notepad C://apurador_de_metas/usuarios.txt"); // Abre o arquivo deste
																								// caminho.
					System.exit(0); // Fecha a aplicação.
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		dtInicial.setValue(LocalDate.now()); // Pega a Data atual do sistema.
		dtFinal.setValue(LocalDate.now()); // Pega a data atual do sistema.

		/**
		 * Executa os comando nescessários e abre a tela de Relatórios de Vendas.
		 */
		btEntrar.setOnAction(event -> {
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					updateMessage("Aguarde..."); // Altera o nome do botão para aguarde.
					btEntrar.setDisable(true); // Desabilita o botão entrar.
					btCancelar.setDisable(true); // Desabilita o botão cancelar.
					dtInicial.setDisable(true); // Desabilita o DatePicker de data inicial.
					dtFinal.setDisable(true); // Desabilita o datepicker de data final.
					txUsuario.setDisable(true); // Desabilita o textfield de login do vendedor(usuário).
					try {
						Thread.sleep(3000); // Quantidade de tempo pra fazer reload na app.
					} catch (Exception e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							LocalDate dataInicial = dtInicial.getValue();
							LocalDate dataFinal = dtFinal.getValue();
							try {
								// Recupera um vendedor do banco
								Vendedor v = helper.pegaVendedor(txUsuario.getText());
								// Recupera o total de vendas como nota fiscal de consumidor
								NotaFiscalConsumidor nfc = helper.pegaNotaFiscalConsumidor(v, dataInicial, dataFinal);
								// Recupera o total de vendas como nota fiscal
								NotaFiscal nf = helper.pegaNotaFiscal(v, dataInicial, dataFinal);
								// Recupera o total de devoluções de um vendedor
								Devolucao dev = helper.pegaDevolucao(v, dataInicial, dataFinal);
								// Recupera o total de vendas sem valor fiscal
								SemValorFiscal svfValor = helper.pegaSemValorFiscal(v, dataInicial, dataFinal);
								// Recupera o total de todas as vendas efetivadas
								TotalDeVendas totalVendas = helper.pegaTotalDeVendas(v, dataInicial, dataFinal);
								// Recupera a meta de um determinado vendedor
								Meta metaDois = helper.pegaMeta(v, dtInicial);

								if (txUsuario.getText().equalsIgnoreCase(v.getDescricao())
										&& dtInicial.getValue() != null) {

									helper.criaTela(event, v, nfc, nf, dev, svfValor, totalVendas, metaDois, dataInicial,
											dataFinal);
								}
							} catch (Exception ex) {

								ex.printStackTrace();
								JOptionPane.showMessageDialog(null,
										"Nenhum resultado retornado! \nVerifique a se a data está correta, \nou se nesse mês "
												+ "existe meta cadastrada!");
								updateMessage("Entrar"); // Altera nome do botão para Entrar.
								btEntrar.setDisable(false); // Habilitando o botão entrar.
								btCancelar.setDisable(false); // Habilitando o botão cancelar.
								dtInicial.setDisable(false); // Habilitando o DatePicker de data inicial.
								dtFinal.setDisable(false); // Habilitando o datepicker de data final.
							}
						}
					});
					return null;
				}
			};
			btEntrar.textProperty().bind(task.messageProperty());
			new Thread(task).start();
		});

		btCancelar.setOnAction(event -> {
			System.exit(0);
		});
	}

	/**
	 * Busca o arquivo .txt que contém o login do usuário que irá utilizar a
	 * aplicação.
	 * <p>
	 * Utiliza a classe {@link Scanner} junto com a classe {@link File} para buscar
	 * o arquivo no diretório preparado pela aplicação,
	 * {@code c:\\apurador_de_metas\\usuarios.txt},
	 * 
	 * @return Retorna uma {@link String}
	 * @throws FileNotFoundException
	 */
	private String mostraArquivo() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("C:\\apurador_de_metas\\usuarios.txt"));
		while (sc.hasNextLine()) {
			usuarioLogado = sc.nextLine();
		}
		sc.close();
		return usuarioLogado;
	}
}
