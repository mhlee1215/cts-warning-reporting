package ac.kaist.sms.model;

import java.util.Map;
import java.util.Vector;

/**
 * @author user
 * Classification (B3) °á°ú
 */
public class SMSAnalysisResultClassification {
	
	private Vector<String> frequencyTypes;
	private Vector<String> viCodes;
	private Vector<String> aiCodes;
	
	public Vector<String> getFrequencyTypes() {
		return frequencyTypes;
	}

	public void setFrequencyTypes(Vector<String> frequencyTypes) {
		this.frequencyTypes = frequencyTypes;
	}

	public Vector<String> getViCodes() {
		return viCodes;
	}

	public void setViCodes(Vector<String> viCodes) {
		this.viCodes = viCodes;
	}

	public Vector<String> getAiCodes() {
		return aiCodes;
	}

	public void setAiCodes(Vector<String> aiCodes) {
		this.aiCodes = aiCodes;
	}

	/**
	 * Accident / Serious Incident / Incident frequency
	 */
	private Map<String, Map<String, Integer> > frequency;
	
	/**
	 * Very important indicator frequency
	 */
	private Map<String, Map<String, Map<String, Integer> > > veryImportantFrequency;
	
	/**
	 * Average important indicator frequency
	 */
	private Map<String, Map<String, Map<String, Integer> > > averageFrequency;
	
	//Initial /Residual risk
	//Corrective action
	private SMSAnalysisResultSafetyReport safetyReportResult;

	public Map<String, Map<String, Integer>> getFrequency() {
		return frequency;
	}

	public void setFrequency(Map<String, Map<String, Integer>> frequency) {
		this.frequency = frequency;
	}

	public Map<String, Map<String, Map<String, Integer>>> getVeryImportantFrequency() {
		return veryImportantFrequency;
	}

	public void setVeryImportantFrequency(
			Map<String, Map<String, Map<String, Integer>>> veryImportantFrequency) {
		this.veryImportantFrequency = veryImportantFrequency;
	}

	public Map<String, Map<String, Map<String, Integer>>> getAverageFrequency() {
		return averageFrequency;
	}

	public void setAverageFrequency(
			Map<String, Map<String, Map<String, Integer>>> averageFrequency) {
		this.averageFrequency = averageFrequency;
	}

	public SMSAnalysisResultSafetyReport getSafetyReportResult() {
		return safetyReportResult;
	}

	public void setSafetyReportResult(
			SMSAnalysisResultSafetyReport safetyReportResult) {
		this.safetyReportResult = safetyReportResult;
	}

	@Override
	public String toString() {
		return "<<SMSAnalysisResultClassification>>\n\tfrequencyTypes="
				+ frequencyTypes + "\n\tviCodes=" + viCodes + "\n\taiCodes="
				+ aiCodes + "\n\tfrequency=" + frequency
				+ "\n\tveryImportantFrequency=" + veryImportantFrequency
				+ "\n\taverageFrequency=" + averageFrequency + "\n";
	}
}
