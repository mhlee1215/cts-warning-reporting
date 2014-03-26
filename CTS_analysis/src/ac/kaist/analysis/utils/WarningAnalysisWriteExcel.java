package ac.kaist.analysis.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import ac.kaist.analysis.WarningAnalyzer.likelihoodDesc;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;

public class WarningAnalysisWriteExcel {
	WritableWorkbook workbook_out;
	WarningAnalysisInputData waInputData;
	WarningAnalysisResultData waResultData;
	
	public WarningAnalysisWriteExcel(String path, WarningAnalysisInputData waInputData, WarningAnalysisResultData waResultData) throws IOException{
		workbook_out = Workbook.createWorkbook(new File(path));
		this.waInputData = waInputData;
		this.waResultData = waResultData;
	}
	
	public void write() throws WriteException, IOException{
		
		int depth = waInputData.getDepth();
		Set<String> sEv_id = waInputData.getsEv_id(); //UNIQUE
		Vector<String> vEv_id = waInputData.getvEv_id();//ALL
		
		Set<String> sSubsection = waInputData.getsSubsection();	//UNIQUE
		Vector<String> vSubsection = waInputData.getvSubsection();//ALL
		
		Vector<String> sInjury = waInputData.getsInjury();	//UNIQUE 
		Vector<String> vInjury = waInputData.getvInjury();	//ALL
		
		Vector<String> sDamage = waInputData.getsDamage();			//UNIQUE
		Vector<String> vDamage = waInputData.getvDamage();			//ALL
				
		//Assign value for label
		Map<String, Integer> damageValueMap = waInputData.getDamageValueMap();
		Map<String, Integer> InjuryValueMap = waInputData.getInjuryValueMap();
		Map<Integer, String> LevelValueMap = waInputData.getLevelValueMap();
		
		//Unique describe Id for retrieval
		Set<String> sDesc = waInputData.getsDesc();
		//Describe ID for saving
		Vector<String> vDesc = waInputData.getvDesc();
		
		
		int totalDeparture = waResultData.getTotalDeparture();
		Map<String, Map<String, Integer> > occurrenceMatrix = waResultData.getOccurrenceMatrix();
		Map<String, Map<String, Integer> > personalInjuryMatrix = waResultData.getPersonalInjuryMatrix();
		Map<String, Map<String, Integer> > highestInjuryMatrix = waResultData.getHighestInjuryMatrix();
		Map<String, Map<String, Integer> > aircraftDamageMatrix = waResultData.getAircraftDamageMatrix();
		Map<String, Float> injuryMillion = waResultData.getInjuryMillion();
		Map<String, Float> damageMillion = waResultData.getDamageMillion();
		
		Map<String, Map<String, Integer> > aircraftDamageDescMatrix = waResultData.getAircraftDamageDescMatrix();
		Map<String, Map<String, Integer> > InjuryLevelDescMatrix = waResultData.getInjuryLevelDescMatrix();
		Map<String, Map<Integer, Integer> > ABCDEDescMatrix = waResultData.getABCDEDescMatrix();
		//Save worst severity
		Map<String, Integer> worstSeverityMap = waResultData.getWorstSeverityMap();
		//Save most severity
		Map<String, Integer> mostSeverityMap = waResultData.getMostSeverityMap();
		//Likelihood map from description to likelihood
		Map<String, Float> likelihoodMap = waResultData.getLikelihoodMap();
		//For saving 5-label severity
		Map<String, Integer> likelihoodQuantizedMap = waResultData.getLikelihoodQuantizedMap();
		//Inverse Likelihood map for likelihood sorting
		ArrayList<likelihoodDesc> likelihoodList = waResultData.getLikelihoodList();
		
		String today = waResultData.getToday();
		Map<String, Integer> todayWorstSeverity = waResultData.getTodayWorstSeverity();
		Map<String, Float> MFDescMatrix = waResultData.getMFDescMatrix();
		
		Map<String, Float> injuryMillionDesc = waResultData.getInjuryMillionDescMatrix();
        Map<String, Float> damageMillionDesc = waResultData.getDamageMillionDescMatrix();
		
		
		workbook_out.createSheet("Event Table", 0);
		workbook_out.createSheet("Risk Assement", 1);
		
		WritableCellFormat cellFormatLeft = new WritableCellFormat();
		cellFormatLeft.setAlignment(Alignment.LEFT);
		WritableCellFormat cellFormatCenter = new WritableCellFormat();
		cellFormatCenter.setAlignment(Alignment.CENTRE);
		//cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		WritableCellFormat cellFormatPink = new WritableCellFormat();
		cellFormatPink.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormatPink.setAlignment(Alignment.CENTRE);
		cellFormatPink.setBackground(Colour.PINK2);
		WritableCellFormat cellFormatGreen = new WritableCellFormat();		
		cellFormatGreen.setBackground(Colour.GREEN);
		cellFormatGreen.setAlignment(Alignment.CENTRE);
		WritableCellFormat cellFormatGreen1 = new WritableCellFormat();		
		cellFormatGreen1.setBackground(Colour.LIGHT_GREEN);
		cellFormatGreen1.setAlignment(Alignment.CENTRE);
		WritableCellFormat cellFormatRed = new WritableCellFormat();		
		cellFormatRed.setBackground(Colour.RED);
		cellFormatRed.setAlignment(Alignment.CENTRE);
		WritableCellFormat cellFormatBlue = new WritableCellFormat();		
		cellFormatBlue.setBackground(Colour.BLUE);
		cellFormatBlue.setAlignment(Alignment.CENTRE);
		WritableCellFormat cellFormatRed1 = new WritableCellFormat();		
		cellFormatRed1.setBackground(Colour.ROSE);
		cellFormatRed1.setAlignment(Alignment.CENTRE);
		WritableCellFormat cellFormatBlue1 = new WritableCellFormat();		
		cellFormatBlue1.setBackground(Colour.LIGHT_BLUE);
		cellFormatBlue1.setAlignment(Alignment.CENTRE);
		
		
		WritableSheet event_id_sheet = workbook_out.getSheet(0);
		
		Number number = null;
		Label label = new Label(0, 1, "Event ID", cellFormatCenter);
		event_id_sheet.addCell(label);		
		int cnt = 0;
		
		
		//Set header
		cnt = 1;
		for(String sub : sSubsection){
			label = new Label(cnt, 1, sub, cellFormatCenter);
			event_id_sheet.addCell(label);
			cnt ++;
		}
		
		
		
		cnt = 2;
		for(String ev_id : sEv_id){
			label = new Label(0, cnt, ev_id, cellFormatCenter);
			event_id_sheet.addCell(label);
			
			Map<String, Integer> m = occurrenceMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String sub : sSubsection){
				Integer occur = m.get(sub);
				if (occur != null){
					//label = new Label(cnt2, cnt, Integer.toString(occur), cellFormatPink);
					number = new Number(cnt2, cnt, occur, cellFormatPink);
					event_id_sheet.addCell(number);
				}
				cnt2++;
			}
			cnt ++;
		}
		
