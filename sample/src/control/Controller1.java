package control;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.Sample;
import common.LodeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import model.M1;

public class Controller1 extends Sample implements Initializable{

	@FXML
    private TableView<String> table;

	@FXML
	private Menu moveMenu;

    @FXML
    private TextField url;

    @FXML
    private TableColumn<Integer, String> playList;

    @FXML
    private WebView video;

    @FXML
    private ComboBox<String> list = new ComboBox<>();

    private String bookMarkName;

	private M1 m1 = new M1();

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    	LodeData lodeData = new LodeData();
    	lodeData.lode();
    	m1 = lodeData.getM1();
    	if(m1.getBookMarkList() != null) {
    		String[] Number = Arrays.asList(m1.getBookMarkList().keySet()).toArray(new String[m1.getBookMarkList().keySet().size()]);
    		for(int i = 0;i<= Number.length;i++) {
    			list.setValue(Number[i]);
    		}
	    	MenuItem[] menuList = (MenuItem[]) m1.getBookMarkList().keySet().toArray();
	    	if(menuList.length > 0) {
	    		moveMenu.setDisable(false);

	    		for(int i = 0;i <= menuList.length; i++) {
	    			moveMenu.getItems().add(i,menuList[i]);
	    		}
	    	}
	    	else {
	    		moveMenu.setDisable(true);
	    	}
    	}

    }

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

    @FXML
    public void onChange(ActionEvent event) throws Exception {
    	String comBox = list.getValue();
    	Integer[] sort = (Integer[]) m1.getBookMarkList().get(comBox).keySet().toArray();
    	Map<String, String> map = new HashMap<>();
    	for(int i = 0;i<=sort.length;i++) {
    		map.putAll(m1.getBookMarkList().get(comBox).get(i));
    	}

    	List<String> a = new ArrayList<String>();
    	ObservableList<String> books = FXCollections.observableArrayList();
		for(int i = 0;i<=sort.length;i++) {
			books.addAll(m1.getBookMarkList().get(comBox).get(sort[i]).keySet());
		}
		table.setItems(books);
    }

    @FXML
    public void onMenu(ActionEvent event) {
    	this.bookMarkName = table.selectionModelProperty().getName();
    }

    @FXML
    public void onAdd(ActionEvent event) throws Exception {
    	String comBox = list.getValue();
    	String result = url.getText();
    	Sample Daialog2 = new Sample();
    	Daialog2.dialog2(result,comBox);
    }

    @FXML
    public void onDelete(ActionEvent event) {
    	String comBox = list.getValue();
    	Integer[] sort =  (Integer[]) m1.getBookMarkList().get(comBox).keySet().toArray();
    	for(int i = 0;i<=sort.length;i++) {
    		m1.getBookMarkList().get(comBox).get(sort[i]).remove(bookMarkName);
    	}
    	sort =  (Integer[]) m1.getBookMarkList().get(comBox).keySet().toArray();
    	for(int j = 1;j<sort.length+1;j++){
    		m1.getBookMarkList().get(comBox).put(j,m1.getBookMarkList().get(comBox).get(sort[j]));
    	}
    	common.SaveData.save(m1);
    }

    @FXML
    public void onMove(ActionEvent event) {
    	String text = "null";
    	String comBox = list.getValue();
    	Integer[] sort =  (Integer[]) m1.getBookMarkList().get(comBox).keySet().toArray();
    	for(int i = 0;i<=sort.length;i++) {
    		text = m1.getBookMarkList().get(comBox).get(sort[i]).get(bookMarkName);
    		if(text != "null" || !(text.isEmpty())) {
    			break;
    		}
    	}
    	for(int i = 0;i<=sort.length;i++) {
    		m1.getBookMarkList().get(comBox).get(sort[i]).remove(bookMarkName);
    	}
    	String name = moveMenu.onActionProperty().getName();
    	Integer max = 0;
    	Integer[] sort2 =  (Integer[]) m1.getBookMarkList().get(name).keySet().toArray();
    	for(int i = 0; i < sort2.length; i++) {
            max = Math.max(max,sort[i]);
     	}
    	m1.getBookMarkList().get(name).put(max+1, new HashMap());
    	m1.getBookMarkList().get(name).get(max+1).put(bookMarkName, text);
    	common.SaveData.save(m1);
    }
}