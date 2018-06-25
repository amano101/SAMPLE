package application;

import java.net.URL;

import control.Controller2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.M1;

public class Sample extends Application {

	private M1 datebase;

    public static void main(String[] args) {
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
            Controller2.setUrl(result);
        }

        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }
}