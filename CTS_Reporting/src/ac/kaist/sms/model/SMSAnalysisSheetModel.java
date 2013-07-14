package ac.kaist.sms.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.joda.time.LocalDate;

import ac.kaist.sms.utils.SMSAnalysisDateUtil;

import jxl.Sheet;

/**
 * @author mh.lee
 * 
 * xls파일을 다큐먼트 단위의 사용하기 쉬운 데이터로 저장합니다.
 *
 */
public class SMSAnalysisSheetModel {
	public static int HEADER_NOT_FOUND = -999;
	
	private Vector<String> headers;
	private Vector<Vector<String> > contents;
	
	public SMSAnalysisSheetModel(Sheet sheet) {
		//Initialize
		headers = new Vector<String>();
		contents = new Vector<Vector<String> >();
		
		for(int i = 0 ; i < sheet.getRows() ; i++){
			Vector<String> rows = new Vector<String>();
			for(int j = 0 ; j < sheet.getColumns() ; j++){
				if(sheet.getCell(j, i).getContents().isEmpty())
					continue;
				//FIrst rows = header
				if(i == 0){
					headers.add(sheet.getCell(j, i).getContents());
				}
				//Otherwise normal content
				else{
					//System.out.println(headers);
					if(headers.get(j).equalsIgnoreCase("날짜"))
						rows.add("20"+sheet.getCell(j, i).getContents());
					else
						rows.add(sheet.getCell(j, i).getContents());
				}
			}
			//Skip header
			if(i > 0){
				//If each rows contains at least one elements, then add
				boolean all_empty = true;
				for(String str : rows)
					if(str.trim().length() > 0) all_empty = false;
				
				if(!all_empty)
					contents.add(rows);
			}
		}
	}
	
	public String toString(){
		String resultStr = "";
		
		for(int i = 0 ; i < headers.size(); i++){
			resultStr += headers.get(i)+"\t";
		}
		resultStr += "\n";
		for(int i = 0 ; i < contents.size(); i++){
			Vector<String> rows = contents.get(i);
			for(int j = 0 ; j < rows.size(); j++){
				resultStr += rows.get(j)+"\t";
			}
			resultStr += "\n";
		}
		return resultStr;
	}
	
	public int getColumnSum(int columnIndex){
		int sum = 0;
		for(int i = 0 ; i < contents.size(); i++){
			sum += Integer.parseInt(contents.get(i).get(columnIndex));
		}
		return sum;
	}
	
