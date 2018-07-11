package control;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    M1 m1 = new M1();

    @FXML
    private TextField urlName;

    @FXML
    private static String urlText;

    /** コンボボックス：名前 */
    @FXML
    private ComboBox<String> playList;

    Alert alert = new Alert(AlertType.ERROR); //アラートを作成

	private String comBoxDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	LodeData lodeData = new LodeData();
    	lodeData.lode();
    	m1 = lodeData.getM1();
    	String comBox = comBoxDate;
    	playList.getItems().clear();
    	if(new common.NullCheck().nullCheck(m1)) {
    		playList.getItems().addAll(m1.getBookMarkList().keySet());
	    	if(comBox != "null") {
	    		playList.getSelectionModel().select(comBoxDate);
	    	}
    	}
    }

/*	@FXML
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
*/
	@FXML
    public void onAddPlayList(ActionEvent event) {
		String playListName = urlName.getText();
		if(playListName.isEmpty() || playListName == "null") {
        	alert.setContentText("名前を入力して下さい。");
        	alert.showAndWait(); //表示
        	return;
        }
		else {
			List<String> list = new ArrayList<>();
			list.addAll(m1.getBookMarkList().keySet());
			boolean cheak = false;
			for(int i = 0;i<list.size();i++) {
				if(list.get(i) == playListName) {
					cheak = true;
					return;
				}
			}
			if(!cheak) {
				m1.getBookMarkList().put(playListName,new HashMap());
				common.SaveData.save(m1);
			}
		}
		 initialize(null, null);
	}

	@FXML
    public void onDeletePlayList(ActionEvent event) {
		String selectPlayList = playList.getValue();
		if(selectPlayList.isEmpty() || selectPlayList == "null") {
			alert.setContentText("プレイリストを選択して下さい。");
        	alert.showAndWait(); //表示
        	return;
		}
		else{
			m1.getBookMarkList().remove(selectPlayList);
			common.SaveData.save(m1);
		}
		initialize(null, null);
	}

	@FXML
    public void onAdd(ActionEvent event) {
		String selectPlayList = playList.getValue();
		String name = urlName.getText();
		String url = urlText;
		if(selectPlayList.isEmpty() || selectPlayList == "null") {
			alert.setContentText("プレイリストを選択して下さい。");
        	alert.showAndWait(); //表示
        	return;
		}
		else if(name.isEmpty() || name == "null") {
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
        	List<Integer> sort = new ArrayList<>();
        	Integer max = 0;
	    	sort.addAll(m1.getBookMarkList().get(selectPlayList).keySet());
	    	for(int i = 0; i < sort.size(); i++) {
	            max = Math.max(max,sort.get(i));
	     	}
	    	m1.getBookMarkList().get(selectPlayList).put(max+1, new HashMap());
	    	m1.getBookMarkList().get(selectPlayList).get(max+1).put(name, url);

	    	common.SaveData.save(m1);
        }
		initialize(null, null);
	}

	@FXML
    public void onLode(ActionEvent event) {
		new common.LodeData();
    }

	public void setUrl(String url) {
		urlText = url;
	}

	public void setComBox(String comBox) {
		comBoxDate = comBox;
		initialize(null, null);
	}

}
