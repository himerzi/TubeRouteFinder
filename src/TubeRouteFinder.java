import java.util.ArrayList;

/**
 * @author md
 * Date 2/2011
 */
public class TubeRouteFinder {
	private Network tube;
	private int start, end;
	public void run(){
			tube = new Network();
			tube.printYourStations();
			getInput();
			printRoute(tube.getPath(start, end),start,end);
		
	}
	private void getInput(){
		//values are set to ensure valid number is entered
		start = -1;
		end = -1;
		Input input = new Input();
		System.out.println("------------------------------------------------------------------------");
		while(tube.getStations(start)==null){
			System.out.println("enter the valid station number of the starting station, or enter 999 to quit: ");
			start = input.nextInt();
		}
		while(tube.getStations(end)==null){
			System.out.println("enter the valid station number of the end station, or enter 999 to quit: ");
			end = input.nextInt();
		}	
	}
	private void printRoute(ArrayList<Integer> r,int start,int end){
		r.add(0, start);
		r.add(r.size(),end);
		System.out.println("Start at " + Line.commonLine(tube.getStations(start), tube.getStations(r.get(1))).getName() + " at "+ tube.getStations(start).getName() + " station.");
		
		for(int i = 1;i<r.size()-1;i++){
			int current = r.get(i);
			System.out.print("Train will stop at " + tube.getStations(current).getName() + " station");
			if(i+1 < r.size()&&i>0){
				Line change  = Line.switchLine(tube.getStations(r.get(i-1)), tube.getStations(current), tube.getStations(r.get(i+1)));
				if(change != null){
					System.out.println(". At this station switch to the " + change.getName()+".");
				
				}else{
					//for formatting of output
					System.out.println();
				}
			}
			

		}
		System.out.println("Arrive at " + tube.getStations(end).getName() + " station.");
		
	}
	public static void main(String[] args) {
		TubeRouteFinder t = new TubeRouteFinder();
		t.run();
		
	}

}