		int cnt3 = 1 + sSubsection.size();
		
		label = new Label(cnt3, 0, "personal injury (total)", cellFormatGreen);
		event_id_sheet.addCell(label);
		event_id_sheet.mergeCells(cnt3,  0, cnt3+3, 0);
		
		int sub_cnt=0;
		for(String a : sInjury){
			label = new Label(cnt3+sub_cnt, 1, a, cellFormatGreen);
			event_id_sheet.addCell(label);
			sub_cnt ++;
		}
		
		cnt = 2;
		for(String ev_id : sEv_id){
			Map<String, Integer> m = personalInjuryMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String a : sInjury){
				Integer b = m.get(a);
				if (b != null){
					//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatPink);
					number = new Number(cnt3+cnt2-1, cnt, b, cellFormatPink);
					event_id_sheet.addCell(number);
				}
				cnt2++;
			}
			cnt ++;
		}
		
		cnt3 += sInjury.size();
		label = new Label(cnt3, 0, "Highest injury", cellFormatGreen);
		event_id_sheet.addCell(label);
		event_id_sheet.mergeCells(cnt3,  0, cnt3+3, 0);
		
		sub_cnt=0;
		for(String a : sInjury){
			label = new Label(cnt3+sub_cnt, 1, a, cellFormatGreen);
			event_id_sheet.addCell(label);
			sub_cnt ++;
		}
		
		cnt = 2;
		for(String ev_id : sEv_id){
			
			Map<String, Integer> m = highestInjuryMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String a : sInjury){
				Integer b = m.get(a);
				if (b != null){
					//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatPink);
					number = new Number(cnt3+cnt2-1, cnt, b, cellFormatPink);
					event_id_sheet.addCell(number);
				}
				cnt2++;
			}
			cnt ++;
		}
		
		cnt3 += sInjury.size();
		WritableCellFormat cellFormatOrange = new WritableCellFormat();		
		cellFormatOrange.setBackground(Colour.ORANGE);
		cellFormatOrange.setAlignment(Alignment.CENTRE);
		label = new Label(cnt3, 0, "Aircraft damage", cellFormatOrange);
		event_id_sheet.addCell(label);
		event_id_sheet.mergeCells(cnt3,  0, cnt3+3, 0);
		
		sub_cnt=0;
		for(String a : sDamage){
			label = new Label(cnt3+sub_cnt, 1, a, cellFormatOrange);
			event_id_sheet.addCell(label);
			sub_cnt ++;
		}
		
		cnt = 2;
		for(String ev_id : sEv_id){
			
			Map<String, Integer> m = aircraftDamageMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String a : sDamage){
				Integer b = m.get(a);
				if (b != null){
					//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatPink);
					number = new Number(cnt3+cnt2-1, cnt, b, cellFormatPink);
					event_id_sheet.addCell(number);
				}
				cnt2++;
			}
			cnt ++;
		}
		
		cnt++;
		
		cnt3 += sDamage.size();
		label = new Label(cnt3, 1, "injury Million $", cellFormatOrange);
		event_id_sheet.addCell(label);
		cnt = 2;
		for(String ev_id : sEv_id){
			Float m = injuryMillion.get(ev_id);
			number = new Number(cnt3, cnt, m);
			event_id_sheet.addCell(number);
			cnt ++;
		}
		
		
		cnt3++;
		label = new Label(cnt3, 1, "damage Million $", cellFormatOrange);
		event_id_sheet.addCell(label);
		cnt = 2;
		for(String ev_id : sEv_id){
			Float m = damageMillion.get(ev_id);
			number = new Number(cnt3, cnt, m);
			event_id_sheet.addCell(number);
			cnt ++;
		}
		/***
		 * WRITE EVENT TABLE END
		 */
		
		
		/***
		 * WRITE risk assessment
		 */
		WritableSheet risk_assess_sheet = workbook_out.getSheet(1);
		
		label = new Label(0, 0, "Descriptive Factor (Depth="+Integer.toString(depth)+")", cellFormatCenter);
		risk_assess_sheet.addCell(label);	
		risk_assess_sheet.mergeCells(0, 0, 0, 1);
		
		
		//Map<String, Map<String, Integer> > aircraftDamageDescMatrix = new TreeMap<String, Map<String, Integer> >();
		//Map<String, Map<String, Integer> > InjuryLevelDescMatrix = new TreeMap<String, Map<String, Integer> >();
		//Map<String, Map<Integer, Integer> > ABCDEDescMatrix = new TreeMap<String, Map<Integer, Integer> >();
		
		cnt = 2;
		for(String a : sDesc){
			label = new Label(0, cnt, a, cellFormatLeft);
			risk_assess_sheet.addCell(label);
			cnt ++;
		}
		
		cnt3 = 1;
		label = new Label(cnt3, 0, "Aircraft damage", cellFormatRed);
		risk_assess_sheet.addCell(label);
		risk_assess_sheet.mergeCells(cnt3,  0, cnt3+3, 0);
		
		sub_cnt=0;
		for(String a : sDamage){
			label = new Label(cnt3+sub_cnt, 1, a, cellFormatRed);
			risk_assess_sheet.addCell(label);
			sub_cnt ++;
		}
		
		
		
		cnt = 2;
		for(String ev_id : sDesc){
			
			Map<String, Integer> m = aircraftDamageDescMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String a : sDamage){
				Integer b = m.get(a);
				if (b == null){
					b = 0;
				}
				
				//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatRed1);
				number = new Number(cnt3+cnt2-1, cnt, b, cellFormatRed1);
				risk_assess_sheet.addCell(number);
				
				cnt2++;
			}
			cnt ++;
		}
		
		
		
		cnt3 = 5;
		label = new Label(cnt3, 0, "Injury Level", cellFormatBlue);
		risk_assess_sheet.addCell(label);
		risk_assess_sheet.mergeCells(cnt3,  0, cnt3+3, 0);
		
		sub_cnt=0;
		for(String a : sInjury){
			label = new Label(cnt3+sub_cnt, 1, a, cellFormatBlue);
			risk_assess_sheet.addCell(label);
			sub_cnt ++;
		}
		
		cnt = 2;
		for(String ev_id : sDesc){
			
			Map<String, Integer> m = InjuryLevelDescMatrix.get(ev_id);
			//if(cnt < 10) System.out.println(m);
			int cnt2 = 1;
			for(String a : sInjury){
				Integer b = m.get(a);
				if (b == null) b = 0;
				//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatBlue1);
				number = new Number(cnt3+cnt2-1, cnt, b, cellFormatBlue1);
				risk_assess_sheet.addCell(number);
				cnt2++;
			}
			cnt ++;
		}
		
		cnt3 = 9;
		sub_cnt=0;
		for(int i=1 ; i <= 5; i++){
			label = new Label(cnt3+sub_cnt, 1, LevelValueMap.get(i), cellFormatGreen);
			risk_assess_sheet.addCell(label);
			sub_cnt ++;
		}
		
		label = new Label(cnt3+sub_cnt, 1, "Grand Total", cellFormatGreen);
		risk_assess_sheet.addCell(label);
		
		
		cnt = 2;
		for(String a : sDesc){
			Map<Integer, Integer> m = ABCDEDescMatrix.get(a);
			int cnt2 = 1;
			Integer grandTot = 0;
			for(int i = 1 ; i <= 5 ; i++){
				Integer b = m.get(i);
				if (b == null) b = 0;
				
				grandTot += b;
				//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(b), cellFormatBlue1);
				number = new Number(cnt3+cnt2-1, cnt, b, cellFormatGreen1);
				risk_assess_sheet.addCell(number);
				
				cnt2++;
			}
			
			//label = new Label(cnt3+cnt2-1, cnt, Integer.toString(grandTot), cellFormatBlue1);
			number = new Number(cnt3+cnt2-1, cnt, grandTot, cellFormatGreen1);
			risk_assess_sheet.addCell(number);
			
			
			
			cnt ++;
		}
		
		cnt3 = 15;
		label = new Label(cnt3-1, 0, "Total Departure", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		number = new Number(cnt3, 0, totalDeparture, cellFormatCenter);
		risk_assess_sheet.addCell(number);
		label = new Label(cnt3, 1, "Likelihood", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			float l = likelihoodMap.get(a);
			number = new Number(cnt3, cnt, l, cellFormatCenter);
			risk_assess_sheet.addCell(number);
			cnt++;
		}
		
		cnt3 = 16;
		label = new Label(cnt3, 1, "Likelihood Rank", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			float l = likelihoodQuantizedMap.get(a);
			number = new Number(cnt3, cnt, l, cellFormatCenter);
			risk_assess_sheet.addCell(number);
			cnt++;
		}
		
		cnt3 = 17;
		label = new Label(cnt3, 0, "Worst", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		label = new Label(cnt3, 1, "Severity", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			Integer l = worstSeverityMap.get(a);
			label = new Label(cnt3, cnt, LevelValueMap.get(l), cellFormatCenter);
			risk_assess_sheet.addCell(label);
			cnt++;
		}
		
		cnt3 = 18;
		label = new Label(cnt3, 0, "Most", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		label = new Label(cnt3, 1, "Severity", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			Integer l = mostSeverityMap.get(a);
			label = new Label(cnt3, cnt, LevelValueMap.get(l), cellFormatCenter);
			risk_assess_sheet.addCell(label);
			cnt++;
		}
		
		cnt3+=2;
		
		label = new Label(cnt3, 0, "Today ("+today+")", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		label = new Label(cnt3, 1, "Severity", cellFormatCenter);
		risk_assess_sheet.addCell(label);		
		
		cnt = 2;
		for(String a : sDesc){
			Integer l = todayWorstSeverity.get(a);
			label = new Label(cnt3, cnt, LevelValueMap.get(l), cellFormatCenter);
			risk_assess_sheet.addCell(label);
			cnt++;
		}
		
		cnt3+=1;
		label = new Label(cnt3, 1, "M.F.", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			Float l = MFDescMatrix.get(a);
			number = new Number(cnt3, cnt, l, cellFormatCenter);
			risk_assess_sheet.addCell(number);
			cnt++;
		}
		
		cnt3+=2;
		label = new Label(cnt3, 1, "Injury Million $", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			Float l = injuryMillionDesc.get(a);
			number = new Number(cnt3, cnt, l, cellFormatCenter);
			risk_assess_sheet.addCell(number);
			cnt++;
		}
		
		cnt3+=1;
		label = new Label(cnt3, 1, "Damage Million $", cellFormatCenter);
		risk_assess_sheet.addCell(label);
		
		cnt = 2;
		for(String a : sDesc){
			Float l = damageMillionDesc.get(a);
			number = new Number(cnt3, cnt, l, cellFormatCenter);
			risk_assess_sheet.addCell(number);
			cnt++;
		}
		
		
		workbook_out.write();
		if(workbook_out != null) workbook_out.close();
	}
}
