package ac.kaist.sms.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.read.biff.BiffException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ac.kaist.sms.model.FileUploadForm;
import ac.kaist.sms.model.SMSAnalysisResults;
import ac.kaist.sms.utils.SMSAnalyzer;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}

	@RequestMapping(value = "/show2", method = RequestMethod.GET)
	public String displayForm2() {
		return "file_upload_form2";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(

	@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map,
			HttpServletRequest request) throws IOException, BiffException {
		System.out.println("Hi! save.do");
		List<MultipartFile> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();
		System.out.println("size:" + files.size());
		String realPath = request.getSession().getServletContext()
				.getRealPath("/report/attachedFiles");
		System.out.println(realPath);
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();
				System.out.println("File name : " + fileName);
				fileNames.add(fileName);

				InputStream is = multipartFile.getInputStream();

				Workbook workbook = Workbook.getWorkbook(is);
				System.out.println("sheet number : "
						+ workbook.getSheets().length);
				
				//Read workbook in xls format using jxl external library
//				Workbook workbook = Workbook.getWorkbook(new File("E:/ext_work/ReportingSystem/workspace/CTS_Reporting/src/ac/kaist/sms/model/data_field_new.xls"));
//				
				//Create Analyzer instance and Parse xls file in constructor
				SMSAnalyzer analyzer = new SMSAnalyzer(workbook);
				
				//Actual computation for analysis
				//Get result objects
				SMSAnalysisResults result = analyzer.Analysis();
				
				//Print results
				//System.out.println(result.toString());
				
				//System.out.println(analyzer.getContents());
				// for(int i = 0 ; i < workbook.getSheets().length ; i++){
				// System.out.println("sheet name["+i+"] : "+workbook.getSheets()[i].getName());
				// Sheet sheet = workbook.getSheets()[i];
				// System.out.println("content 0,0 "+sheet.getCell(0,
				// 0).getContents());
				//
				// }

				// Reader reader = new InputStreamReader(is);
				//
				// int data = reader.read();
				// while(data != -1){
				// //char theChar = (char) data;
				// data = reader.read();
				//
				// System.out.println(data);
				// }
				//
				// reader.close();
				map.addAttribute("smsResults", result);
			}
		}

		
		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}
}
