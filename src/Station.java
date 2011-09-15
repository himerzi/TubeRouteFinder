import java.util.ArrayList;

/**
 * @author md
 * Date 2/2011
 */
public class Station extends NetworkElement {

	private ArrayList<Line> lines;
	//note that data is formatted so station id's dont start at 0
	public Station(ArrayList<Line> lines, String name, int id){
		super(name,id);
		//this.name = name;
		this.lines = lines;
		//this.id = id;
	

	}
	public Station(String name, int id){
		super(name,id);
		lines = new ArrayList<Line>();
	}

	public void addLine(Line l){
		lines.add(l);
	}
	public ArrayList<Line> getLines(){
		return lines;
	}

}
