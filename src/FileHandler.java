import java.util.ArrayList;
public class FileHandler {

	public static ArrayList<String> Loader(String fileLocation){
	   FileInput in = new FileInput(fileLocation);
		ArrayList<String> data = new ArrayList<String>();
		while (in.hasNextLine()) {
			String c = in.nextLine();
			data.add(c);
		}
		in.close();
		return data;
	}

}
