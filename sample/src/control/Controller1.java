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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	    		moveMenu.getItems().clear();
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

    private void abortAction(WindowEvent t) {
    	initialize(null, null);
    }


    @FXML
    public void onBookmark(ActionEvent event) throws Exception {
    	String comBox = list.getValue();
    	String result = url.getText();
    	String fxml = "/view/dialog1.fxml";
    	Stage stage = new Stage();
    	URL             location    = getClass().getResource(fxml);
        FXMLLoader      fxmlLoader  = new FXMLLoader( location );
        // シーングラフの作成
        Pane    root        = (Pane) fxmlLoader.load();

        // ロードしたFXMLファイルに関連づくControllerを取得
        final Controller2 c2 = (Controller2) fxmlLoader.getController();
        // Controllerさん、どうぞ。
        c2.setUrl(result);
        c2.setComBox(comBox);


        // シーンの作成
        Scene   scene       = new Scene( root , root.getPrefWidth() , root.getPrefHeight());
        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();

    	stage.setOnCloseRequest(t -> {
    		stage.hide();
        	abortAction(t);
        });
    }

    @FXML
    public void onChange(ActionEvent event) throws Exception {
    	table.getItems().clear();
    	String comBox = list.getValue();
    	List<String> list = new ArrayList<>();
    	Map<String,String> map = new HashMap<>();
    	if(new common.NullCheck().nullCheck(m1)) {
    		sort.clear();
    		if(!(m1.getBookMarkList().get(comBox).isEmpty()) || m1.getBookMarkList().get(comBox) != null) {
    		sort.addAll(m1.getBookMarkList().get(comBox).keySet());
    		}
/*	    	for(int i = 0;i < sort.size();i++) {
	    		map.putAll(m1.getBookMarkList().get(comBox).get(sort.get(0)));
	    	}*/
    		if(!(sort.isEmpty()) || sort != null) {
				for(int i = 0;i < sort.size();i++) {
					list.addAll(m1.getBookMarkList().get(comBox).get(sort.get(i)).keySet());
					table.getItems().add(new Person(list.get(i)));
				}
    		}
    	}
    }

    @FXML
    public void onMenu(ActionEvent event) {
    	if(table.getSelectionModel().getSelectedItem() != null) {
    		this.bookMarkName = table.getSelectionModel().getSelectedItem().name.getValue();
    	}
    }

    @FXML
    public void onAdd(ActionEvent event) throws Exception {
    	String comBox = list.getValue();
    	String result = url.getText();
    	String fxml = "/view/dialog2.fxml";

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

    	stage.setOnCloseRequest(t -> {
    		stage.hide();
        	abortAction(t);
        });
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
	    			break;
	    		}
	    	}
	    	sort.clear();
	    	sort.addAll(m1.getBookMarkList().get(comBox).keySet());
	    	if(!(sort.isEmpty() || sort != null)) {
		    	for(int i = 0;i < sort.size();i++){
		    		m1.getBookMarkList().get(comBox).put(i,m1.getBookMarkList().get(comBox).get(sort.get(i+1)));
		    	}
		    }
	    	common.SaveData.save(m1);
	    	initialize(null, null);
	    	if(new common.NullCheck().nullCheck(m1)) {
		    	for(int i = 0;i < sort.size();i++) {
		    		map.putAll(m1.getBookMarkList().get(comBox).get(i));
		    	}
		    	sort.clear();
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
    	initialize(null, null);
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