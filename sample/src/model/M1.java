package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class M1 implements Serializable{
	@FXML
    private TextField url;

	@FXML
	private TextField name;

	@FXML
	private String urlText;

	@FXML
	private Map<String,Map<Integer,Map<String,String>>> bookMarkList = new HashMap<>();

	public TextField getUrl() {
		return url;
	}

	public void setUrl(TextField url) {
		this.url = url;
	}

	public TextField getName() {
		return name;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public String getUrlText() {
		return urlText;
	}

	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

	public Map<String, Map<Integer, Map<String, String>>> getBookMarkList() {
		return bookMarkList;
	}

	public void setBookMarkList(Map<String, Map<Integer, Map<String, String>>> bookMarkList) {
		this.bookMarkList = bookMarkList;
	}

}