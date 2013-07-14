package ac.kaist.sms.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
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
import ac.kaist.sms.model.ValueCompAsc;

import java.util.Map.Entry;
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
	
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValuesDsc(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                return -e1.getValue().compareTo(e2.getValue());
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValuesAsc(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                return e1.getValue().compareTo(e2.getValue());
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	public static Comparator<Map.Entry<String, Double>> doubleCompAsc = new Comparator<Map.Entry<String, Double>>() {
	    @Override
	    public int compare(Map.Entry date1, Map.Entry date2) {
	    	Double d1 = (Double)date1.getValue();
	    	Double d2 = (Double)date2.getValue();
	        return d1 > d2 ? 1 : -1; 
	    }
	};
	
	public static Comparator<Double> doubleCompDsc = new Comparator<Double>() {
	    @Override
	    public int compare(Double date1, Double date2) {
	        return date1 > date2 ? -1 : 1; 
	    }
	};
	
	public SMSAnalyzer(Workbook workbook){
		results = new SMSAnalysisResults();
		_parser = new SMSAnalysisParser(workbook);
	}
	
	public SMSAnalysisResults Analysis(){
		//안전보고 A1
		SMSAnalysisSheetModel nrSheet = _parser.getSheetByName("의무보고 report");
		SMSAnalysisSheetModel vrSheet = _parser.getSheetByName("자율보고 report");
		SMSAnalysisSheetModel surveySheet = _parser.getSheetByName("Survey");
		SMSAnalysisResultSafetyReport safetyReportResult = SafetyReport(nrSheet, vrSheet, surveySheet);
		results.setSafetyReportResult(safetyReportResult);
		
		//Hazard A2
		SMSAnalysisSheetModel hzSheet = _parser.getSheetByName("Hazard report");
		SMSAnalysisResultHazard hazardResult = Hazard(hzSheet);
		results.setHazardResult(hazardResult);
		
		//Audit A3
		SMSAnalysisSheetModel iaSheet = _parser.getSheetByName("Internal audit report");
		SMSAnalysisSheetModel eaSheet = _parser.getSheetByName("External audit report");
		SMSAnalysisResultAudit auditResult = Audit(iaSheet, eaSheet);
		results.setAuditResult(auditResult);
		
		//Investigation A4
		SMSAnalysisSheetModel iiSheet = _parser.getSheetByName("internal investigation report");
		SMSAnalysisSheetModel eiSheet = _parser.getSheetByName("external investigation report");
		SMSAnalysisResultInvestigation investigationResult = Investigation(iiSheet, eiSheet);
		results.setInvestigationResult(investigationResult);
		
		//Training A5
		SMSAnalysisSheetModel trSheet = _parser.getSheetByName("Training Report");
		SMSAnalysisResultTraining trainingResult = Training(trSheet);
		results.setTrainingResult(trainingResult);
		
		//Classification B3
		SMSAnalysisSheetModel acSheet = _parser.getSheetByName("00accident code");
		SMSAnalysisResultClassification classificationResult = Classification(safetyReportResult, acSheet);
		results.setClassificationResult(classificationResult);
		
		//Monitoring and trend analysis B1
		SMSAnalysisSheetModel tgSheet = _parser.getSheetByName("Target level");
		SMSAnalysisSheetModel alSheet = _parser.getSheetByName("Alert level");
		SMSAnalysisResultMonitoringAndTrendAnalysis monitoringAndTrendAnalysisResult = MonitoringAndTrendAnalysis(classificationResult, tgSheet, alSheet);
		results.setMonitoringAndTrendAnalysisResult(monitoringAndTrendAnalysisResult);
		
		//Effectiveness analysis B2
		SMSAnalysisResultEffectivenessAnalysis effectivenessAnalysisResult = EffectivenessAnalysis(classificationResult, auditResult, investigationResult);
		results.setEffectivenessAnalysisResult(effectivenessAnalysisResult);
		
		return results;
	}

	//안전보고 메서드 (A1)
	//nrSheet : 의무보고 report
	//vrSheet : 자율보고 report
	//surveySheet : Survey
	public SMSAnalysisResultSafetyReport SafetyReport(SMSAnalysisSheetModel nrSheet, SMSAnalysisSheetModel vrSheet, SMSAnalysisSheetModel surveySheet){
		SMSAnalysisResultSafetyReport result = new SMSAnalysisResultSafetyReport();
		
		//자율보고 횟수
		result.setVolunteerReportCount(vrSheet.getColumnSumByHeader("incident"));
		
		//Survey 횟수
		result.setSurveyCount(surveySheet.getColumnSumByHeader("참여자 수"));
		
		//Survey 참여자 수
		result.setSurveyParticipantCount(surveySheet.getColumnSumByHeader("보고 숫자"));
		
		//Frequency of occurrences
		result.setNrSheet(nrSheet);
		result.setVrSheet(vrSheet);
		result.setSurveySheet(surveySheet);
		
		return result;
	}
	
	//Hazard 메서드 (A2)
	//hzSheet : 하자드 report
	public SMSAnalysisResultHazard Hazard(SMSAnalysisSheetModel hzSheet){
		SMSAnalysisResultHazard result = new SMSAnalysisResultHazard();
		result.setHazardOcurrenceCount(hzSheet.getColumnSumByHeader("hazard 숫자"));
		return result;
	}
	
	//Audit 메서드 (A3)
	public SMSAnalysisResultAudit Audit(SMSAnalysisSheetModel iaSheet, SMSAnalysisSheetModel eaSheet){
		SMSAnalysisResultAudit result = new SMSAnalysisResultAudit();
		//Internal audit count
		result.setInternalAuditCount(iaSheet.getColumnSumByHeader("Audit NO"));
		//Internal audit finding  count
		result.setInternalAuditFindingCount(iaSheet.getColumnSumByHeader("Finding NO"));
		//External audit count
		result.setExternalAuditCount(eaSheet.getColumnSumByHeader("Audit NO"));
		//External audit finding count
		result.setExternalAuditFindingCount(eaSheet.getColumnSumByHeader("Finding NO"));
		
		//Initial / residual risk
		//corrective action
		result.setIaSheet(iaSheet);
		result.setEaSheet(eaSheet);
		
		return result;
	}
	
	//Investigation 메서드 (A4)
	public SMSAnalysisResultInvestigation Investigation(SMSAnalysisSheetModel iiSheet, SMSAnalysisSheetModel eiSheet){
		SMSAnalysisResultInvestigation result = new SMSAnalysisResultInvestigation();
		
		//Internal investigation count
		result.setInternalInvestigationCount(iiSheet.getColumnSumByHeader("Investigation NO"));
		//Internal investigation finding count
		result.setInternalInvestigationFindingCount(iiSheet.getColumnSumByHeader("Finding NO"));
		//External investigation count
		result.setExternalInvestigationCount(eiSheet.getColumnSumByHeader("Investigation NO"));
		//External investigation finding
		result.setExternalInvestigationFindingCount(eiSheet.getColumnSumByHeader("Finding NO"));
		
		//Initial/residual risk
		//Corrective action
		result.setIiSheet(iiSheet);
		result.setEiSheet(eiSheet);
		
		return result;
	}
	
	//Training 메서드 (A5)
	public SMSAnalysisResultTraining Training(SMSAnalysisSheetModel tSheet){
		SMSAnalysisResultTraining result = new SMSAnalysisResultTraining();
		result.setTrainingScore(tSheet.getColumnAverage("평균 점수"));
		return result;
	}
	
	//Monitoring and Trend analysis 메서드 (B1)
	public SMSAnalysisResultMonitoringAndTrendAnalysis MonitoringAndTrendAnalysis(SMSAnalysisResultClassification classificationResult, SMSAnalysisSheetModel tgSheet, SMSAnalysisSheetModel alSheet){
		SMSAnalysisResultMonitoringAndTrendAnalysis result = new SMSAnalysisResultMonitoringAndTrendAnalysis();
		
		SMSAnalysisSheetModel nrSheet = classificationResult.getSafetyReportResult().getNrSheet();
		SMSAnalysisSheetModel vrSheet = classificationResult.getSafetyReportResult().getVrSheet();
		Map<String, Map<String, Integer> > nrDataSum = classificationResult.getFrequency();
		Vector<String> viCodes = classificationResult.getViCodes();
		Vector<String> aiCodes = classificationResult.getAiCodes();
		Vector<String> frequencyTypes = classificationResult.getFrequencyTypes();
		Map<String, Map<String, Map<String, Integer> > > nrVICodeBasedDataSum = classificationResult.getVeryImportantFrequency();
		Map<String, Map<String, Map<String, Integer> > > nrAICodeBasedDataSum = classificationResult.getAverageFrequency();
		
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
		//System.out.println("trends : "+trends);
		result.setTrends(trends);
		
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
		//System.out.println("viTrends : "+viTrends);
		result.setVeryImportantTrends(viTrends);
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
		//System.out.println("aiTrends : "+aiTrends);
		result.setAverageImportantTrends(aiTrends);
		
		//Top/bottom five occurrence (자율보고) 
		Map<String, Map<String, Vector<String> > > vrOcurrence = getOcurrence(vrSheet, tgSheet, alSheet);
		result.setVrTopOccurrence(vrOcurrence.get("top"));
		result.setVrBottomOccurrence(vrOcurrence.get("bottom"));
		
		//Top/bottom five occurrence (의무보고) 
		Map<String, Map<String, Vector<String> > > nrOcurrence = getOcurrence(nrSheet, tgSheet, alSheet);
		result.setNrTopOccurrence(nrOcurrence.get("top"));
		result.setNrBottomOccurrence(nrOcurrence.get("bottom"));
		
		return result;
	}
	
	//Effectivenss analysis (B2)
	public SMSAnalysisResultEffectivenessAnalysis EffectivenessAnalysis(SMSAnalysisResultClassification classificationResult, SMSAnalysisResultAudit auditResult, SMSAnalysisResultInvestigation investigationResult){
		SMSAnalysisResultEffectivenessAnalysis result = new SMSAnalysisResultEffectivenessAnalysis();
		
		SMSAnalysisSheetModel nrSheet = classificationResult.getSafetyReportResult().getNrSheet();
		SMSAnalysisSheetModel iaSheet = auditResult.getIaSheet();
		SMSAnalysisSheetModel eaSheet = auditResult.getEaSheet();
		SMSAnalysisSheetModel iiSheet = investigationResult.getIiSheet();
		SMSAnalysisSheetModel eiSheet = investigationResult.getEiSheet();
		
		//Effectiveness of safety report
		result.setEffectivenssOfSafetyReport(nrSheet.getEffectiveness("날짜", "initial risk", "Resitual risk", "action"));
		//Effectiveness of internal audit 
		result.setEffectivenssOfInternalAudit(iaSheet.getEffectiveness("날짜", "initial risk", "Resitual risk", "action"));
		//Effectiveness of external audit
		result.setEffectivenssOfExternalAudit(eaSheet.getEffectiveness("날짜", "initial risk", "Resitual risk", "action"));
		//Effectiveness of internal investigation
		result.setEffectivenssOfInternalInvestigation(iiSheet.getEffectiveness("날짜", "initial risk", "Resitual risk", "action"));
		//Effectiveness of external investigation
		result.setEffectivenssOfExternalInvestigation(eiSheet.getEffectiveness("날짜", "initial risk", "Resitual risk", "action"));
		
		return result;
	}
	
	//Classification 메서드 (B3)
	public SMSAnalysisResultClassification Classification(SMSAnalysisResultSafetyReport safetyReportResult, SMSAnalysisSheetModel acSheet){
		SMSAnalysisResultClassification result = new SMSAnalysisResultClassification();
		SMSAnalysisSheetModel nrSheet = safetyReportResult.getNrSheet();
		
		Vector<String> frequencyCodes = new Vector<String>();
		frequencyCodes.add("accident");
		frequencyCodes.add("serious incident");
		frequencyCodes.add("incident");
		
		result.setFrequencyTypes(frequencyCodes);
		Map<String, Map<String, Integer> > nrDataSum = new TreeMap<String, Map<String, Integer> >();
		
		for(String fType : frequencyCodes){
			nrDataSum.put(fType, nrSheet.getDateColumnSum("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth));
		}
		
		result.setFrequency(nrDataSum);
			
		//Very important indicator frequency 
		//Get very important abbreviation
		SMSAnalysisSheetModel acModel = _parser.getSheetByName("00accident code");
		Vector<String> viCodes = acModel.getDataByCondition("Abbreviation", "importance", "very importance");
		//System.out.println(viCodes);
		result.setViCodes(viCodes);
		Map<String, Map<String, Map<String, Integer> > > nrVICodeBasedDataSum = new TreeMap<String, Map<String, Map<String, Integer> > >();
		for (String code : viCodes){
			Map<String, Map<String, Integer> > resultBean = new TreeMap<String, Map<String, Integer> >();
			nrVICodeBasedDataSum.put(code, resultBean);
			for(String fType : frequencyCodes){
				nrVICodeBasedDataSum.get(code).put(fType, nrSheet.getDateColumnSumByCondition("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth, "type", code));
			}	
		}
		result.setVeryImportantFrequency(nrVICodeBasedDataSum);
		
		//Average important indicator frequency 
		Vector<String> aiCodes = acModel.getDataByCondition("Abbreviation", "importance", "average importance");
		//System.out.println(aiCodes);
		result.setAiCodes(aiCodes);
		Map<String, Map<String, Map<String, Integer> > > nrAICodeBasedDataSum = new TreeMap<String, Map<String, Map<String, Integer> > >();
		for (String code : aiCodes){
			Map<String, Map<String, Integer> > resultBean = new TreeMap<String, Map<String, Integer> >();
			nrAICodeBasedDataSum.put(code, resultBean);
			for(String fType : frequencyCodes){
				nrAICodeBasedDataSum.get(code).put(fType, nrSheet.getDateColumnSumByCondition("날짜", fType, SMSAnalysisDateUtil.dateTypeMonth, "type", code));
			}	
		}

		result.setAverageFrequency(nrAICodeBasedDataSum);
		result.setSafetyReportResult(safetyReportResult);
		
		return result;
	}
	 
	

	public SMSAnalysisDocumentModel getContents() {
		return _parser.get_doc_model();
	}

	public SMSAnalysisResults getResults() {
		return results;
	}
	
	
	public Map<String, Map<String, Vector<String> > > getOcurrence(SMSAnalysisSheetModel reportSheet, SMSAnalysisSheetModel tgSheet, SMSAnalysisSheetModel alSheet){
		Map<String, Map<String, Vector<String> > > ocurrence = new TreeMap<String, Map<String, Vector<String> > >();
		
		Map<String, Double> averageInitialRisk = reportSheet.getDateColumnAverageByCondition("날짜", "initial risk", SMSAnalysisDateUtil.dateTypeMonth, null, null);
	
		Map<String, Double> targetCode = tgSheet.getDataMap("Occurrence code", "Target");
		Map<String, Double> alertCode = alSheet.getDataMap("Occurrence code", "Alert level");
		
		Map<String, Vector<String> > topOccurrence = new TreeMap<String, Vector<String> >();
		Map<String, Vector<String> > bottomOccurrence = new TreeMap<String, Vector<String> >();
		for(String date : averageInitialRisk.keySet()){
			topOccurrence.put(date, new Vector<String>());
			bottomOccurrence.put(date, new Vector<String>());
			Map<String, Double> targetDistanceMap = new TreeMap<String, Double>();
			Map<String, Double> alertDistanceMap = new TreeMap<String, Double>();
			for (String code : targetCode.keySet()){
				targetDistanceMap.put(code, Math.abs(targetCode.get(code) - averageInitialRisk.get(date)));
			}
			for (String code : alertCode.keySet()){
				alertDistanceMap.put(code, Math.abs(alertCode.get(code) - averageInitialRisk.get(date)));
			}
			SortedSet<Map.Entry<String, Double> > targetSortedAsc = entriesSortedByValuesAsc(targetDistanceMap);
			SortedSet<Map.Entry<String, Double> > alertSortedAsc = entriesSortedByValuesAsc(alertDistanceMap);
			
		
			for(int i = 0 ; i < 5 ; i++){
				Map.Entry<String, Double> a = (Entry<String, Double>) targetSortedAsc.toArray()[i];
				topOccurrence.get(date).add(a.getKey());
			}
			
			for(int i = 0 ; i < 5 ; i++){
				Map.Entry<String, Double> a = (Entry<String, Double>) alertSortedAsc.toArray()[i];
				bottomOccurrence.get(date).add(a.getKey());
			}
		}
		
		ocurrence.put("top", topOccurrence);
		ocurrence.put("bottom", bottomOccurrence);
		
		return ocurrence;
	}	
}