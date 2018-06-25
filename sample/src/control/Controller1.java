package control;
import java.net.URL;
import java.util.ResourceBundle;

import application.Sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class Controller1 extends Sample implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField url;

    @FXML
    private WebView video;


    @FXML
    public void onClick(ActionEvent event) {
    	String result = url.getText();
        if(result.isEmpty() || result == "null") {
        	return;
        }
        else{
        	video.getEngine().load(result);

        }
    }

    @FXML
    public void onBookmark(ActionEvent event) throws Exception {
    	String result = url.getText();
    	Sample Daialog1 = new Sample();
    	Daialog1.dialog1(result);
    }
}