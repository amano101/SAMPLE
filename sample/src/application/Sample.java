package application;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

import control.Controller2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.M1;

public class Sample extends Application {

	private static M1 m1 = new M1();

    public static void main(String[] args) {
		try {
		    FileInputStream fis = new FileInputStream("SaveData.dat");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    m1 = (M1) ois.readObject();
		    ois.close();
		} catch (Exception e) {
		}
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	String fxml = "/view/url.fxml";
     	xfmlLode(fxml,null);
    }

    public void dialog1(String result) throws Exception{
    	String fxml = "/view/dialog1.fxml";
    	xfmlLode(fxml,result);
    }

    public void xfmlLode(String fxml,String result) throws Exception {
    	Stage stage = new Stage();
    	URL             location    = getClass().getResource(fxml);
        FXMLLoader      fxmlLoader  = new FXMLLoader( location );
        // シーングラフの作成
        Pane    root        = (Pane) fxmlLoader.load();
        if(!(result == null)) {
            // ロードしたFXMLファイルに関連づくControllerを取得
            final Controller2 c2 = (Controller2) fxmlLoader.getController();
            // Controllerさん、どうぞ。
            c2.setUrl(result);
        }

        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }

	public M1 getM1() {
		return m1;
	}

	public void setM1(M1 m1) {
		this.m1 = m1;
	}
}