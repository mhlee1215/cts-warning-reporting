package ac.kaist.sms.model;

/**
 * @author user
 * Training (A5) °á°ú
 */
public class SMSAnalysisResultTraining {
	//Continuation courses (not implemented)
	private int continuationCourses;
	
	//Recurrent training (not implemented)
	private int recurrentTraining;
	
	//Training score
	private double trainingScore;

	public int getContinuationCourses() {
		return continuationCourses;
	}

	public void setContinuationCourses(int continuationCourses) {
		this.continuationCourses = continuationCourses;
	}

	public int getRecurrentTraining() {
		return recurrentTraining;
	}

	public void setRecurrentTraining(int recurrentTraining) {
		this.recurrentTraining = recurrentTraining;
	}

	public double getTrainingScore() {
		return trainingScore;
	}

	public void setTrainingScore(double trainingScore) {
		this.trainingScore = trainingScore;
	}

	@Override
	public String toString() {
		return "<<SMSAnalysisResultTraining>>\n\tcontinuationCourses="
				+ "N/A" + "\n\trecurrentTraining="
				+ "N/A" + "\n\ttrainingScore=" + trainingScore
				+ "\n";
	}
}
