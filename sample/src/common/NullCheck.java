package common;
import model.M1;

public class NullCheck {
	public boolean nullCheck(M1 m1){
		if(m1.getBookMarkList() == null|| m1.getBookMarkList().isEmpty()) {
			return false;
		}
		return true;
    }
}