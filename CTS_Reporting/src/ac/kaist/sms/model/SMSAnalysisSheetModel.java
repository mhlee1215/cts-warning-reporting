package ac.kaist.sms.model;

import java.util.Vector;

import jxl.Sheet;

/**
 * @author mh.lee
 * 
 * xls파일을 다큐먼트 단위의 사용하기 쉬운 데이터로 저장합니다.
 *
 */
public class SMSAnalysisSheetModel {
	public static int HEADER_NOT_FOUND = -999;
	
	private Vector<String> headers;
	private Vector<Vector<String> > contents;
	
	public SMSAnalysisSheetModel(Sheet sheet) {
		//Initialize
		headers = new Vector<String>();
		contents = new Vector<Vector<String> >();
		
		for(int i = 0 ; i < sheet.getRows() ; i++){
			Vector<String> rows = new Vector<String>();
			for(int j = 0 ; j < sheet.getColumns() ; j++){
				//FIrst rows = header
				if(i == 0){
					headers.add(sheet.getCell(j, i).getContents());
				}
				//Otherwise normal content
				else{
					rows.add(sheet.getCell(j, i).getContents());
				}
			}
			//Skip header
			if(i > 0){
				//If each rows contains at least one elements, then add
				boolean all_empty = true;
				for(String str : rows)
					if(str.trim().length() > 0) all_empty = false;
				
				if(!all_empty)
					contents.add(rows);
			}
		}
	}
	
	public String toString(){
		String resultStr = "";
		
		for(int i = 0 ; i < headers.size(); i++){
			resultStr += headers.get(i)+"\t";
		}
		resultStr += "\n";
		for(int i = 0 ; i < contents.size(); i++){
			Vector<String> rows = contents.get(i);
			for(int j = 0 ; j < rows.size(); j++){
				resultStr += rows.get(j)+"\t";
			}
			resultStr += "\n";
		}
		return resultStr;
	}
	
	public int getColumnSum(int columnIndex){
		int sum = 0;
		for(int i = 0 ; i < contents.size(); i++){
			sum += Integer.parseInt(contents.get(i).get(columnIndex));
		}
		return sum;
	}
	
	public int getColumnSumByHeader(String header){
		int index = -1;
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(header))
				index = i;
		}
		
		if (index == -1) return HEADER_NOT_FOUND;
		else return getColumnSum(index);
		
	}
	
	public double getColumnAverage(int columnIndex){
		int sum = getColumnSum(columnIndex);
		double average = sum / (double) contents.size();
		return average;
	}

	public Vector<String> getHeaders() {
		return headers;
	}

	public Vector<Vector<String>> getContents() {
		return contents;
	}
	
	
}
