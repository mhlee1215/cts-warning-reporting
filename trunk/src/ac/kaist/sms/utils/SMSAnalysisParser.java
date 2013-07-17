package ac.kaist.sms.utils;

import ac.kaist.sms.model.SMSAnalysisDocumentModel;
import ac.kaist.sms.model.SMSAnalysisSheetModel;
import jxl.*; 
import jxl.read.biff.BiffException;

/**
 * @author mh.lee
 * 
 * 사용하기 용의하게 저장된 xls데이터를 이용해 보다 쉽게 
 * 원하는 데이터를 얻기 위한 함수가 포함되어 있습니다.
 * 예) 열의 합, 평균 등 
 *
 */

public class SMSAnalysisParser {
	private SMSAnalysisDocumentModel _doc_model;
	
	public SMSAnalysisParser(Workbook workbook){
		_doc_model = new SMSAnalysisDocumentModel(workbook);
	}

	public SMSAnalysisDocumentModel get_doc_model() {
		return _doc_model;
	}

	public void set_doc_model(SMSAnalysisDocumentModel _doc_model) {
		this._doc_model = _doc_model;
	}
	
	public SMSAnalysisSheetModel getSheetByName(String sheetName){
	
		int sheet_index = _doc_model.findSheetModelByName(sheetName);
		SMSAnalysisSheetModel sheetModel = _doc_model.getSheetModelByIndex(sheet_index);
		
		return sheetModel;
	}

}
