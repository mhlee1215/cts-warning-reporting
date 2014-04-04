package ac.kaist.analysis.model;

public class RiskMatrixData{
		public RiskMatrixData(String eventID, String hazardID, int worst, int most, int today, int likelihood, float mf){
			this.eventID = eventID;
			this.hazardID = hazardID;
			this.worst = worst;
			this.most = most;
			this.today = today;
			this.liklihood = likelihood;
			this.mf = mf;
		}
		public Float mf;
		public int liklihood;
		public int worst;
		public int most;
		public Integer today;
		public String eventID;
		public String hazardID;
		
		@Override
		public String toString() {
			return "{\"mf\":\"" + mf + "\", \"liklihood\":\"" + liklihood
					+ "\", \"worst\":\"" + worst + "\", \"most\":\"" + most
					+ "\", \"today\":\"" + today + "\", \"eventID\":\""
					+ eventID + "\", \"hazardID\":\"" + hazardID + "\"}";
		}
		
		
	}