package ac.kaist.analysis.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.joda.time.LocalDate;

import ac.kaist.analysis.WarningAnalyzer.likelihoodDesc;

public class WarningAnalysisResultData {
	//Total Departure from user input
	int totalDeparture;
	int analDepth;
	//Occurrence Matrix (Map<Ev_ID, Map<SUBSECTION, OCURRENCE>)
	Map<String, Map<String, Integer> > occurrenceMatrix;
	//Personal Injury (Map<Ev_ID, Map<INJURY_LEVEL, NUMBER>)
	Map<String, Map<String, Integer> > personalInjuryMatrix;
	//Highest Injury (Map<Ev_ID, Map<INJURY_LEVEL, NUMBER>)
	Map<String, Map<String, Integer> > highestInjuryMatrix;
	//Aircraft Injury (Map<Ev_ID, Map<INJURY_LEVEL, NUMBER>)
	Map<String, Map<String, Integer> > aircraftDamageMatrix;
	//Injury Million (Map<Ev_ID, InjuryMillion>)
	Map<String, Float> injuryMillion;
	//Damage Million (Map<Ev_ID, DamageMillion>)
	Map<String, Float> damageMillion;
	
	//Aircraft Damage (Map<Descriptor, Map<DAMAGE, NUMBER>)
	Map<String, Map<String, Integer> > aircraftDamageDescMatrix;
	//Injury Level (Map<Descriptor, Map<INJURY_LEVEL, NUMBER>)
	Map<String, Map<String, Integer> > InjuryLevelDescMatrix;
	//Combined Hazard (Warning) Level (Map<Descriptor, Map<A_to_E_LEVEL, NUMBER>) 
	Map<String, Map<Integer, Integer> > ABCDEDescMatrix;
	//Save worst severity
	Map<String, Integer> worstSeverityMap;
	//Save most severity
	Map<String, Integer> mostSeverityMap;
	//Likelihood map from description to likelihood
	Map<String, Float> likelihoodMap;
	//For saving 5-label severity
	Map<String, Integer> likelihoodQuantizedMap;
	//Inverse Likelihood map for likelihood sorting
	ArrayList<likelihoodDesc> likelihoodList;
	
	Set<String> sEv_id;				//UNIQUE
	Vector<String> vEv_id;			//ALL
	Vector<String> pEv_id;			//Among certain period
	Set<String> sSubsection;		//UNIQUE
	Vector<String> vSubsection;		//ALL
	Vector<String> sInjury;			//UNIQUE
	Vector<String> vInjury;			//ALL
	Vector<String> sDamage;			//UNIQUE
	Vector<String> vDamage;			//ALL
			
	//Assign value for label
	Map<String, Integer> damageValueMap;
	Map<String, Integer> InjuryValueMap;
	Map<Integer, String> LevelValueMap;
	
	Set<String> sDesc;
	//Describe ID for saving
	Vector<String> vDesc;
	Vector<String> pDesc;			//Among certain period
	
	String today;
	Map<String, Integer> todayWorstSeverity;
	Map<String, Float> MFDescMatrix;
	
	Map<String, Float> injuryMillionDescMatrix;
	Map<String, Float> damageMillionDescMatrix;
	Map<String, Float> riskScoreDescMatrix;
	
	LocalDate analStartDate;
	LocalDate analEndDate;
	
