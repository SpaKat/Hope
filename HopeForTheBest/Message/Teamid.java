package Message;

import java.io.Serializable;

public class Teamid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8432287976865200199L;
	private int id;
	
	public Teamid(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
}
