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
 * 본 프로그램의 목적은 입력으로 들어오는 xls파일은 분석하여, 원하는 결과를 도출하는데 있습니다.
 * 자바를 사용함에 따른 장점은 웹등 다른 여러 플랫폼에 적용하기 쉽다는 점이 있습니다.
 * 
 * 최초 프로그램이 시작되는 부분이며, 파일의 입력, 분석, 출력 등으로 구성되어 있습니다.
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


