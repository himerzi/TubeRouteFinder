
public class NetworkElement {
	protected final String name;
	protected final int  id;
	public NetworkElement(String name, int id){
		this.name = name;
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public int getID(){
		return id;
	}

}
