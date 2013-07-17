package ac.kaist.sms.model;

import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @author mh.lee
 * 
 * ��Ʊ������ xls������ �м��Ͽ� ����ϱ� ���� �����ͷ� �����մϴ�.
 *
 */
public class SMSAnalysisDocumentModel {
	private Vector<String> sheetTitles;
	private Vector<SMSAnalysisSheetModel> doc_contents;

	public SMSAnalysisDocumentModel(Workbook workbook) {
		// TODO Auto-generated constructor stub
		sheetTitles = new Vector<String>();
		doc_contents = new Vector<SMSAnalysisSheetModel>();
		
		for(int i = 0 ; i < workbook.getSheets().length ; i++){
			sheetTitles.add(workbook.getSheets()[i].getName());
			Sheet sheet = workbook.getSheets()[i];
			doc_contents.add(new SMSAnalysisSheetModel(sheet));
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
	
	public SMSAnalysisSheetModel getSheetModelByIndex(int index){
		return doc_contents.get(index);
	}

}