	public int getColumnSumByHeader(String header){
		int index = -1;
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(header))
				index = i;
		}
		
		if (index == -1) return HEADER_NOT_FOUND;
		else return getColumnSum(index);
		
	}
	
	public double getColumnAverage(String dataHeader){
		return getColumnAverage(getColumnIndex(dataHeader));
	}
	
	public double getColumnAverage(int columnIndex){
		int sum = getColumnSum(columnIndex);
		double average = sum / (double) contents.size();
		return average;
	}

	public Map<String, Integer> getDateColumnSum(String dateHeader, String dataHeader, int dateType){
//		int dateIndex = -1;
//		int dataIndex = -1;
//		for(int i = 0 ; i < headers.size(); i++){
//			if(headers.get(i).equalsIgnoreCase(dateHeader))
//				dateIndex = i;
//			if(headers.get(i).equalsIgnoreCase(dataHeader))
//				dataIndex = i;
//		}
//		
//		if(dateIndex == -1 || dataIndex == -1){
//			System.out.println("Header mismatched!");
//			return null;
//		}
//		
//		TreeMap<LocalDate, Integer> inputDateMap = new TreeMap<LocalDate, Integer>(SMSAnalysisDateUtil.dateComp);
//		for(int i = 0 ; i < contents.size(); i++){
//			//sum += Integer.parseInt(contents.get(i).get(columnIndex));
//			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
//			inputDateMap.put(new LocalDate(dateStr), 0);
//		}
//		
//		Map<String, Integer> dateMap = SMSAnalysisDateUtil.getDateArray(inputDateMap, type);
//		for(int i = 0 ; i < contents.size(); i++){
//			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
//			dateStr = SMSAnalysisDateUtil.cutDateStr(dateStr, type);
//			Integer data_val = Integer.parseInt(contents.get(i).get(dataIndex));
//			Integer cur_val = dateMap.get(dateStr);
//			dateMap.put(dateStr, cur_val + data_val);
//		}
		//return dateMap;
		return getDateColumnSumByCondition(dateHeader, dataHeader, dateType, null, null);
	}
	
	public Map<String, Integer> getDateColumnSumByCondition(String dateHeader, String dataHeader, int dateType, String condHeader, String condValue){
		int dateIndex = -1;
		int dataIndex = -1;
		int condIndex = -1;
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(dateHeader))
				dateIndex = i;
			if(headers.get(i).equalsIgnoreCase(dataHeader))
				dataIndex = i;
			if(condHeader != null){
				if(headers.get(i).equalsIgnoreCase(condHeader))
					condIndex = i;
			}
		}
		
		if(dateIndex == -1 || dataIndex == -1){
			System.out.println("Headers (data or date) mismatched!");
			return null;
		}

		if(condHeader != null){
			if(condIndex == -1){
				System.out.println("Header (Cond) mismatched!");
				return null;
			}
			
		}
		
		
		
		TreeMap<LocalDate, Integer> inputDateMap = new TreeMap<LocalDate, Integer>(SMSAnalysisDateUtil.dateComp);
		for(int i = 0 ; i < contents.size(); i++){
			//sum += Integer.parseInt(contents.get(i).get(columnIndex));
			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
			inputDateMap.put(new LocalDate(dateStr), 0);
		}
		
		Map<String, Integer> dateMap = SMSAnalysisDateUtil.getDateArray(inputDateMap, dateType);
		for(int i = 0 ; i < contents.size(); i++){
			if(condHeader != null){
				if(!condValue.equalsIgnoreCase(contents.get(i).get(condIndex))){
					continue;
				}
			}
			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
			dateStr = SMSAnalysisDateUtil.cutDateStr(dateStr, dateType);
			Integer data_val = Integer.parseInt(contents.get(i).get(dataIndex));
			Integer cur_val = dateMap.get(dateStr);
			dateMap.put(dateStr, cur_val + data_val);
		}
		return dateMap;
	}
	
	public Map<String, Double> getDateColumnAverageByCondition(String dateHeader, String dataHeader, int dateType, String condHeader, String condValue){
		int dateIndex = -1;
		int dataIndex = -1;
		int condIndex = -1;
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(dateHeader))
				dateIndex = i;
			if(headers.get(i).equalsIgnoreCase(dataHeader))
				dataIndex = i;
			if(condHeader != null){
				if(headers.get(i).equalsIgnoreCase(condHeader))
					condIndex = i;
			}
		}
		
		if(dateIndex == -1 || dataIndex == -1){
			System.out.println("Headers (data or date) mismatched!");
			return null;
		}

		if(condHeader != null){
			if(condIndex == -1){
				System.out.println("Header (Cond) mismatched!");
				return null;
			}
			
		}
		
		
		
		TreeMap<LocalDate, Integer> inputDateMap = new TreeMap<LocalDate, Integer>(SMSAnalysisDateUtil.dateComp);
		Map<String, Integer> countMap = new TreeMap<String, Integer>();
		Map<String, Double> dateMapAverage = new TreeMap<String, Double>();
		for(int i = 0 ; i < contents.size(); i++){
			//sum += Integer.parseInt(contents.get(i).get(columnIndex));
			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
			inputDateMap.put(new LocalDate(dateStr), 0);
			countMap.put(SMSAnalysisDateUtil.cutDateStr(dateStr, dateType), 0);
		}
		
		
		
		Map<String, Integer> dateMap = SMSAnalysisDateUtil.getDateArray(inputDateMap, dateType);
		
		for(int i = 0 ; i < contents.size(); i++){
			if(condHeader != null){
				if(!condValue.equalsIgnoreCase(contents.get(i).get(condIndex))){
					continue;
				}
			}
			String dateStr = SMSAnalysisDateUtil.convertJodaDateFormat(contents.get(i).get(dateIndex));
			dateStr = SMSAnalysisDateUtil.cutDateStr(dateStr, dateType);
			Integer data_val = Integer.parseInt(contents.get(i).get(dataIndex));
			Integer cur_val = dateMap.get(dateStr);
			dateMap.put(dateStr, cur_val + data_val);
			Integer cur_count = countMap.get(dateStr);
			countMap.put(dateStr, cur_count + 1);
		}
		
	
		//Do average
		Set<String> keySet = dateMap.keySet();
		for(String key : keySet){
			Integer cur_val = dateMap.get(key);
			Integer cur_count = countMap.get(key);
			dateMapAverage.put(key, cur_val / (double)cur_count);
		}
		
		return dateMapAverage;
	}
	
	public Vector<String> getDataByCondition(String dataHeader, String condHeader, String condValue){
		Vector<String> result = new Vector<String>();
		
		int dataIndex = -1;
		int condIndex = -1;
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(dataHeader))
				dataIndex = i;
			if(headers.get(i).equalsIgnoreCase(condHeader))
				condIndex = i;
		}
		if(dataIndex == -1 || condIndex == -1){
			return null;
		}
		
		for(int i = 0 ; i < contents.size(); i++){
			if(condValue.equalsIgnoreCase(contents.get(i).get(condIndex))){
				String value = contents.get(i).get(dataIndex);
				result.add(value);
			}
		}
		
		return result;
	}
	
	public Vector<String> getHeaders() {
		return headers;
	}

	public Vector<Vector<String>> getContents() {
		return contents;
	}
	
	public Map<String, Double> getDataMap(String dataHeader, String valueHeader){
		Map<String, Double> dataMap = new TreeMap<String, Double>();
		
		int valueIndex = -1;
		int dataIndex = -1;

		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(valueHeader))
				valueIndex = i;
			if(headers.get(i).equalsIgnoreCase(dataHeader))
				dataIndex = i;

		}
		
		if(valueIndex == -1 || dataIndex == -1){
			System.out.println("Headers (data or date) mismatched!");
			return null;
		}

		for(int i = 0 ; i < contents.size(); i++){
				String code = contents.get(i).get(dataIndex);
				Double value = Double.parseDouble(contents.get(i).get(valueIndex));
				//result.add(value);
				dataMap.put(code, value);
		}
		
		
		return dataMap;
	}
	
	public Set<String> getColumnTypeList(String dataHeader){
		Map<String, Integer> typeList = new TreeMap<String, Integer>();
		int dataIndex = getColumnIndex(dataHeader);
		
		for(int i = 0 ; i < contents.size(); i++){
			String code = contents.get(i).get(dataIndex);
			typeList.put(code, 0);
		}
		
		return typeList.keySet();
	}
	
	public int getColumnIndex(String dataHeader){
		int dataIndex = -1;
		
		for(int i = 0 ; i < headers.size(); i++){
			if(headers.get(i).equalsIgnoreCase(dataHeader))
				dataIndex = i;

		}
		
		if(dataIndex == -1){
			System.out.println("Headers (data or date) mismatched!");
			return -1;
		}
		
		return dataIndex;
	}
	
	public Map<String, Map<String, Double> > getEffectiveness(String dateHeader, String initialRiskHeader, String residualRiskHeader, String actionHeader){
		
		if(getColumnIndex(dateHeader) < 0 || getColumnIndex(initialRiskHeader) < 0 || getColumnIndex(residualRiskHeader) < 0 || getColumnIndex(actionHeader) < 0){
			System.out.println("In this sheet, effectivenss is cannot be computed. Because necessary columns do not exist.");
			return null;
		}
		
		Set<String> codes = this.getColumnTypeList(actionHeader);
		//System.out.println("action code : "+codes);
		Map<String, Map<String, Double> > nreffectiveness = new TreeMap<String, Map<String, Double> >();
		for(String action_code : codes){
			Map<String, Double> effectivenessMap = new TreeMap<String, Double>();
			
			Map<String, Double> averageInitialRiskByAction = this.getDateColumnAverageByCondition(dateHeader, initialRiskHeader, SMSAnalysisDateUtil.dateTypeMonth, actionHeader, action_code);
			Map<String, Double> averageResidualRiskByAction = this.getDateColumnAverageByCondition(dateHeader, residualRiskHeader, SMSAnalysisDateUtil.dateTypeMonth, actionHeader, action_code);
			
			Set<String> monthList = averageInitialRiskByAction.keySet();
			for(String month : monthList){
				Double initialRisk = averageInitialRiskByAction.get(month);
				Double ResidualRisk = averageResidualRiskByAction.get(month);
				Double effectiveness = initialRisk - ResidualRisk;
				effectivenessMap.put(month, effectiveness);
			}
			nreffectiveness.put(action_code, effectivenessMap);
		}
		//System.out.println("nreffectiveness : "+nreffectiveness);
		return nreffectiveness;
	}
}
