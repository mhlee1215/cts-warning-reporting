package ac.kaist.sms.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @author user
 * Monitoring And Trend Analysis (B1) 결과
 */
public class SMSAnalysisResultMonitoringAndTrendAnalysis {
	
	/**
	 * Accident / Serious incident / incident trend
	 */
	Map<String, Double> trends;
	/**
	 * Very important indicator trend
	 */
	TreeMap<String, Map<String, Double> > veryImportantTrends;
	/**
	 * Average important indicator trend
	 */
	TreeMap<String, Map<String, Double> > averageImportantTrends;
	/**
	 * Top five occurrence (의무보고)
	 */
	Map<String, Vector<String> > nrTopOccurrence;
	/**
	 * Bottom five occurrence (의무보고)
	 */
	Map<String, Vector<String> > nrBottomOccurrence;
	/**
	 * Top five occurrence (자율보고)
	 */
	Map<String, Vector<String> > vrTopOccurrence;
	/**
	 * Bottom five occurrence (자율보고)
	 */
	Map<String, Vector<String> > vrBottomOccurrence;
	public Map<String, Double> getTrends() {
		return trends;
	}
	public void setTrends(Map<String, Double> trends) {
		this.trends = trends;
	}
	public TreeMap<String, Map<String, Double>> getVeryImportantTrends() {
		return veryImportantTrends;
	}
	public void setVeryImportantTrends(
			TreeMap<String, Map<String, Double>> veryImportantTrends) {
		this.veryImportantTrends = veryImportantTrends;
	}
	public TreeMap<String, Map<String, Double>> getAverageImportantTrends() {
		return averageImportantTrends;
	}
	public void setAverageImportantTrends(
			TreeMap<String, Map<String, Double>> averageImportantTrends) {
		this.averageImportantTrends = averageImportantTrends;
	}
	public Map<String, Vector<String>> getNrTopOccurrence() {
		return nrTopOccurrence;
	}
	public void setNrTopOccurrence(Map<String, Vector<String>> nrTopOccurrence) {
		this.nrTopOccurrence = nrTopOccurrence;
	}
	public Map<String, Vector<String>> getNrBottomOccurrence() {
		return nrBottomOccurrence;
	}
	public void setNrBottomOccurrence(Map<String, Vector<String>> nrBottomOccurrence) {
		this.nrBottomOccurrence = nrBottomOccurrence;
	}
	public Map<String, Vector<String>> getVrTopOccurrence() {
		return vrTopOccurrence;
	}
	public void setVrTopOccurrence(Map<String, Vector<String>> vrTopOccurrence) {
		this.vrTopOccurrence = vrTopOccurrence;
	}
	public Map<String, Vector<String>> getVrBottomOccurrence() {
		return vrBottomOccurrence;
	}
	public void setVrBottomOccurrence(Map<String, Vector<String>> vrBottomOccurrence) {
		this.vrBottomOccurrence = vrBottomOccurrence;
	}
	
	@Override
	public String toString() {
		return "<<SMSAnalysisResultMonitoringAndTrendAnalysis>>\n\ttrends="
				+ trends + "\n\tveryImportantTrends=" + veryImportantTrends
				+ "\n\taverageImportantTrends=" + averageImportantTrends
				+ "\n\tnrTopOccurrence=" + nrTopOccurrence
				+ "\n\tnrBottomOccurrence=" + nrBottomOccurrence
				+ "\n\tvrTopOccurrence=" + vrTopOccurrence
				+ "\n\tvrBottomOccurrence=" + vrBottomOccurrence + "\n";
	}
	
}