	Vector<LocalDate> vDate;
		
	
	public Vector<LocalDate> getvDate() {
		return vDate;
	}
	public void setvDate(Vector<LocalDate> vDate) {
		this.vDate = vDate;
	}
	public LocalDate getAnalStartDate() {
		return analStartDate;
	}
	public void setAnalStartDate(LocalDate analStartDate) {
		this.analStartDate = analStartDate;
	}
	public LocalDate getAnalEndDate() {
		return analEndDate;
	}
	public void setAnalEndDate(LocalDate analEndDate) {
		this.analEndDate = analEndDate;
	}
	public int getAnalDepth() {
		return analDepth;
	}
	public void setAnalDepth(int analDepth) {
		this.analDepth = analDepth;
	}
	public Vector<String> getpEv_id() {
		return pEv_id;
	}
	public void setpEv_id(Vector<String> pEv_id) {
		this.pEv_id = pEv_id;
	}
	public Vector<String> getpDesc() {
		return pDesc;
	}
	public void setpDesc(Vector<String> pDesc) {
		this.pDesc = pDesc;
	}
	public Map<String, Float> getInjuryMillionDescMatrix() {
		return injuryMillionDescMatrix;
	}
	public void setInjuryMillionDescMatrix(
			Map<String, Float> injuryMillionDescMatrix) {
		this.injuryMillionDescMatrix = injuryMillionDescMatrix;
	}
	public Map<String, Float> getDamageMillionDescMatrix() {
		return damageMillionDescMatrix;
	}
	public void setDamageMillionDescMatrix(
			Map<String, Float> damageMillionDescMatrix) {
		this.damageMillionDescMatrix = damageMillionDescMatrix;
	}
	public Map<String, Float> getRiskScoreDescMatrix() {
		return riskScoreDescMatrix;
	}
	public void setRiskScoreDescMatrix(Map<String, Float> riskScoreDescMatrix) {
		this.riskScoreDescMatrix = riskScoreDescMatrix;
	}
	public Map<String, Float> getMFDescMatrix() {
		return MFDescMatrix;
	}
	public void setMFDescMatrix(Map<String, Float> mFDescMatrix) {
		MFDescMatrix = mFDescMatrix;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public Map<String, Integer> getTodayWorstSeverity() {
		return todayWorstSeverity;
	}
	public void setTodayWorstSeverity(Map<String, Integer> todayWorstSeverity) {
		this.todayWorstSeverity = todayWorstSeverity;
	}
	public Map<String, Map<String, Integer>> getOccurrenceMatrix() {
		return occurrenceMatrix;
	}
	public void setOccurrenceMatrix(
			Map<String, Map<String, Integer>> occurrenceMatrix) {
		this.occurrenceMatrix = occurrenceMatrix;
	}
	public Map<String, Map<String, Integer>> getHighestInjuryMatrix() {
		return highestInjuryMatrix;
	}
	public void setHighestInjuryMatrix(
			Map<String, Map<String, Integer>> highestInjuryMatrix) {
		this.highestInjuryMatrix = highestInjuryMatrix;
	}
	public Map<String, Map<String, Integer>> getAircraftDamageMatrix() {
		return aircraftDamageMatrix;
	}
	public void setAircraftDamageMatrix(
			Map<String, Map<String, Integer>> aircraftDamageMatrix) {
		this.aircraftDamageMatrix = aircraftDamageMatrix;
	}
	public Map<String, Map<String, Integer>> getAircraftDamageDescMatrix() {
		return aircraftDamageDescMatrix;
	}
	public void setAircraftDamageDescMatrix(
			Map<String, Map<String, Integer>> aircraftDamageDescMatrix) {
		this.aircraftDamageDescMatrix = aircraftDamageDescMatrix;
	}
	public Map<String, Map<String, Integer>> getInjuryLevelDescMatrix() {
		return InjuryLevelDescMatrix;
	}
	public void setInjuryLevelDescMatrix(
			Map<String, Map<String, Integer>> injuryLevelDescMatrix) {
		InjuryLevelDescMatrix = injuryLevelDescMatrix;
	}
	public Map<String, Map<Integer, Integer>> getABCDEDescMatrix() {
		return ABCDEDescMatrix;
	}
	public void setABCDEDescMatrix(
			Map<String, Map<Integer, Integer>> aBCDEDescMatrix) {
		ABCDEDescMatrix = aBCDEDescMatrix;
	}
	public Map<String, Integer> getWorstSeverityMap() {
		return worstSeverityMap;
	}
	public void setWorstSeverityMap(Map<String, Integer> worstSeverityMap) {
		this.worstSeverityMap = worstSeverityMap;
	}
	public Map<String, Integer> getMostSeverityMap() {
		return mostSeverityMap;
	}
	public void setMostSeverityMap(Map<String, Integer> mostSeverityMap) {
		this.mostSeverityMap = mostSeverityMap;
	}
	public Map<String, Float> getLikelihoodMap() {
		return likelihoodMap;
	}
	public void setLikelihoodMap(Map<String, Float> likelihoodMap) {
		this.likelihoodMap = likelihoodMap;
	}
	public Map<String, Integer> getLikelihoodQuantizedMap() {
		return likelihoodQuantizedMap;
	}
	public void setLikelihoodQuantizedMap(
			Map<String, Integer> likelihoodQuantizedMap) {
		this.likelihoodQuantizedMap = likelihoodQuantizedMap;
	}
	public ArrayList<likelihoodDesc> getLikelihoodList() {
		return likelihoodList;
	}
	public void setLikelihoodList(ArrayList<likelihoodDesc> likelihoodList) {
		this.likelihoodList = likelihoodList;
	}
	public Set<String> getsEv_id() {
		return sEv_id;
	}
	public void setsEv_id(Set<String> sEv_id) {
		this.sEv_id = sEv_id;
	}
	public Vector<String> getvEv_id() {
		return vEv_id;
	}
	public void setvEv_id(Vector<String> vEv_id) {
		this.vEv_id = vEv_id;
	}
	public Set<String> getsSubsection() {
		return sSubsection;
	}
	public void setsSubsection(Set<String> sSubsection) {
		this.sSubsection = sSubsection;
	}
	public Vector<String> getvSubsection() {
		return vSubsection;
	}
	public void setvSubsection(Vector<String> vSubsection) {
		this.vSubsection = vSubsection;
	}
	public Vector<String> getsInjury() {
		return sInjury;
	}
	public void setsInjury(Vector<String> sInjury) {
		this.sInjury = sInjury;
	}
	public Vector<String> getvInjury() {
		return vInjury;
	}
	public void setvInjury(Vector<String> vInjury) {
		this.vInjury = vInjury;
	}
	public Vector<String> getsDamage() {
		return sDamage;
	}
	public void setsDamage(Vector<String> sDamage) {
		this.sDamage = sDamage;
	}
	public Vector<String> getvDamage() {
		return vDamage;
	}
	public void setvDamage(Vector<String> vDamage) {
		this.vDamage = vDamage;
	}
	public Map<String, Integer> getDamageValueMap() {
		return damageValueMap;
	}
	public void setDamageValueMap(Map<String, Integer> damageValueMap) {
		this.damageValueMap = damageValueMap;
	}
	public Map<String, Integer> getInjuryValueMap() {
		return InjuryValueMap;
	}
	public void setInjuryValueMap(Map<String, Integer> injuryValueMap) {
		InjuryValueMap = injuryValueMap;
	}
	public Map<Integer, String> getLevelValueMap() {
		return LevelValueMap;
	}
	public void setLevelValueMap(Map<Integer, String> levelValueMap) {
		LevelValueMap = levelValueMap;
	}
	public Set<String> getsDesc() {
		return sDesc;
	}
	public void setsDesc(Set<String> sDesc) {
		this.sDesc = sDesc;
	}
	public Vector<String> getvDesc() {
		return vDesc;
	}
	public void setvDesc(Vector<String> vDesc) {
		this.vDesc = vDesc;
	}
	public int getTotalDeparture() {
		return totalDeparture;
	}
	public void setTotalDeparture(int totalDeparture) {
		this.totalDeparture = totalDeparture;
	}
	public Map<String, Map<String, Integer>> getPersonalInjuryMatrix() {
		return personalInjuryMatrix;
	}
	public void setPersonalInjuryMatrix(
			Map<String, Map<String, Integer>> personalInjuryMatrix) {
		this.personalInjuryMatrix = personalInjuryMatrix;
	}
	public Map<String, Float> getInjuryMillion() {
		return injuryMillion;
	}
	public void setInjuryMillion(Map<String, Float> injuryMillion) {
		this.injuryMillion = injuryMillion;
	}
	public Map<String, Float> getDamageMillion() {
		return damageMillion;
	}
	public void setDamageMillion(Map<String, Float> damageMillion) {
		this.damageMillion = damageMillion;
	}
	
}
