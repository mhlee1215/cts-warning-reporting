package ac.kaist.sms.model;

/**
 * @author user
 * Hazard (A2) ���
 */
public class SMSAnalysisResultHazard {
	//hazard �߻� �Ǽ�
	private int hazardOcurrenceCount = 0;

	public int getHazardOcurrenceCount() {
		return hazardOcurrenceCount;
	}

	public void setHazardOcurrenceCount(int hazardOcurrenceCount) {
		this.hazardOcurrenceCount = hazardOcurrenceCount;
	}

	@Override
	public String toString() {
		return "<<SMSAnalysisResultHazard>>\n\thazardOcurrenceCount="
				+ hazardOcurrenceCount + "\n";
	}
	
	
}
