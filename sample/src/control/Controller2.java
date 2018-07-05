package control;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import common.LodeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.M1;

public class Controller2 implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	LodeData lodeData = new LodeData();
    	lodeData.lode();
    	m1 = lodeData.getM1();
    	onAdd.getItems().clear();
    	if(new common.NullCheck().nullCheck(m1)) {
    		onAdd.getItems().addAll(m1.getBookMarkList().keySet());
    	}
    }

    M1 m1 = new M1();

    @FXML
    private TextField urlName;

    @FXML
    private static String urlText;

    /** コンボボックス：名前 */
    @FXML
    private ComboBox<String> onAdd;

    Alert alert = new Alert(AlertType.ERROR); //アラートを作成

	@FXML
    public void onClick(ActionEvent event) {
    	String name = urlName.getText();
    	String url = urlText;
        if(name.isEmpty() || name == "null") {
        	alert.setContentText("名前を入力して下さい。");
        	alert.showAndWait(); //表示
        	return;
        }

        else if(!(new common.NullCheck().nullCheck(m1))) {
        	alert.setContentText("URLがありません。");
        	alert.showAndWait(); //表示
        	return;
        }

        else {
        	Map<String, Map<Integer, Map<String, String>>> bookMark = new HashMap<String, Map<Integer,Map<String,String>>>();
        	bookMark = m1.getBookMarkList();
        	bookMark.put("test",new HashMap());
        	bookMark.get("test").put(1, new HashMap());
        	bookMark.get("test").get(1).put(name, url);
        	m1.setBookMarkList(bookMark);

        	common.SaveData.save(m1);
        }
    }

	@FXML
    public void onAddPlayList(ActionEvent event) {
	}

	@FXML
    public void onDeletePlayList(ActionEvent event) {
	}

	@FXML
    public void onAdd(ActionEvent event) {
	}


	@FXML
    public void onLode(ActionEvent event) {
		new common.LodeData();
    }

	public void setUrl(String url) {
		urlText = url;
	}
}
