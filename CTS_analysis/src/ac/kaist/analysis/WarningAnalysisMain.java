package ac.kaist.analysis;

import java.io.IOException;
import java.text.ParseException;

import org.joda.time.LocalDate;

import jxl.write.WriteException;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;
import ac.kaist.analysis.utils.WarningAnalysisWriteExcel;


/**
 * @author mh.lee
 * 
 */
public class WarningAnalysisMain {
	
	public static void main(String[] args) throws IOException, ParseException, WriteException {
		
		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 3;
		
		//Load from excel file
		WarningAnalysisLoadExcel load = new WarningAnalysisLoadExcel(inputPath, inputSheetName, descriptor_depth);
		WarningAnalysisInputData waInputData = load.getWaInputData();
			
		
		//Analysis
		int totalDeparture = 192847;
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "2008-01-18");
		//Get Result data
		WarningAnalysisResultData waResultData = wa.getWaResultData();
		
		//Write
		String outputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process_out3.xls";
		WarningAnalysisWriteExcel waWrite = new WarningAnalysisWriteExcel(outputPath, waInputData, waResultData);
		waWrite.write();
		System.out.println("FIN!");
	}	
}


