package br.com.centrocar.apurador.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Classe responsável por inicar a aplicação.
 * 
 * @author Williams Gomes
 * @version 1.0.0
 * 
 * @see Application
 */
public class Main extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		Main.stage = stage;
		login();
	}
	
	/**
	 * Método que cria a cena de Login do usuário através de um arquivo .fxml usando o {@link FXMLLoader}.
	 * Cria a cena que interagirá com o usúario através da classe {@link Scene}, e a inicia <code>UNDECORATED</code> 
	 * através de um {@link Enum} da classe {@code StageStyle#UNDECORATED}.
	 * 
	 * @throws Lança uma {@link IOException}
	 */	
	private void login() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("../util/Login.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			stage.centerOnScreen();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * @return Retorna um {@link Stage}
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage
	 */
	public void setStage(Stage stage) {
		Main.stage = stage;
	}

	/**
	 * Método responsável para rodar a aplicação.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	public FXMLLoader telaRelatorio(String local, ActionEvent event) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(local));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage1 = new Stage();
		stage1.initStyle(StageStyle.UNDECORATED);
		stage1.setScene(scene);
		stage1.show();
		((Node) event.getSource()).getScene().getWindow().hide();
		
		return loader;
	}
	
	public void abreTela(ActionEvent event, String local) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(local));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();

			((Node) event.getSource()).getScene().getWindow().hide();
		} catch (IOException e) {
			e.getMessage();
		}
	}

}
