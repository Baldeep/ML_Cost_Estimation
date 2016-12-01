import java.util.List;

public class FileObject {
	private List<List<Double>> data;
	private List<String> attributeLabels;
	
	public FileObject(List<List<Double>> data, List<String> attributeLabels) {
		this.data = data;
		this.attributeLabels = attributeLabels;
	}

	public List<List<Double>> getData() {
		return data;
	}

	public void setData(List<List<Double>> data) {
		this.data = data;
	}

	public List<String> getAttributeLabels() {
		return attributeLabels;
	}

	public void setAttributeLabels(List<String> attributeLabels) {
		this.attributeLabels = attributeLabels;
	}
	
	public List<Double> getDataForLabel(String label){
		int index = -1;
		for(int i = 0; i < attributeLabels.size(); i++){
			if(attributeLabels.get(i).equals(label)){
				index = i;
				break;
			}
		}
		
		if(index >= 0){
			return data.get(index);
		} else {
			return null;
		}
	}
}
