/**
 * @author md
 * Date 2/2011
 */
import java.util.ArrayList;
public class Network {
	private final int[][] ADMATRIX;//adjacency matrix
	private int[][] floydArray;
	private int[][] floydPath;
	
	private ArrayList<Station> stations;
	private ArrayList<Line> lines; 

	public Network(){
		this.stations = loadStations();
		this.ADMATRIX = adMatrixMaker();
		this.lines = loadLines();
		loadLinesInStations();
		floydArray = new int[ADMATRIX.length][ADMATRIX.length];
		floydPath = new int[ADMATRIX.length][ADMATRIX.length];
		System.arraycopy(ADMATRIX, 0, floydArray, 0, (floydArray.length));
		//floydArray = arrayCopier(ADMATRIX,floydArray);
		floyd();
	}
	public Station getStations(int id){
		if(id>stations.size() || id<1){
			return null;
		}
		Station temp = stations.get(id-1);
		if(temp.getID() == id){
			return temp;
		}else{ //this is in case data was not processed in order of "id"
			for(Station s: stations){
				if(s.getID() == id){
					return s;
				}
			}
		}
		return null;
	}
	private void floyd(){
		int ja = floydArray.length;
		for(int i = 1; i < ja;i++){
			for(int j = 1; j < ja;j++){
				for(int k = 1; k < ja;k++){
					if((floydArray[j][i]+floydArray[i][k])<floydArray[j][k]){
						floydArray[j][k] = floydArray[j][i]+floydArray[i][k];
						floydPath[j][k] = i;
					}		
				}
			}
			
		}
		System.out.println();
	}
	
    public ArrayList<Integer> getPath (  int start,  int end )  {
        int selected = floydPath[start][end];
    	if(selected == 0)  {
            return new ArrayList<Integer>();
        }
        final ArrayList<Integer> path = new ArrayList<Integer>();
        
        path.addAll(getPath(start, selected));
        path.add(selected);
        path.addAll(getPath(selected, end));
        return path;
    }

	private int[][] adMatrixMaker(){
		//station ids dont start at 0
		int[][] matrix = new int[stations.size()+1][stations.size()+1];
		ArrayList<String> data = FileHandler.Loader("lineDefinitions.txt");
		
		//first the adjacency matrix is filled in with stations that are adjacent
		for(int i=0;i<data.size();i = i + 3){
			matrix[Integer.parseInt(data.get(i))][Integer.parseInt(data.get(i+1))] = 1;
			matrix[Integer.parseInt(data.get(i+1))][Integer.parseInt(data.get(i))] = 1;
			}
		//then the remainder of the matrix is filled
		for(int j = 0;j<matrix.length;j++){
			for(int k = 0; k<matrix.length;k++){
				if(j == k){
					matrix[j][k] = 0;
				}else
					if(matrix[j][k] != 1){
						matrix[j][k] = 777;
					}
			}
		}
		return matrix;
	}
	//loads station data
	private ArrayList<Station> loadStations(){
		ArrayList<String> data = FileHandler.Loader("alltubestations.txt");
		ArrayList<Station> stationsLi = new ArrayList<Station>();
		//stationsLi.add(new Station("dummy",999));
		for(int i=0;i<data.size();i = i + 2){
			Station temp = new Station(data.get(i+1),Integer.parseInt(data.get(i)));
			stationsLi.add(temp);
		}
		return stationsLi;
		
	}
	//loads line data
	private ArrayList<Line> loadLines(){
		ArrayList<String> data = FileHandler.Loader("lines.txt");
		ArrayList<Line> l = new ArrayList<Line>();
		//l.add(null);
		for(int i = 0; i<data.size();i=i+2){
			//l.add(Integer.parseInt(data.get(i)), new Line(data.get(i+1),Integer.parseInt(data.get(i))));// so we dont start at 0
			l.add(new Line(data.get(i+1),Integer.parseInt(data.get(i))));
		}
		return l;
		
	}
	//this actually associates every station with a line
	private void loadLinesInStations(){
		ArrayList<String> data = FileHandler.Loader("lineDefinitions.txt");
	
		int station1,station2,lineid;
		Station temp;
		//first the adjacency matrix is filled in with stations that are adjacent
		for(int i=0;i<data.size();i = i + 3){
			//-1 to account for things starting at index 0
			lineid = Integer.parseInt(data.get(i+2)) - 1;
		
			station1 = Integer.parseInt(data.get(i)) - 1;
			temp = new Station(stations.get(station1).getLines(),stations.get(station1).getName(),stations.get(station1).getID());
			Line temp2 = lines.get(lineid);
			temp.addLine(temp2);
			stations.set(station1, temp); //updating the stations 
			
			station2 = Integer.parseInt(data.get(i+1)) - 1;
			temp = new Station(stations.get(station2).getLines(),stations.get(station2).getName(),stations.get(station2).getID());
			temp.addLine(lines.get(lineid));
			stations.set(station2, temp); //updating the stations 

		}
	}

	
	public void printYourStations(){
		int i = 0;
		for(Station s:stations){
			if(i==5){
				System.out.println();
				i = 0;
			}
			System.out.print(s.getID() + " " + s.getName()+"  |  ");
			i++;
		}
		System.out.println();
	}
	public void printYourLines(){
		for(Line l:lines){
			System.out.println("Name: " + l.getName() + " ID: " + l.getID());
			}
	}

}
