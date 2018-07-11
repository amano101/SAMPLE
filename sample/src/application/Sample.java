package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.M1;

public class Sample extends Application {

	private static M1 m1 = new M1();

    public static void main(String[] args) {
    	launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
    	String fxml = "/view/url.fxml";
     	xfmlLode(fxml);
    }
/*
    public void dialog1(String result) throws Exception{
    	String fxml = "/view/dialog1.fxml";
    	xfmlLode(fxml,result);
    }*/

/*    public void dialog2(String result,String bookMarkName) throws Exception{
    	String fxml = "/view/dialog2.fxml";
    	xfmlLode(fxml,result,bookMarkName);
    }*/

    public void xfmlLode(String fxml) throws Exception {
    	Stage stage = new Stage();
    	URL             location    = getClass().getResource(fxml);
        FXMLLoader      fxmlLoader  = new FXMLLoader( location );
        // シーングラフの作成
        Pane    root        = (Pane) fxmlLoader.load();
        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }

/*      public void xfmlLode(String fxml,String result) throws Exception {
    	Stage stage = new Stage();
    	URL             location    = getClass().getResource(fxml);
        FXMLLoader      fxmlLoader  = new FXMLLoader( location );
        // シーングラフの作成
        Pane    root        = (Pane) fxmlLoader.load();

        // ロードしたFXMLファイルに関連づくControllerを取得
        final Controller2 c2 = (Controller2) fxmlLoader.getController();
        // Controllerさん、どうぞ。
        c2.setUrl(result);

        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }*/

/*    public void xfmlLode(String fxml,String result,String comBox) throws Exception {
    	Stage stage = new Stage();
    	URL             location    = getClass().getResource(fxml);
        FXMLLoader      fxmlLoader  = new FXMLLoader( location );
        // シーングラフの作成
        Pane    root        = (Pane) fxmlLoader.load();

        // ロードしたFXMLファイルに関連づくControllerを取得
        final Controller3 c3 = (Controller3) fxmlLoader.getController();
        // Controllerさん、どうぞ。
        c3.setUrl(result);
        c3.setComBox(comBox);

        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }
*/
	public M1 getM1() {
		return m1;
	}

	public void setM1(M1 m1) {
		this.m1 = m1;
	}
}