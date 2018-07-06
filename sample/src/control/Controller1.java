package control;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.Sample;
import common.LodeData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import model.M1;

public class Controller1 extends Sample implements Initializable{

	@FXML
    private TableView<Person> table;

	@FXML
	private Menu moveMenu;

    @FXML
    private TextField url;

    @FXML
    private TableColumn playList;

    @FXML
    private WebView video;

    @FXML
    private ComboBox<String> list = new ComboBox<>();

    private String bookMarkName;

	private M1 m1 = new M1();

	private MenuItem menuItem = null;

	private List<Integer> sort = new ArrayList<>();

	@Override
    public void initialize(URL location, ResourceBundle resources) {
		playList.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
    	LodeData lodeData = new LodeData();
    	lodeData.lode();
    	m1 = lodeData.getM1();
		list.getItems().clear();
    	if(new common.NullCheck().nullCheck(m1)) {
    		//単一セルの選択の有効化
    		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    		table.getSelectionModel().setCellSelectionEnabled(true);
    		list.getItems().addAll(m1.getBookMarkList().keySet());
    		List<String> listData = new ArrayList<>();
    		listData.addAll(m1.getBookMarkList().keySet());
	    	if(m1.getBookMarkList().size()> 0) {
	    		moveMenu.setDisable(false);
	    		for(int i = 0;i < m1.getBookMarkList().size(); i++) {
	    			menuItem = new MenuItem(listData.get(i));
	    			moveMenu.getItems().add(i,menuItem);
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
    	List<String> list = new ArrayList<>();
    	Map<String, String> map = new HashMap<>();
    	if(new common.NullCheck().nullCheck(m1)) {
	    	for(int i = 0;i < sort.size();i++) {
	    		map.putAll(m1.getBookMarkList().get(comBox).get(i));
	    	}
	    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());

			for(int i = 0;i < sort.size();i++) {
				list.addAll(m1.getBookMarkList().get(comBox).get(sort.get(0)).keySet());
				table.getItems().add(new Person(list.get(i)));
			}
    	}
    }

    @FXML
    public void onMenu(ActionEvent event) {
    	this.bookMarkName = table.getSelectionModel().getSelectedItem().name.getValue();
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
    	Map<String, String> map = new HashMap<>();
    	List<String> list = new ArrayList<>();
    	if(new common.NullCheck().nullCheck(m1)) {
	    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());
	    	for(int i = 0;i < sort.size();i++) {
	    		if(m1.getBookMarkList().get(comBox).get(sort.get(i)).containsKey(bookMarkName) == true) {
	    			m1.getBookMarkList().get(comBox).get(sort.get(i)).remove(bookMarkName);
	    			m1.getBookMarkList().get(comBox).remove(sort.get(i));
	    		}
	    	}
	    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());
	    	if(sort == null || sort.isEmpty()) {
		    	for(int j = 1;j < sort.size()+1;j++){
		    		m1.getBookMarkList().get(comBox).put(j,m1.getBookMarkList().get(comBox).get(sort.get(j)));
		    	}
		    	common.SaveData.save(m1);
		    }
	    	common.SaveData.save(m1);

	    	if(new common.NullCheck().nullCheck(m1)) {
		    	for(int i = 0;i < sort.size();i++) {
		    		map.putAll(m1.getBookMarkList().get(comBox).get(i));
		    	}
		    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());

				for(int i = 0;i < sort.size();i++) {
					list.addAll(m1.getBookMarkList().get(comBox).get(sort.get(0)).keySet());
					table.getItems().add(new Person(list.get(i)));
				}
	    	}
    	}
    }

    @FXML
    public void onMove(ActionEvent event) {
    	String text = "null";
    	String comBox = list.getValue();
    	if(new common.NullCheck().nullCheck(m1)) {
    		sort.addAll(m1.getBookMarkList().get(comBox).keySet());
	    	for(int i = 0;i < sort.size();i++) {
	    		text = m1.getBookMarkList().get(comBox).get(sort.get(i)).get(bookMarkName);
	    		if(text != "null" || !(text.isEmpty())) {
	    			break;
	    		}
	    	}
	    	for(int i = 0;i < sort.size();i++) {
	    		m1.getBookMarkList().get(comBox).get(sort.get(i)).remove(bookMarkName);
	    	}
	    	String name = moveMenu.onActionProperty().getName();
	    	Integer max = 0;
	    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());
	    	for(int i = 0; i < sort.size(); i++) {
	            max = Math.max(max,sort.get(i));
	     	}
	    	m1.getBookMarkList().get(name).put(max+1, new HashMap());
	    	m1.getBookMarkList().get(name).get(max+1).put(bookMarkName, text);
	    	common.SaveData.save(m1);
    	}
    }

    public class Person {
        private StringProperty name;

        public Person(String aName) {
            name = new SimpleStringProperty(aName);
        }
        public StringProperty nameProperty() {
            return name;
        }
    }
}