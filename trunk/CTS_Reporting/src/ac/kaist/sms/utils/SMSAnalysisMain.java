package ac.kaist.sms.utils;

import java.io.File; 
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.LocalDate;

import ac.kaist.sms.model.SMSAnalysisResults;
import ac.kaist.sms.utils.SMSAnalyzer;
import jxl.*; 
import jxl.read.biff.BiffException;

/**
 * @author mh.lee
 * �� ���α׷��� ������ �Է����� ������ xls������ �м��Ͽ�, ���ϴ� ����� �����ϴµ� �ֽ��ϴ�.
 * �ڹٸ� ����Կ� ���� ������ ���� �ٸ� ���� �÷����� �����ϱ� ���ٴ� ���� �ֽ��ϴ�.
 * 
 * ���� ���α׷��� ���۵Ǵ� �κ��̸�, ������ �Է�, �м�, ��� ������ �����Ǿ� �ֽ��ϴ�.
 *
 */
public class SMSAnalysisMain {

	public static void main(String[] args) throws BiffException, IOException, ParseException {

		//Read workbook in xls format using jxl external library
		Workbook workbook = Workbook.getWorkbook(new File("E:/ext_work/ReportingSystem/workspace/CTS_Reporting/src/ac/kaist/sms/model/data_field_new.xls"));
		
		//Create Analyzer instance and Parse xls file in constructor
		SMSAnalyzer analyzer = new SMSAnalyzer(workbook);
		
		//Actual computation for analysis
		//Get result objects
		SMSAnalysisResults result = analyzer.Analysis();
		
		//Print results
		System.out.println(result.toString());
		//System.out.println(analyzer.getContents());
	}
}


