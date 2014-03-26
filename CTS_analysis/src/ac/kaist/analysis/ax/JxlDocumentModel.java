package ac.kaist.analysis.ax;

import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @author mh.lee
 * 
 * 
 *
 */
public class JxlDocumentModel {
	private Vector<String> sheetTitles;
	private Vector<JxlSheetModel> doc_contents;

	public JxlDocumentModel(Workbook workbook) {
		// TODO Auto-generated constructor stub
		sheetTitles = new Vector<String>();
		doc_contents = new Vector<JxlSheetModel>();
		
		for(int i = 0 ; i < workbook.getSheets().length ; i++){
			sheetTitles.add(workbook.getSheets()[i].getName());
			Sheet sheet = workbook.getSheets()[i];
			doc_contents.add(new JxlSheetModel(sheet));
		}
	}
	
	public String toString(){
		String resultStr = "";
		
		for(int i = 0 ; i < doc_contents.size(); i++){
			resultStr += "<<"+sheetTitles.get(i)+">>\n";
			resultStr += doc_contents.get(i).toString();
			resultStr += "\n\n";
		}
		
		return resultStr;
	}
	
	public int findSheetModelByName(String name){
		for(int i = 0 ; i < sheetTitles.size() ; i++){
			if( sheetTitles.get(i).trim().compareToIgnoreCase(name) == 0)
				return i;
		}
		return -1;
	}
	
	public JxlSheetModel getSheetModelByIndex(int index){
		return doc_contents.get(index);
	}

}
