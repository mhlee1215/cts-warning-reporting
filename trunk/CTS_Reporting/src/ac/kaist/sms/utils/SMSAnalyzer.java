package ac.kaist.sms.utils;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.joda.time.LocalDate;

import ac.kaist.sms.model.SMSAnalysisDocumentModel;
import ac.kaist.sms.model.SMSAnalysisResultAudit;
import ac.kaist.sms.model.SMSAnalysisResultClassification;
import ac.kaist.sms.model.SMSAnalysisResultEffectivenessAnalysis;
import ac.kaist.sms.model.SMSAnalysisResultHazard;
import ac.kaist.sms.model.SMSAnalysisResultInvestigation;
import ac.kaist.sms.model.SMSAnalysisResultMonitoringAndTrendAnalysis;
import ac.kaist.sms.model.SMSAnalysisResultSafetyReport;
import ac.kaist.sms.model.SMSAnalysisResultTraining;
import ac.kaist.sms.model.SMSAnalysisResults;

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
		Vector<String> frequencyTypes = new Vector<String>();
		frequencyTypes.add("accident");
		frequencyTypes.add("serious incident");
		frequencyTypes.add("incident");
		
		SMSAnalysisSheetModel nrModel = _parser.getSheetByName("의무보고 report");
		Map<String, Map<String, Integer> > nrDataSum = new TreeMap<String, Map<String, Integer> >();
		
		for(String fType : frequencyTypes){
			nrDataSum.put(fType, nrModel.getDateColumnSum("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth));
		}
		
		System.out.println("nrDataSum :"+nrDataSum);
		
		//Very important indicator frequency 
		//Get very important abbreviation
		SMSAnalysisSheetModel acModel = _parser.getSheetByName("00accident code");
		Vector<String> viCodes = acModel.getDataByCondition("Abbreviation", "importance", "very importance");
		System.out.println(viCodes);
		Map<String, Map<String, Map<String, Integer> > > nrVICodeBasedDataSum = new TreeMap<String, Map<String, Map<String, Integer> > >();
		for (String code : viCodes){
			Map<String, Map<String, Integer> > resultBean = new TreeMap<String, Map<String, Integer> >();
			nrVICodeBasedDataSum.put(code, resultBean);
			for(String fType : frequencyTypes){
				nrVICodeBasedDataSum.get(code).put(fType, nrModel.getDateColumnSumByCondition("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth, "type", code));
			}	
		}
		System.out.println("nrVICodeBasedDataSum :"+nrVICodeBasedDataSum);
		
		//Average important indicator frequency 
		Vector<String> aiCodes = acModel.getDataByCondition("Abbreviation", "importance", "average importance");
		System.out.println(aiCodes);
		Map<String, Map<String, Map<String, Integer> > > nrAICodeBasedDataSum = new TreeMap<String, Map<String, Map<String, Integer> > >();
		for (String code : aiCodes){
			Map<String, Map<String, Integer> > resultBean = new TreeMap<String, Map<String, Integer> >();
			nrAICodeBasedDataSum.put(code, resultBean);
			for(String fType : frequencyTypes){
				nrAICodeBasedDataSum.get(code).put(fType, nrModel.getDateColumnSumByCondition("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth, "type", code));
			}	
		}
		System.out.println("nrAICodeBasedDataSum :"+nrAICodeBasedDataSum);
		
		//Initial/residual risk 
		//column used as intermediate result (skip)
		
		//Corrective action 
		//action field for categorization (skip)
		
		//Hazard Count
		SMSAnalysisSheetModel hzModel = _parser.getSheetByName("Hazard report");
		results.setHazardCount(hzModel.getColumnSumByHeader("hazard 숫자"));
		
		//Accident trend 
		//Serious incident trend 
		//Incident trend 
		LocalDate today = new LocalDate("2008-02-10");
		//System.out.println("today?"+today);
		int recentMonthInterval = 4;
		Vector<String> recentMonthsList = new Vector<String>();
		for(int i = 0 ; i < recentMonthInterval ; i++){
			String monthStr = SMSAnalysisDateUtil.cutDateStr(today, SMSAnalysisDateUtil.dateTypeMonth);
			recentMonthsList.add(monthStr);
			today = today.plusMonths(-1);
		}
		//System.out.println("recentMonthsList : "+recentMonthsList);
		
		TreeMap<String, Double> trends = new TreeMap<String, Double>();
		for(String fType : frequencyTypes){
			Double average = 0.0;
			for(String monthStr : recentMonthsList){
				if(nrDataSum.get(fType) != null){
					Integer value = nrDataSum.get(fType).get(monthStr);
					if(value != null){
						average += nrDataSum.get(fType).get(monthStr);
					}
				}
			}
			average /= recentMonthInterval;
			trends.put(fType, average);
			//nrDataSum.put(fType, nrModel.getDateColumnSum("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth));
		}
		System.out.println("trends : "+trends);
		
		
		//Very important indicator trend 
		TreeMap<String, Map<String, Double> > viTrends = new TreeMap<String, Map<String, Double> >();
		
		for (String code : viCodes){
			viTrends.put(code, new TreeMap<String, Double>());
			if (nrVICodeBasedDataSum.get(code) != null){
				for(String fType : frequencyTypes){
					Double average = 0.0;
					for(String monthStr : recentMonthsList){
						if(nrVICodeBasedDataSum.get(code).get(fType) != null){
							Integer value = nrVICodeBasedDataSum.get(code).get(fType).get(monthStr);
							if(value != null){
								average += nrVICodeBasedDataSum.get(code).get(fType).get(monthStr);
							}
						}
					}
					average /= recentMonthInterval;
					viTrends.get(code).put(fType, average);
				}
			}
		}
		System.out.println("viTrends : "+viTrends);
		
		//Average important indicator trend 
		TreeMap<String, Map<String, Double> > aiTrends = new TreeMap<String, Map<String, Double> >();
		
		for (String code : aiCodes){
			aiTrends.put(code, new TreeMap<String, Double>());
			if (nrAICodeBasedDataSum.get(code) != null){
				for(String fType : frequencyTypes){
					Double average = 0.0;
					for(String monthStr : recentMonthsList){
						if(nrAICodeBasedDataSum.get(code).get(fType) != null){
							Integer value = nrAICodeBasedDataSum.get(code).get(fType).get(monthStr);
							if(value != null){
								average += nrAICodeBasedDataSum.get(code).get(fType).get(monthStr);
							}
						}
					}
					average /= recentMonthInterval;
					aiTrends.get(code).put(fType, average);
				}
			}
		}
		System.out.println("aiTrends : "+aiTrends);
		
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

	
	public SMSAnalysisResultSafetyReport SafetyReport(SMSAnalysisSheetModel nrSheet, SMSAnalysisSheetModel frSheet, SMSAnalysisSheetModel surveySheet){
		return null;
	}
	
	public SMSAnalysisResultHazard Hazard(SMSAnalysisSheetModel hazardSheet){
		return null;
	}
	
	public SMSAnalysisResultAudit Audit(SMSAnalysisSheetModel iaReport, SMSAnalysisSheetModel eaReport){
		return null;
	}
	
	public SMSAnalysisResultClassification Classification(Map<String, Integer> frequencyOfOccurrence, Map<String, String> iCode, Map<String, String> oCode, Map<String, String> asCode ){
		return null;
	}
	
	public SMSAnalysisResultInvestigation Investigation(SMSAnalysisSheetModel iiReport, SMSAnalysisSheetModel eiReport){
		return null;
	}
	
	public SMSAnalysisResultTraining Training(SMSAnalysisSheetModel tReport){
		return null;
	}
	
	public SMSAnalysisResultMonitoringAndTrendAnalysis MonitoringAndTrendAnalysis(){
		return null;
	}
	
	public SMSAnalysisResultEffectivenessAnalysis EffectivenessAnalysis(){
		return null;
	}
	 
	

	public SMSAnalysisDocumentModel getContents() {
		return _parser.get_doc_model();
	}

	public SMSAnalysisResults getResults() {
		return results;
	}
	
	
	
	
}
