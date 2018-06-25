package control;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

    }

    M1 m1 = new M1();

    @FXML
    private TextField name;

    @FXML
    private static String urlText;

    /** コンボボックス：名前 */
    @FXML
    private ComboBox<String> cbKind;

    Alert alert = new Alert(AlertType.ERROR); //アラートを作成

	@FXML
    public void onClick(ActionEvent event) {
    	String urlName = name.getText();
    	String url = urlText;
        if(urlName.isEmpty() || urlName == "null") {
        	alert.setContentText("名前を入力して下さい。");
        	alert.showAndWait(); //表示
        	return;
        }

        else if(url.isEmpty() || url == "null") {
        	alert.setContentText("URLがありません。");
        	alert.showAndWait(); //表示
        	return;
        }

        else {
        	Map<String, Map<Integer, Map<String, String>>> bookMark = new HashMap<String, Map<Integer,Map<String,String>>>();
        	bookMark = m1.getBookMarkList();
        	bookMark.put("test",new HashMap());
        	bookMark.get("test").put(1, new HashMap());
        	bookMark.get("test").get(1).put(urlName, url);
        	m1.setBookMarkList(bookMark);

        	try {
        	    FileOutputStream fos = new FileOutputStream("SaveData.dat");
        	    ObjectOutputStream oos = new ObjectOutputStream(fos);
        	    oos.writeObject(m1);
        	    oos.close();
        	} catch (Exception e) {
        	}
        }
    }

	@FXML
    public void onLode(ActionEvent event) {
		try {
		    FileInputStream fis = new FileInputStream("SaveData.dat");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    M1 m1 = (M1) ois.readObject();
		    ois.close();
		} catch (Exception e) {
		}
    }

	public static void setUrl(String url) {
		urlText = url;
	}
}
