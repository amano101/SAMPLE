package common;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import model.M1;

public class LodeData {
	private M1 m1 = new M1();
	public M1 lode(){
		try {
		    FileInputStream fis = new FileInputStream("SaveData.dat");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    setM1((M1) ois.readObject());
		    ois.close();
		} catch (Exception e) {
		}
		return m1;
    }
	public M1 getM1() {
		return m1;
	}
	public void setM1(M1 m1) {
		this.m1 = m1;
	}
}