import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileParser {

	public static FileObject readFile(String fileName) {
		Logger log = Logger.getLogger(FileObject.class);
		
		FileObject fo = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String currentLine = "";

			List<String> attributes = new ArrayList<String>();
			List<List<Double>> values_by_attr = new ArrayList<List<Double>>();

			int lineNum = 0; // for debugging
			log.info("Reading File " + fileName);
			// Read in all attributes
			while(!currentLine.contains("@data")){
				if(currentLine.contains("@attribute") && !currentLine.contains("%")){
					//System.out.println(currentLine.substring("@attribute".length()+1));
					attributes.add(
							currentLine.substring("@attribute".length(), 
													currentLine.length() - "numeric".length())
										.trim()
										.replaceAll("[ \\t]+", "_"));
				} 
				currentLine = br.readLine(); 
				lineNum++;
			}

			if(currentLine.contains("@data")){
				// have reached data 
				System.out.println("Reading data in file " + fileName + "; found " + attributes.size() + " attributes.");

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
						try{
							values_by_attr.get(i).add(Double.parseDouble(tokens[i].trim()));
						} catch(NumberFormatException e){
							//log.info("line " + lineNum + ", token " + i + ": Not a valid Double, replacing with 1.0");
							values_by_attr.get(i).add((double) lineNum);
						} 
					}
				}

			} else {
				System.out.println("No data found in file " + fileName);
			}

			br.close();

			// Finally print it all out to check it's right
			if(Parameters.DEBUG_FILEPARSER){
				System.out.println("Attributes:");
				for(int i = 0; i < attributes.size(); i++){
					System.out.println(attributes.get(i));
				}
				for(int i = 0; i < values_by_attr.get(values_by_attr.size()-1).size(); i++){
					for(int j = 0; j < values_by_attr.size(); j++){
						System.out.print(values_by_attr.get(j).get(i).intValue() + ",");
					}
					System.out.print("\n");
				}
			}
			
			
			fo = new FileObject(values_by_attr, attributes);
			
			System.out.println("Finished reading file");
			
		} catch (FileNotFoundException e) {
			log.error("Couldn't find file: " + fileName + "; No data available.");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Couldn't read file: " + fileName + "; No data avaiable"); 
			e.printStackTrace();
		}

		return fo;
	}

	/*public static void main(String[] args){
		readFile(Parameters.FILENAME);
	}*/

}
