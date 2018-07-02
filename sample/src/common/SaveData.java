package common;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import model.M1;

public class SaveData {
	public static void save(M1 m1) {
		try {
		    FileOutputStream fos = new FileOutputStream("SaveData.dat");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(m1);
		    oos.close();
		} catch (Exception e) {
		}
	}
}