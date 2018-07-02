package control;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.M1;

public class Controller3 implements Initializable{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	new common.LodeData();
    }

    M1 m1 = new M1();

    @FXML
    private TextField text;

    private static String urlText;

    private static String comBoxDate;

    private static Integer flag;

    Alert alert = new Alert(AlertType.ERROR); //アラートを作成

	@FXML
    public void onAdd(ActionEvent event) {
    	String textName = text.getText();
    	String url = urlText;
    	String comBox = comBoxDate;
    	Integer flagValue = flag;
        if(textName.isEmpty() || textName == "null") {
        	alert.setContentText("名前を入力して下さい。");
        	alert.showAndWait(); //表示
        	return;
        }
        else {
        	new common.LodeData();
        	Integer max = 0;
        	Integer[] sort =  (Integer[]) m1.getBookMarkList().get(comBox).keySet().toArray();
        	for(int i = 0; i < sort.length; i++) {
               max = Math.max(max,sort[i]);
        	}
        	if(m1.getBookMarkList() == null) {
	        	Map<String, Map<Integer, Map<String, String>>> bookMark = new HashMap<String, Map<Integer,Map<String,String>>>();
	        	bookMark = m1.getBookMarkList();
	        	bookMark.put("test",new HashMap());
	        	bookMark.get("test").put(1, new HashMap());
	        	bookMark.get("test").get(1).put(textName, url);
	        	m1.setBookMarkList(bookMark);

	        	new common.SaveData();
        	}
        	else {
        		if(!(url.isEmpty()) || !(url == "null")) {
	        		m1.getBookMarkList().get(comBox).put(max,new HashMap());
	        		m1.getBookMarkList().get(comBox).get(max).put(textName, url);
	        		new common.SaveData();
        		}
        		else{
        			m1.getBookMarkList().put(textName, new HashMap());
        			new common.SaveData();
        		}
        	}
        }
    }


	public void setUrl(String url) {
		urlText = url;
	}

	public void setComBox(String comBox) {
		comBoxDate = comBox;
	}

	public void setFlag(Integer flagValue) {
		flag = flagValue;
	}
}
