/**
 * @author md
 * Date 2/2011
 */
public class Line extends NetworkElement{
	
	public Line(String name,int id){
		super(name,id);
	}

	// These static methods are mainly used for the route description algorithm
	
	//commonLine determines if stations one and two are on the same line, and if so which line.
	public static Line commonLine(Station one, Station two){
		for(Line l: two.getLines()){
			for(Line i: one.getLines()){
				if(l.getID() == i.getID()){
					return i;
				}
			}
		}
		return null;
	}
	//given three sequential stations, the method returns which line to switch to at station two in order to get from station one to three
	public static Line switchLine(Station one, Station two, Station three){

		if(notSameLine(one,three)){
			for(Line l: two.getLines()){
				for(Line i: three.getLines()){
					if(l.getID() == i.getID()){
						return l;
					}
				}
			}
		}
		return null;
	}
	//determines if stations one and two arent on the same line
	private static Boolean notSameLine(Station one, Station two){
		boolean change = true;
		for(Line l: one.getLines()){
			for(Line i: two.getLines()){
				if(l.getID() == i.getID()){
					change = false;
				}
			}
		}
		//return false if on the sameLine, return true if notsame line
		return change;
	}
}