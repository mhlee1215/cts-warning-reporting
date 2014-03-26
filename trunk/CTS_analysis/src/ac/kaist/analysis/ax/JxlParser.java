package ac.kaist.analysis.ax;

import ac.kaist.analysis.ax.JxlDocumentModel;
import ac.kaist.analysis.ax.JxlSheetModel;
import jxl.*; 

/**
 * @author mh.lee
 * 
 *  *
 */

public class JxlParser {
	private JxlDocumentModel _doc_model;
	
	public JxlParser(Workbook workbook){
		_doc_model = new JxlDocumentModel(workbook);
	}

	public JxlDocumentModel get_doc_model() {
		return _doc_model;
	}

	public void set_doc_model(JxlDocumentModel _doc_model) {
		this._doc_model = _doc_model;
	}
	
	public JxlSheetModel getSheetByName(String sheetName){
	
		int sheet_index = _doc_model.findSheetModelByName(sheetName);
		JxlSheetModel sheetModel = _doc_model.getSheetModelByIndex(sheet_index);
		
		return sheetModel;
	}

}
