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
	
	public WarningAnalysisLoadExcel(String path, String sheetName) throws IOException{
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
			
			
			//Set<String> sSubsection = nrSheet.getColumnTypeList("subsection");		//UNIQUE
			//Vector<String> vSubsection = nrSheet.getColumnStringContents("subsection");	//ALL
			
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
			
			//String[] factors = {"Category", "SubCategory", "Section", "SubSection"};
			//System.out.println("Headers!: "+nrSheet.getHeaders());
			Map<String, Vector<String> > vDescColumns = new HashMap<String, Vector<String> >();
			Map<String, Set<String> > sDescColumns = new HashMap<String, Set<String> >();
			Vector<String> factorsV = new Vector<String>();
			for(int i = 1 ; i <= 4 ; i++){
				String f = nrSheet.getHeaders().get(i);
				factorsV.add(f);
				sDescColumns.put(f, nrSheet.getColumnTypeList(f));
				vDescColumns.put(f, nrSheet.getColumnStringContents(f));
			}
//			System.out.println(factorsV);
//			String[] factors = (String[]) factorsV.toArray();
			
			
			
			
			//Unique describe Id for retrieval
			//Set<String> sDesc = nrSheet.getColumnTypeList(factorsV.get(depth-1));
			//Describe ID for saving
			//Vector<String> vDesc = nrSheet.getColumnStringContents(factorsV.get(depth-1));
			
			waInputData = new WarningAnalysisInputData();
			
			//waInputData.setDepth(depth);
			waInputData.setsEv_id(sEv_id);
			waInputData.setvEv_id(vEv_id);
			//waInputData.setsSubsection(sSubsection);
			//waInputData.setvSubsection(vSubsection);
			waInputData.setsInjury(sInjury);
			waInputData.setvInjury(vInjury);
			waInputData.setsDamage(sDamage);
			waInputData.setvDamage(vDamage);
			waInputData.setDamageValueMap(damageValueMap);
			waInputData.setInjuryValueMap(InjuryValueMap);
			waInputData.setLevelValueMap(LevelValueMap);
			//waInputData.setsDesc(sDesc);
			//waInputData.setvDesc(vDesc);	
			
			waInputData.setDamageWeight(damageWeight);
			waInputData.setInjuryWeight(injuryWeight);
			
			waInputData.setMvInjury(mvInjury);
			
			waInputData.setvDate(vDate);
			waInputData.setsDate(sDate);
			waInputData.setvDescColumns(vDescColumns);
			waInputData.setsDescColumns(sDescColumns);
			
			waInputData.setInputStartDate(sDate.get(0));
            waInputData.setInputEndDate(sDate.get(sDate.size()-1));
		
            waInputData.setFactors(factorsV);
            
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, Set<String> > dataValidityCheck(){
		Map<String, Map<String, Integer> > err = new TreeMap<String, Map<String, Integer> >();
		Map<String, Set<String> > errRtn = new TreeMap<String, Set<String> >();
		
		Map<String, Map<String, String> > dataContainer = new TreeMap<String, Map<String, String> >();
		for(int i = 0 ; i < waInputData.getvEv_id().size() ; i++){
			String s = waInputData.getvEv_id().get(i);
			if(dataContainer.get(s) == null) dataContainer.put(s, new TreeMap<String, String>());
			Map<String, String> subContainer = dataContainer.get(s);
			
			
			if(err.get(s) == null) err.put(s,  new TreeMap<String, Integer>());
			Map<String, Integer> errColumn = err.get(s);
			//highest_inj_level
			Vector<String> vInjury = waInputData.getvInjury();
			String injury_column = "highest_inj_level";
			if(subContainer.get(injury_column) == null) subContainer.put(injury_column, vInjury.get(i));
			else{
				String cur = subContainer.get(injury_column);
				if(!cur.equals(vInjury.get(i))) errColumn.put(injury_column, 0);
				if(waInputData.getInjuryValueMap().get(vInjury.get(i)) == null) errColumn.put(injury_column, 0);
			}
			
			//damage
			Vector<String> vDamage = waInputData.getvDamage();
			String damage_column = "damage";
			if(subContainer.get(damage_column) == null) subContainer.put(damage_column, vDamage.get(i));
			else{
				String cur = subContainer.get(damage_column);
				//if("20080118X00073".equals(s)) System.out.println(vDamage.get(i) +"/"+cur);
				if(!cur.equals(vDamage.get(i))){
					//if("20080118X00073".equals(s)) System.out.println("ADD!!");
					errColumn.put(damage_column, 0); 
				}
				
				if(waInputData.getDamageValueMap().get(vDamage.get(i)) == null) errColumn.put(damage_column, 0);
			}
			
			//injury FATL / SERS / MINR / NONE
			Map<String, Vector<Integer> > mvInjury = waInputData.getMvInjury();
			for(String a : waInputData.getsInjury()){
				String injury_amount_column = a;
				//System.out.println(mvInjury);
				Integer amount = mvInjury.get(injury_amount_column).get(i);
				if(subContainer.get(injury_amount_column) == null) subContainer.put(injury_amount_column, Integer.toString(amount));
				else{
					String cur = subContainer.get(injury_amount_column);
					if(!cur.equals(Integer.toString(amount))) errColumn.put("Injury "+injury_amount_column, 0);
				}
			}
			
			if(errColumn.size() > 0){
				err.put(s, errColumn);
			}
		}
		
		for(String key : err.keySet()){
			if(err.get(key).size() > 0) errRtn.put(key, err.get(key).keySet());
		}
		
		return errRtn;
	}
	
	public boolean isValidDate(LocalDate d, LocalDate start, LocalDate end){
		if(start.compareTo(d) <= 0 && end.compareTo(d) >= 0){
			return true;
		}
		else
			return false;
	}

	public WarningAnalysisInputData getWaInputData() {
		return waInputData;
	}
	
	

}
