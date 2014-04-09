package ac.kaist.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.joda.time.LocalDate;

import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;

public class WarningAnalyzer {
	
	WarningAnalysisResultData waResultData;
	LocalDate analStartDate;
	LocalDate analEndDate;
	Map<String, Set<String> > errColumns;
	
	public WarningAnalyzer(WarningAnalysisInputData waInputData, int analDepth, int totalDeparture, String today){
		this(waInputData, analDepth, totalDeparture, today, null, waInputData.getInputStartDate(), waInputData.getInputEndDate());
	}
	
	public WarningAnalyzer(WarningAnalysisInputData waInputData, int analDepth, int totalDeparture, String today, Map<String, Set<String> > errColumns){
		this(waInputData, analDepth, totalDeparture, today, errColumns, waInputData.getInputStartDate(), waInputData.getInputEndDate());
	}
	
	public boolean isValidDate(LocalDate d){
		if(analStartDate.compareTo(d) <= 0 && analEndDate.compareTo(d) >= 0){
			return true;
		}
		else
			return false;
	}
	
	public boolean isValidColumns(String ev_id){
		if(errColumns == null) return true;
		else{
			if(errColumns.get(ev_id) == null) return true;
			else return false;
		}
	}
	
	public WarningAnalyzer(WarningAnalysisInputData waInputData, int analDepth, int totalDeparture, String today, LocalDate analStartDate, LocalDate analEndDate){
		this(waInputData, analDepth, totalDeparture, today, null, analStartDate, analEndDate);
	}
	
