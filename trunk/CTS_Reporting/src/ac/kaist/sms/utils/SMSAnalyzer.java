package ac.kaist.sms.utils;

import ac.kaist.sms.model.SMSAnalysisDocumentModel;

import ac.kaist.sms.model.SMSAnalysisSheetModel;
import jxl.*; 

/**
 * @author mh.lee
 * 
 * 실제로 우리가 원하는 결과갑시들을 계산하는 로직 부분이 포함되어 있습니다.
 *
 */
public class SMSAnalyzer {
	//private Workbook _workbook;
	private SMSAnalysisDocumentModel contents;
	private SMSAnalysisResults results;
	private SMSAnalysisParser _parser;
	
	public SMSAnalyzer(Workbook workbook){
		results = new SMSAnalysisResults();
		_parser = new SMSAnalysisParser(workbook);
	}
	
	public SMSAnalysisResults Analysis(){
		//Volunteer report count
		SMSAnalysisSheetModel vrModel = _parser.getSheetByName("자율보고 report");
		results.setVolunteerReportCount(vrModel.getColumnSumByHeader("incident"));
		
		//Survey count
		SMSAnalysisSheetModel sModel = _parser.getSheetByName("Survey");
		results.setServeyCount(sModel.getColumnSumByHeader("참여자 수"));
		
		//Survey Volunteer count
		results.setServeyVolunteerCount(sModel.getColumnSumByHeader("보고 숫자"));
		
		//Frequency of occurrences (Not directly connected. Skip)
		
		//Accident/serious incident/incident frequency 
		SMSAnalysisSheetModel nrModel = _parser.getSheetByName("의무보고 report");
		
		//Very important indicator frequency 
		
		//Average important indicator frequency 
		
		//Initial/residual risk 
		
		//Corrective action 
		
		//Hazard Count
		SMSAnalysisSheetModel hzModel = _parser.getSheetByName("Hazard report");
		results.setHazardCount(hzModel.getColumnSumByHeader("hazard 숫자"));
		
		//Accident trend 
		
		//Serious incident trend 
		
		//Incident trend 
		
		//Very important indicator trend 
		
		//Average important indicator trend 
		
		//Top/bottom five occurrence 
		
		
		
		//Internal audit count
		SMSAnalysisSheetModel iaModel = _parser.getSheetByName("Internal audit report");
		results.setInternalAuditCount(iaModel.getColumnSumByHeader("Audit NO"));
		
		//Internal audit finding  count
		results.setInternalAuditFindingCount(iaModel.getColumnSumByHeader("Finding NO"));
		
		//External audit count
		SMSAnalysisSheetModel eaModel = _parser.getSheetByName("External audit report");
		results.setExternalAuditCount(eaModel.getColumnSumByHeader("Audit NO"));
		
		//External audit finding count
		results.setExternalAuditFindingCount(eaModel.getColumnSumByHeader("Finding NO"));
		
		
		//Initial/residual risk 
		
		//Corrective action 
		
		
		//Effectiveness of safety report
		
		//Effectiveness of internal audit 
		
		//Effectiveness of external audit 
		
		//Effectiveness of internal investigation 
		
		//Effectiveness of external investigation 
		
		
		
		//Internal investigation count
		SMSAnalysisSheetModel iiModel = _parser.getSheetByName("Internal investigation report");
		results.setInternalInvestigationCount(iiModel.getColumnSumByHeader("Investigation NO"));
		
		//Internal investigation finding count
		results.setInternalInvestigationFindingCount(iiModel.getColumnSumByHeader("Finding NO"));
		
		//External investigation count
		SMSAnalysisSheetModel eiModel = _parser.getSheetByName("External investigation report");
		results.setExternalInvestigationCount(eiModel.getColumnSumByHeader("Investigation NO"));
		
		//External investigation finding
		results.setExternalInvestigationFindingCount(iiModel.getColumnSumByHeader("Finding NO"));
		
		//Continuation courese (Skipped)
		//Recurrent training (Skipped)
		
		//Traning Score
		SMSAnalysisSheetModel trModel = _parser.getSheetByName("Training Report");
		results.setTrainingScore(trModel.getColumnAverage(2));
		
		return results;
	}

	

	public SMSAnalysisDocumentModel getContents() {
		return _parser.get_doc_model();
	}

	public SMSAnalysisResults getResults() {
		return results;
	}
	
	
	
	
}
