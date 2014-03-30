package ac.kaist.analysis.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.joda.time.LocalDate;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import ac.kaist.analysis.ax.JxlParser;
import ac.kaist.analysis.ax.JxlSheetModel;
import ac.kaist.analysis.model.WarningAnalysisInputData;

public class WarningAnalysisLoadExcel {
	
	WarningAnalysisInputData waInputData;
	
	public WarningAnalysisLoadExcel(String path, String sheetName, int depth) throws IOException{
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(path));
		
			//Parse
			JxlParser parser = new JxlParser(workbook);
			//Get sheet
			JxlSheetModel nrSheet = parser.getSheetByName(sheetName);
			
			Set<String> sEv_id = nrSheet.getColumnTypeList("Ev_id");				//UNIQUE
			Vector<String> vEv_id = nrSheet.getColumnStringContents("Ev_id");				//ALL
			
			Vector<LocalDate> vDate = new Vector<LocalDate>();
			Vector<LocalDate> sDate = new Vector<LocalDate>();
			Set<LocalDate> sDateSet;// = new Vector<LocalDate>();
			TreeMap<LocalDate, Integer> inputDateMap = new TreeMap<LocalDate, Integer>(DateUtil.dateComp);
			for(String s : vEv_id){
				//System.out.println(s.subSequence(0, 8));
				String y = (String) s.subSequence(0, 4);
				String m = (String) s.subSequence(4, 6);
				String d = (String) s.subSequence(6, 8);
				String dateStr = y+"-"+m+"-"+d;
				vDate.add(new LocalDate(dateStr));
				inputDateMap.put(new LocalDate(dateStr), 0);
			}
			
			sDateSet = inputDateMap.keySet();
			
			for(LocalDate s : sDateSet){
				sDate.add(s);
			}
			
			
			Set<String> sSubsection = nrSheet.getColumnTypeList("subsection");		//UNIQUE
			Vector<String> vSubsection = nrSheet.getColumnStringContents("subsection");	//ALL
			
			Vector<String> sInjury = new Vector<String>();
			sInjury.add("FATL");
			sInjury.add("SERS");
			sInjury.add("MINR");
			sInjury.add("NONE");
			Vector<String> vInjury = nrSheet.getColumnStringContents("highest_inj_level");//ALL
			
			Map<String, Vector<Integer> > mvInjury = new HashMap<String, Vector<Integer> >();
			
			//System.out.println(nrSheet.getContents());
			Map<String, Float> injuryWeight = new HashMap<String, Float>();
			for(String a : sInjury){
				Vector<String> weights = nrSheet.getColumnStringContents(a+" IJR $");
				injuryWeight.put(a, Float.parseFloat(weights.get(0)));
				
				Vector<Integer> injury = nrSheet.getColumnIntegerContents("injury "+a);
				mvInjury.put(a, injury);
			}
			
			Vector<String> sDamage = new Vector<String>();
			sDamage.add("DEST");
			sDamage.add("SUBS");
			sDamage.add("MINR");
			sDamage.add("NONE");
			Vector<String> vDamage = nrSheet.getColumnStringContents("damage");			//ALL
			
			Map<String, Float> damageWeight = new HashMap<String, Float>();
			for(String a : sDamage){
				Vector<String> weights = nrSheet.getColumnStringContents(a+" DMG $");
				damageWeight.put(a, Float.parseFloat(weights.get(0)));
			}
					
			//Assign value for label
			Map<String, Integer> damageValueMap = new HashMap<String, Integer>();
			damageValueMap.put("DEST", 4);
			damageValueMap.put("SUBS", 3);
			damageValueMap.put("MINR", 2);
			damageValueMap.put("NONE", 1);
			Map<String, Integer> InjuryValueMap = new HashMap<String, Integer>();
			InjuryValueMap.put("FATL", 4);
			InjuryValueMap.put("SERS", 3);
			InjuryValueMap.put("MINR", 2);
			InjuryValueMap.put("NONE", 1);
			Map<Integer, String> LevelValueMap = new HashMap<Integer, String>();
			LevelValueMap.put(5, "A");
			LevelValueMap.put(4, "B");
			LevelValueMap.put(3, "C");
			LevelValueMap.put(2, "D");
			LevelValueMap.put(1, "E");
			
			String[] factors = {"Category", "SubCategory", "Section", "SubSection"};
			
			//Unique describe Id for retrieval
			Set<String> sDesc = nrSheet.getColumnTypeList(factors[depth-1]);
			//Describe ID for saving
			Vector<String> vDesc = nrSheet.getColumnStringContents(factors[depth-1]);
			
			waInputData = new WarningAnalysisInputData();
			
			waInputData.setDepth(depth);
			waInputData.setsEv_id(sEv_id);
			waInputData.setvEv_id(vEv_id);
			waInputData.setsSubsection(sSubsection);
			waInputData.setvSubsection(vSubsection);
			waInputData.setsInjury(sInjury);
			waInputData.setvInjury(vInjury);
			waInputData.setsDamage(sDamage);
			waInputData.setvDamage(vDamage);
			waInputData.setDamageValueMap(damageValueMap);
			waInputData.setInjuryValueMap(InjuryValueMap);
			waInputData.setLevelValueMap(LevelValueMap);
			waInputData.setsDesc(sDesc);
			waInputData.setvDesc(vDesc);	
			
			waInputData.setDamageWeight(damageWeight);
			waInputData.setInjuryWeight(injuryWeight);
			
			waInputData.setMvInjury(mvInjury);
			
			waInputData.setvDate(vDate);
			waInputData.setsDate(sDate);
			
			waInputData.setInputStartDate(sDate.get(0));
            waInputData.setInputEndDate(sDate.get(sDate.size()-1));
		
            waInputData.setFactors(factors);
            
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WarningAnalysisInputData getWaInputData() {
		return waInputData;
	}
	
	

}
