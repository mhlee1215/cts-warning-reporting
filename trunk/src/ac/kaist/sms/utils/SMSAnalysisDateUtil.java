package ac.kaist.sms.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;

public class SMSAnalysisDateUtil {
	public static int dateTypeDay = 1;
	public static int dateTypeMonth = 3;
	public static int dateTypeYear = 4;
	
	public static Comparator<String> dateStrComp = new Comparator<String>() {
	    @Override
	    public int compare(String date1, String date2) {
	        return new LocalDate(date1).compareTo(new LocalDate(date2));
	    }
	};
	
	public static Comparator<LocalDate> dateComp = new Comparator<LocalDate>() {
	    @Override
	    public int compare(LocalDate date1, LocalDate date2) {
	        return (date1).compareTo(date2);
	    }
	};
	
	public static Map<String, Integer> getDateArray(LocalDate dateStart, LocalDate dateEnd, int type){
		Map<String, Integer> dateMap = new TreeMap<String, Integer>(dateStrComp);
		
		while(dateStart.compareTo(dateEnd)!=1){
			//LocalDate tmpDate = new LocalDate(dateStart);
			
			dateMap.put(cutDateStr(dateStart,  type), 0);
			
		    //System.out.println(dateStart);
			if(type == dateTypeDay)
				dateStart = dateStart.plusDays(1);
			else if(type == dateTypeMonth)
				dateStart = dateStart.plusMonths(1);
			else if(type == dateTypeYear)
				dateStart = dateStart.plusYears(1);
		   
		}
		return dateMap;
	}
	
	public static Map<String, Integer> getDateArray(TreeMap<LocalDate, Integer> dateArray, int type){
		LocalDate startDate = (LocalDate)dateArray.keySet().toArray()[0];
		LocalDate endDate = (LocalDate)dateArray.keySet().toArray()[dateArray.keySet().size()-1];
		return getDateArray(startDate, endDate, type);
	}
	
	public static String cutDateStr(LocalDate inputDate, int type){
		return cutDateStr(inputDate.toString(), type);
	}
	public static String cutDateStr(String inputDate, int type){
		String rtnStr = "";
		LocalDate date = new LocalDate(inputDate);
		if(type == dateTypeDay)
			rtnStr = date.toString();
		else if(type == dateTypeMonth)
		{
			rtnStr = Integer.toString(date.getYear()) + "-"+ Integer.toString(date.getMonthOfYear());
		}
		else if(type == dateTypeYear)
		{
			rtnStr = Integer.toString(date.getYear());
		}
		return rtnStr;
	}
	
	public static String convertJodaDateFormat(String input){
		return input.replace(".", "-").replace(" ", "");
	}
}
