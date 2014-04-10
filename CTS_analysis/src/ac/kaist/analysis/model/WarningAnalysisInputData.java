package ac.kaist.analysis.model;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.joda.time.LocalDate;

public class WarningAnalysisInputData {
	//Depth for descriptive factor (0=category, 1=subcategory, 2=section, 3=subsection)
	//int depth;
	
	//Vector = contains a single column data with dupplicated id,
	//Unique = contains only unique column data
	
	//Ev_ID (Unique)
	Set<String> sEv_id;
	//Ev_ID (Vector)
	Vector<String> vEv_id;
	//Subsection (Unique)
	//Set<String> sSubsection;
	//Subsection (Vector)
	//Vector<String> vSubsection;
	//highest_inj_level (Unique)
	Vector<String> sInjury;
	//highest_inj_level (Vector)
	Vector<String> vInjury;
	//damage (Unique)
	Vector<String> sDamage;
	//damage (Vector)
	Vector<String> vDamage;			
			
	//Assign value for label (FATL = 4, SERS = 3, MINR = 2, NONE = 1) or so on.
	Map<String, Integer> damageValueMap;
	Map<String, Integer> InjuryValueMap;
	static Map<Integer, String> LevelValueMap;
	
	//Unique Descriptor based on depth (Unique)
	//Set<String> sDesc;
	//Vector of Descriptor
	//Vector<String> vDesc;
	
	//Damage Weight for million computation
	Map<String, Float> damageWeight;
	//Injury Weight for million computation
	Map<String, Float> injuryWeight;
	//Injury number of FATL, SERS, MINR, NONE based on Ev_id
	Map<String, Vector<Integer> > mvInjury;
	
	Vector<LocalDate> sDate;
	Vector<LocalDate> vDate;
	
	LocalDate inputStartDate;
	LocalDate inputEndDate;
	
	Vector<String> factors;
	
	Map<String, Set<String> > sDescColumns;
	Map<String, Vector<String> > vDescColumns;
	
	Map<String, Set<String> > errColumns;
	
	
	public Map<String, Set<String>> getErrColumns() {
		return errColumns;
	}
	public void setErrColumns(Map<String, Set<String>> errColumns) {
		this.errColumns = errColumns;
	}
	public Map<String, Set<String>> getsDescColumns() {
		return sDescColumns;
	}
	public void setsDescColumns(Map<String, Set<String>> sDescColumns) {
		this.sDescColumns = sDescColumns;
	}
	public Map<String, Vector<String>> getvDescColumns() {
		return vDescColumns;
	}
	public void setvDescColumns(Map<String, Vector<String>> vDescColumns) {
		this.vDescColumns = vDescColumns;
	}
	public Vector<String> getFactors() {
		return factors;
	}
	public void setFactors(Vector<String> factors) {
		this.factors = factors;
	}
	public LocalDate getInputStartDate() {
		return inputStartDate;
	}
	public void setInputStartDate(LocalDate analStartDate) {
		this.inputStartDate = analStartDate;
	}
	public LocalDate getInputEndDate() {
		return inputEndDate;
	}
	public void setInputEndDate(LocalDate analEndDate) {
		this.inputEndDate = analEndDate;
	}
	public Vector<LocalDate> getsDate() {
		return sDate;
	}
	public void setsDate(Vector<LocalDate> sDate) {
		this.sDate = sDate;
	}
	public Vector<LocalDate> getvDate() {
		return vDate;
	}
	public void setvDate(Vector<LocalDate> vDate) {
		this.vDate = vDate;
	}
	public Map<String, Vector<Integer>> getMvInjury() {
		return mvInjury;
	}
	public void setMvInjury(Map<String, Vector<Integer>> mvInjury) {
		this.mvInjury = mvInjury;
	}
	public Map<String, Float> getDamageWeight() {
		return damageWeight;
	}
	public void setDamageWeight(Map<String, Float> damageWeight) {
		this.damageWeight = damageWeight;
	}
	public Map<String, Float> getInjuryWeight() {
		return injuryWeight;
	}
	public void setInjuryWeight(Map<String, Float> injuryWeight) {
		this.injuryWeight = injuryWeight;
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
//	public Set<String> getsSubsection() {
//		return sSubsection;
//	}
//	public void setsSubsection(Set<String> sSubsection) {
//		this.sSubsection = sSubsection;
//	}
//	public Vector<String> getvSubsection() {
//		return vSubsection;
//	}
//	public void setvSubsection(Vector<String> vSubsection) {
//		this.vSubsection = vSubsection;
//	}
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
//	public Set<String> getsDesc() {
//		return sDesc;
//	}
//	public void setsDesc(Set<String> sDesc) {
//		this.sDesc = sDesc;
//	}
//	public Vector<String> getvDesc() {
//		return vDesc;
//	}
//	public void setvDesc(Vector<String> vDesc) {
//		this.vDesc = vDesc;
//	}
//	public int getDepth() {
//		return depth;
//	}
//	public void setDepth(int depth) {
//		this.depth = depth;
//	}
	
	
}