	public WarningAnalyzer(WarningAnalysisInputData waInputData, int analDepth, int totalDeparture, String today, Map<String, Set<String> > errColumns, LocalDate analStartDate, LocalDate analEndDate){
		
		this.analStartDate = analStartDate;
		this.analEndDate = analEndDate;
		this.errColumns = errColumns;
		
		waResultData = new WarningAnalysisResultData();
		waResultData.setTotalDeparture(totalDeparture);
		/***
		 * ######################################################################################
		 * ######################################################################################
		 * Actual Algorithm Start
		 * Here! we start to re-arrange data
		 * ######################################################################################
		 * ######################################################################################
		 */
		
		//int depth = 3;	//1 to 4
		//int totalDeparture = 192847;
		
		//get event id as vector type
		//Prefix s = unique key
		//Prefix v = data vector
		Vector<String> factorsV = waInputData.getFactors();
		
		Set<String> sEv_id = waInputData.getsEv_id(); //UNIQUE
		Vector<String> vEv_id = waInputData.getvEv_id();//ALL
		//Unique describe Id for retrieval
		Set<String> sDesc = waInputData.getsDescColumns().get(factorsV.get(analDepth-1));
		//Describe ID for saving
		Vector<String> vDesc = waInputData.getvDescColumns().get(factorsV.get(analDepth-1));
		
		//Set<String> sSubsection = waInputData.getsDescColumns().get(factorsV.get(analDepth-1));	//UNIQUE
		//Vector<String> vSubsection = waInputData.getvDescColumns().get(factorsV.get(analDepth-1));//ALL
		
		Vector<String> sInjury = waInputData.getsInjury();	//UNIQUE
		Map<String, Float> injuryWeight = waInputData.getInjuryWeight();	//Weight
		Vector<String> vInjury = waInputData.getvInjury();	//ALL
		
		Vector<String> sDamage = waInputData.getsDamage();			//UNIQUE
		Map<String, Float> damageWeight = waInputData.getDamageWeight();	//Weight
		Vector<String> vDamage = waInputData.getvDamage();			//ALL
		
		Map<String, Vector<Integer> > mvInjury = waInputData.getMvInjury();
		
		Vector<LocalDate> vDate = waInputData.getvDate();
		
		//Truncate error columns
		if(errColumns!= null && errColumns.size() > 0 ){
			Vector<String> vvEv_id = new Vector<String>();
			Map<String, Integer> mEv_id = new TreeMap<String, Integer>();	
			Vector<String> vvDesc = new Vector<String>();
			Vector<String> vvInjury = new Vector<String>();
			Vector<String> vvDamage = new Vector<String>();
			Map<String, Integer> mDesc = new TreeMap<String, Integer>();
			Vector<LocalDate> vvDate = new Vector<LocalDate>();
			Map<String, Vector<Integer> > mmvInjury = new TreeMap<String, Vector<Integer> >();
			for(int i = 0 ; i < vDesc.size() ; i++){
				String id = vEv_id.get(i);
				String d = vDesc.get(i);
				String s = vEv_id.get(i);
				String ij = vInjury.get(i);
				String da = vDamage.get(i);
				LocalDate date = vDate.get(i);
				if(!isValidDate(vDate.get(i))) continue;
				//If not exists, add
				
				if(errColumns.get(s) == null){
					vvEv_id.add(s);
					mEv_id.put(s,  0);
					vvDesc.add(d);
					mDesc.put(d,  0);
					vvInjury.add(ij);
					vvDamage.add(da);
					vvDate.add(date);
					
					//Vector<Integer> subMvInjury = new Vector<Integer>();
					for (String injury : sInjury){
						if(mmvInjury.get(injury) == null) mmvInjury.put(injury, new Vector<Integer>());
						mmvInjury.get(injury).add(mvInjury.get(injury).get(i));
						//subMvInjury.add(mvInjury.get(injury).get(i));
					}
					
				}
			}
			
			
			vEv_id = vvEv_id;
			sEv_id = mEv_id.keySet();
			vDesc = vvDesc;
			sDesc = mDesc.keySet();
			vInjury = vvInjury;
			vDamage = vvDamage;
			vDate = vvDate;
			mvInjury = mmvInjury;
		}
		
		
				
		//Assign value for label
		Map<String, Integer> damageValueMap = waInputData.getDamageValueMap();
		Map<String, Integer> InjuryValueMap = waInputData.getInjuryValueMap();
		Map<Integer, String> LevelValueMap = waInputData.getLevelValueMap();		
		
		
		
		
		///////////////////////////////////////////
		//Memory Allocation <Occurrence, highest injury, aircraft damage> matrix for each event id (unique)
		Map<String, Map<String, Integer> > occurrenceMatrix = new TreeMap<String, Map<String, Integer> >();
		for (String event_id : sEv_id){
			occurrenceMatrix.put(event_id, new TreeMap<String, Integer>());
		}
		
		Map<String, Map<String, Integer> > personalInjuryMatrix = new TreeMap<String, Map<String, Integer> >();
		Map<String, Map<String, Integer> > highestInjuryMatrix = new TreeMap<String, Map<String, Integer> >();
		Map<String, Map<String, Integer> > aircraftDamageMatrix = new TreeMap<String, Map<String, Integer> >();
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			personalInjuryMatrix.put(event_id, new TreeMap<String, Integer>());
			highestInjuryMatrix.put(event_id, new TreeMap<String, Integer>());
			aircraftDamageMatrix.put(event_id, new TreeMap<String, Integer>());
		}
		
		Map<String, Float> injuryMillion = new HashMap<String, Float>();
		Map<String, Float> damageMillion = new HashMap<String, Float>();
		
