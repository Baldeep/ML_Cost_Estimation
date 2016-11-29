import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

	public static String fileName = "china.arff";
	
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String currentLine = "";
			
			ArrayList<String> attributes = new ArrayList<String>();
			ArrayList<ArrayList<Double>> values_by_attr = new ArrayList<ArrayList<Double>>();
			
			int lineNum = 0; // for debugging
			
			// Read in all attributes
			while(!currentLine.contains("@data")){
				if(currentLine.contains("@attribute") && !currentLine.contains("%")){
					System.out.println(currentLine.substring("@attribute".length()+1));
					attributes.add(currentLine.substring(0, "@attribute".length()));
				} 
				currentLine = br.readLine(); 
				lineNum++;
			}
			
			if(currentLine.contains("@data")){
				// have reached data 
				System.out.println("Have reached data in file " + fileName);
				
				// Create a list for each attribute
				for(int i = 0; i < attributes.size(); i++){
					values_by_attr.add(new ArrayList<Double>());
				}
				
				while((currentLine = br.readLine()) != null){
					lineNum++;
					String[] tokens = currentLine.split(",");
					if(tokens.length != attributes.size()){
						System.out.println("Mismatching number of tokens found on line " + lineNum + 
											"; tokens: " + tokens.length + ", attributes: " + attributes.size());
					}
					
					for(int i = 0; i < tokens.length; i++){
						values_by_attr.get(i).add(Double.parseDouble(tokens[i].trim()));
					}
				}
				
			} else {
				System.out.println("No data found in file " + fileName);
			}
			
			br.close();
			
			// Finally print it all out to check it's right
			for(int i = 0; i < values_by_attr.get(0).size(); i++){
				for(int j = 0; j < values_by_attr.size(); j++){
					System.out.print(values_by_attr.get(j).get(i).intValue() + ",");
				}
				System.out.println("\n");
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