		Map<String, Integer> pEv_idMap = new TreeMap<String, Integer>();
		Vector<String> pEv_id = new Vector<String>();
		///////////////////////////////////////////

		
		//#########################################
		//EVENT TABLE BEGIN
		//#########################################
		//Add event occurrence
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			//pEv_id.add(event_id);
			pEv_idMap.put(event_id, 0);
			String subsection_name = vDesc.get(i);
			Integer cur_value = occurrenceMatrix.get(event_id).get(subsection_name);
			if(cur_value == null) cur_value = 0;
			occurrenceMatrix.get(event_id).put(subsection_name, cur_value+1);
		}
		
		pEv_id = cvtSet2Vector(pEv_idMap.keySet());
		
		//Personal Injury
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			//System.out.println(this.analStartDate+"/"+this.analEndDate+"//check:"+vDate.get(i));
			if(!isValidDate(vDate.get(i))) continue;
			for (String injury : sInjury){
				Integer i_val = mvInjury.get(injury).get(i);
				if(i_val == null) i_val = 0;
				//Integer c_val = personalInjuryMatrix.get(event_id).get(injury);
				//if(c_val == null) c_val = 0;
				personalInjuryMatrix.get(event_id).put(injury, i_val);	
			}
		}
		
		//System.out.println("personalInjuryMatrix:"+personalInjuryMatrix);
		
		//Injury Million
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			float i_million = 0;
			for (String injury : sInjury){
				Integer c_val = personalInjuryMatrix.get(event_id).get(injury);
				if(c_val == null) c_val = 0;
				
				i_million += c_val*injuryWeight.get(injury);
			}
			injuryMillion.put(event_id, i_million);
		}
		
		//System.out.println("mvInjury: "+mvInjury);
		//System.out.println("personalInjuryMatrix: "+personalInjuryMatrix);
		
		//Highest Injury
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			String h_injury = vInjury.get(i);
			highestInjuryMatrix.get(event_id).put(h_injury, 1);
		}
		
		//Aircraft damage
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			String h_damage = vDamage.get(i);
			aircraftDamageMatrix.get(event_id).put(h_damage, 1);
		}
		
		//Damage Million
		for(int i = 0 ; i < vEv_id.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			float d_million = 0;
			for (String damage : sDamage){
				Integer c_val = aircraftDamageMatrix.get(event_id).get(damage);
				if(c_val == null) c_val = 0;
				
				d_million += c_val*damageWeight.get(damage);
			}
			damageMillion.put(event_id, d_million);
		}
		
		
		//#########################################
		//EVENT TABLE END
		//#########################################
		
		
		
		
		//#########################################
		//DESCR FACTOR VS SEVERITY BEGIN
		//#########################################

		//Set<String> sDesc = nrSheet.getColumnTypeList(factorsV.get(depth-1));
		//Describe ID for saving
		//Vector<String> vDesc = nrSheet.getColumnStringContents(factorsV.get(depth-1));
		
		//Vector<String> factorsV = waInputData.getFactors();
		
		
		Map<String, Integer> pDescMap = new TreeMap<String, Integer>();
		Vector<String> pDesc = new Vector<String>();
		
		Map<String, Map<String, Integer> > aircraftDamageDescMatrix = new TreeMap<String, Map<String, Integer> >();
		Map<String, Map<String, Integer> > InjuryLevelDescMatrix = new TreeMap<String, Map<String, Integer> >();
		Map<String, Map<Integer, Integer> > ABCDEDescMatrix = new TreeMap<String, Map<Integer, Integer> >();
		Map<String, Integer> ABCDETodayDescMatrix = new TreeMap<String, Integer>();
		Map<String, Float> MFDescMatrix = new TreeMap<String, Float>();
		Map<String, Float> injuryMillionDescMatrix = new TreeMap<String, Float>();
		Map<String, Float> damageMillionDescMatrix = new TreeMap<String, Float>();
		Map<String, Float> riskScoreDescMatrix = new TreeMap<String, Float>();
		for (String descFactor : vDesc){
			aircraftDamageDescMatrix.put(descFactor, new TreeMap<String, Integer>());
			InjuryLevelDescMatrix.put(descFactor, new TreeMap<String, Integer>());
			ABCDEDescMatrix.put(descFactor, new TreeMap<Integer, Integer>(Collections.reverseOrder()));
		}
		
		for(int i = 0 ; i < vDesc.size() ; i++){
			if(!isValidDate(vDate.get(i))) continue;
			String descFactor = vDesc.get(i);
			//pDesc.add(descFactor);
			pDescMap.put(descFactor, 0);
			String aDamage = vDamage.get(i);
			Integer cur_value = aircraftDamageDescMatrix.get(descFactor).get(aDamage);
			if(cur_value == null) cur_value = 0;
			aircraftDamageDescMatrix.get(descFactor).put(aDamage, cur_value+1);
			
			String iLevel = vInjury.get(i);
			Integer cur_value2 = InjuryLevelDescMatrix.get(descFactor).get(iLevel);
			if(cur_value2 == null) cur_value2 = 0;
			InjuryLevelDescMatrix.get(descFactor).put(iLevel, cur_value2+1);
			
		}
		
		pDesc = cvtSet2Vector(pDescMap.keySet());
		
		for(int i = 0 ; i < vDesc.size() ; i++){
			String event_id = vEv_id.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			String descFactor = vDesc.get(i);
			Float i_million = injuryMillion.get(event_id);
			if(i_million == null) i_million = 0.0f;
			Float cur_value = injuryMillionDescMatrix.get(descFactor);
			if(cur_value == null) cur_value = 0.0f;
			injuryMillionDescMatrix.put(descFactor, i_million);
			
			Float d_million = damageMillion.get(event_id);
			if(d_million == null) d_million = 0.0f;
			Float cur_value2 = damageMillionDescMatrix.get(descFactor);
			if(cur_value2 == null) cur_value2 = 0.0f;
			damageMillionDescMatrix.put(descFactor, d_million);
		}
		
		//System.out.println("injuryMillion: "+injuryMillion);
		//System.out.println("injuryMillionDescMatrix: "+injuryMillionDescMatrix);
		
		for(String desc : sDesc){
			Float i_million = injuryMillionDescMatrix.get(desc);
			if(i_million == null) i_million = 0.0f;
			Float d_million = damageMillionDescMatrix.get(desc);
			if(d_million == null) d_million = 0.0f;
			riskScoreDescMatrix.put(desc, i_million+d_million);
		}
		
		
		
		
		//#########################################
		//DESCR FACTOR VS SEVERITY END
		//#########################################
	
		
		
		
		//#########################################
		//Risk Assessment BEGIN
		//#########################################
		
		//Save worst severity
		Map<String, Integer> worstSeverityMap = new TreeMap<String, Integer>();
		//Save most severity
		Map<String, Integer> mostSeverityMap = new TreeMap<String, Integer>();
		//Likelihood map from description to likelihood
		Map<String, Float> likelihoodMap = new TreeMap<String, Float>();
		//For saving 5-label severity
		Map<String, Integer> likelihoodQuantizedMap = new TreeMap<String, Integer>();
		//Inverse Likelihood map for likelihood sorting
		ArrayList<likelihoodDesc> likelihoodList = new ArrayList<likelihoodDesc>();
		
		
		
		//Convert into 5-label severity
		for(int i = 0 ; i < vDesc.size() ; i++){
			String desc = vDesc.get(i);
			if(!isValidDate(vDate.get(i))) continue;
			String injury = vInjury.get(i);
			String damage = vDamage.get(i);	
			//Convert two 4 labels (DEST, ... and FATL, ... ) to 5-label (A, B, C, D, E)
			int level5 = getCombinedSeverity(damageValueMap.get(damage), InjuryValueMap.get(injury));
			Integer cur_value = ABCDEDescMatrix.get(desc).get(level5);
			if(cur_value == null) cur_value = 0;
			ABCDEDescMatrix.get(desc).put(level5, cur_value+1);
			
			
		}
		
		LocalDate t_date = new LocalDate(today);
		
		//Compute Today Result
		for(int i = 0 ; i < vEv_id.size() ; i++){
			if(!isValidDate(vDate.get(i))) continue;
			//String event_id = vEv_id.get(i);
			if(vDate.get(i).compareTo(t_date) == 0){
				//System.out.println("Added!");
				String h_injury = vInjury.get(i);
				String h_damage = vDamage.get(i);
				String h_desc = vDesc.get(i);
				int level5 = getCombinedSeverity(damageValueMap.get(h_damage), InjuryValueMap.get(h_injury));
				Integer cur_level = ABCDETodayDescMatrix.get(h_desc);
				if(cur_level == null){
					cur_level = 1;
				}
				//System.out.println("cur level : "+cur_level);
				if(cur_level < level5)
					ABCDETodayDescMatrix.put(h_desc, level5);
				else
					ABCDETodayDescMatrix.put(h_desc, cur_level);
			}
		}
		
		//System.out.println("+++"+ABCDETodayDescMatrix);
		
		for(String desc : sDesc){
			Integer todaySeverity = ABCDETodayDescMatrix.get(desc);
			//Set minimum severity for blank
			//if(todaySeverity == null) ABCDETodayDescMatrix.put(desc, 1);
			
		}
				
		//Compute likelihood and get worst and most severity
		for(String desc : sDesc){
			Map<Integer, Integer> sortedSeverityMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
			Map<Integer, Integer> severityMap = ABCDEDescMatrix.get(desc);
			//Sorting by adding to TreeMap object
			int grandTotal = 0;
			for(int j = 1 ; j <= 5 ; j++){
				Integer cnt = severityMap.get(j);
				if (cnt == null ) continue;
				grandTotal += cnt;
				sortedSeverityMap.put(cnt, j);
			}
			
			//likelihood
			float likelihood = grandTotal / (float)totalDeparture * 10000000;
			//add worst severity and Most severity
			int worstSeverity = 0;
			int mostSeverity = 0;
			//Get the first entry as most severity since it is sorted by its frequency
			for(Map.Entry<Integer, Integer> entry : sortedSeverityMap.entrySet()){
				mostSeverity = entry.getValue();
				break;
			}
			//Get the first entry as worst severity since it is sorted by its severity
			for(Map.Entry<Integer, Integer> entry : severityMap.entrySet()){
				worstSeverity = entry.getKey();
				break;
			}
				
			worstSeverityMap.put(desc,  worstSeverity);
			mostSeverityMap.put(desc,  mostSeverity);
			//Set Likelihood
			likelihoodMap.put(desc,  likelihood);
			likelihoodList.add(new likelihoodDesc(desc, likelihood));
		}
		
		
		//Compute M.F. values
		for(String desc : sDesc){
			Integer todaySeverity = ABCDETodayDescMatrix.get(desc);
			if(todaySeverity == null) continue;
			Map<Integer, Integer> severityMap = ABCDEDescMatrix.get(desc);
			int grandTotal = 0;
			float mf = 0;
			for(int j = 1 ; j <= 5 ; j++){
				Integer cnt = severityMap.get(j);
				if (cnt == null ) cnt = 0;
				grandTotal += cnt;
				
				mf += (j - todaySeverity) * cnt;
			}
			if(grandTotal > 0)
				mf /= grandTotal;
			MFDescMatrix.put(desc,  mf);
		}
		
		
		Collections.sort(likelihoodList);
				
		//Likelihood partitioning (here, 0-20%, 20-40%, .. so on)
		int totalDesc = sDesc.size();
		int cur_label = 1;
		int cur_cnt = 0;
		float lim = (totalDesc*cur_label) / 5;
		for(likelihoodDesc ld : likelihoodList){
			cur_cnt++;
			if(cur_cnt > lim){
				cur_label++;
				lim = (totalDesc*cur_label) / 5;
			}
			likelihoodQuantizedMap.put(ld.getDesc(), cur_label);
		}
		
		//#########################################
		//Risk Assessment BEGIN
		//#########################################
				
		/***
		 * ######################################################################################
		 * ######################################################################################
		 * Actual Algorithm END
		 * ######################################################################################
		 * ######################################################################################
		 */
		
	
		
		waResultData.setOccurrenceMatrix(occurrenceMatrix);
		waResultData.setPersonalInjuryMatrix(personalInjuryMatrix);
		waResultData.setHighestInjuryMatrix(highestInjuryMatrix);;
		waResultData.setAircraftDamageMatrix(aircraftDamageMatrix);
		waResultData.setInjuryMillion(injuryMillion);
		waResultData.setDamageMillion(damageMillion);
		
		waResultData.setAircraftDamageDescMatrix(aircraftDamageDescMatrix);
		waResultData.setInjuryLevelDescMatrix(InjuryLevelDescMatrix);
		waResultData.setABCDEDescMatrix(ABCDEDescMatrix);
		waResultData.setWorstSeverityMap(worstSeverityMap);
		waResultData.setMostSeverityMap(mostSeverityMap);
		waResultData.setLikelihoodMap(likelihoodMap);
		waResultData.setLikelihoodQuantizedMap(likelihoodQuantizedMap);
		waResultData.setLikelihoodList(likelihoodList);
		waResultData.setsEv_id(sEv_id);
		waResultData.setvEv_id(vEv_id);
		waResultData.setsSubsection(sDesc);
		waResultData.setvSubsection(vDesc);
		waResultData.setsInjury(sInjury);
		waResultData.setvInjury(vInjury);
		waResultData.setsDamage(sDamage);
		waResultData.setvDamage(vDamage);
		waResultData.setDamageValueMap(damageValueMap);
		waResultData.setInjuryValueMap(InjuryValueMap);
		waResultData.setLevelValueMap(LevelValueMap);
		waResultData.setsDesc(sDesc);
		waResultData.setvDesc(vDesc);
		
		waResultData.setTodayWorstSeverity(ABCDETodayDescMatrix);
		waResultData.setToday(today);
		waResultData.setMFDescMatrix(MFDescMatrix);
		
		waResultData.setInjuryMillionDescMatrix(injuryMillionDescMatrix);
		waResultData.setDamageMillionDescMatrix(damageMillionDescMatrix);
		waResultData.setRiskScoreDescMatrix(riskScoreDescMatrix);
		
		waResultData.setpEv_id(pEv_id);
		waResultData.setpDesc(pDesc);
		waResultData.setAnalDepth(analDepth);
		
		waResultData.setAnalStartDate(analStartDate);
		waResultData.setAnalEndDate(analEndDate);
		
		waResultData.setvDate(vDate);
	}
	
	public WarningAnalysisResultData getWaResultData() {
		return waResultData;
	}

	public static int getCombinedSeverity(int a, int b){
		if (Math.max(a, b) == 4)
			return 5;
		else if (Math.max(a, b) == 3)
			return 4;
		else if (a + b > 3)
			return 3;
		else if (a + b > 2)
			return 2;
		else
			return 1;
	}
	
	//For sorting. 
		public static class likelihoodDesc implements Comparable<likelihoodDesc>{
			
			String desc;
			float likelihood;
			public likelihoodDesc(String desc, float likelihood){
				this.desc = desc;
				this.likelihood = likelihood;
			}


			public String getDesc() {
				return desc;
			}

			public void setDesc(String desc) {
				this.desc = desc;
			}

			public float getLikelihood() {
				return likelihood;
			}

			public void setLikelihood(float likelihood) {
				this.likelihood = likelihood;
			}

			@Override
			public int compareTo(likelihoodDesc arg0) {
				// TODO Auto-generated method stub
				return (int)(this.likelihood - arg0.getLikelihood());
			}

			@Override
			public String toString() {
				return "{\"desc\":\"" + desc + "\", \"likelihood\":\"" + likelihood
						+ "\"}";
			}		
		}	
		
		
		
		public static <T> Vector<T> cvtSet2Vector(Set<T> src){
			Vector<T> dst = new Vector<T>();
			
			for(T o : src){
				dst.add(o);
			}
			
			return dst;
		}
}
