package ac.kaist.cts.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import language.LanguagePack;
import language.LanguageServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;
import ac.kaist.cts.service.ReportService;
import ac.kaist.cts.service.UserService;

@Controller
public class ReportController {
	
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final ReportService reportService = null;

	@RequestMapping("/report.do")
    public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ReportItem riQuery = new ReportItem();
		riQuery.setReport_no(report_no);
		List<ReportItem> rpItemList = reportService.readReportItemList(riQuery);
		
		ModelAndView model = new ModelAndView("report/reportMain");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("report_no", report_no);
		model.addObject("rpItemList", rpItemList);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportBasic.do")
    public ModelAndView reportBasic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		Report report = reportService.readReport(rp);
		
		FlightInfo fi = new FlightInfo();
		fi.setId(report.getFlight_info_id());
		FlightInfo rfi = reportService.readFlightInfo(fi);
		
		AircraftInfo ai = new AircraftInfo();
		ai.setId(rfi.getAircraft_info_id());
		AircraftInfo rai = reportService.readAIrcraftInformation(ai);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Vector<String> acModelList = new Vector<String>();
		acModelList.add("A300-600");
		acModelList.add("A320-200");
		acModelList.add("A321-100");
		acModelList.add("A321-200");
		acModelList.add("A330-200");
		acModelList.add("A330-300");
		acModelList.add("A380-800");
		acModelList.add("B737-400");
		acModelList.add("B737-500");
		acModelList.add("B737-600");
		acModelList.add("B737-700");
		acModelList.add("B737-800");
		acModelList.add("B737-900");
		acModelList.add("B737-900ER");
		acModelList.add("B747-400");
		acModelList.add("B767-300");
		acModelList.add("B777-200ER");
		acModelList.add("B777-300");
		acModelList.add("B777-300ER");
		acModelList.add("B747-400 Combi");
		acModelList.add("B747-400SF");
		acModelList.add("B747-400F");
		acModelList.add("B747-8F");
		acModelList.add("B767-300F");
		acModelList.add("B777F");
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_BASIC);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		
		ModelAndView model = new ModelAndView("report/reportItem_basic");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("report_no", report_no);
		model.addObject("report", report);
		model.addObject("flight_info", rfi);
		model.addObject("aircraft_info", rai);
		model.addObject("reportItem", reportItem);
		model.addObject("acModelList", acModelList);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTaxiOut.do")
    public ModelAndView reportTaxiOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_TAXI_OUT);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_taxi-out");
		model.addObject("report_no", report_no);
		model.addObject("report_item_type", ReportItem.TYPE_TAXI_OUT);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTakeOff.do")
    public ModelAndView reportTakeOff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_TAKE_OFF);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_take-off");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportClimb.do")
    public ModelAndView reportClimb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_CLIMB);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_climb");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/report_en_route.do")
    public ModelAndView reportEnRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_EN_ROUTE);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_en-route");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportDecent.do")
    public ModelAndView reportDecent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_DECENT);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_decent");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportApproach.do")
    public ModelAndView reportApproach(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_APPROACH);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_approach");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportLanding.do")
    public ModelAndView reportLanding(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_LANDING);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_landing");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTaxiIn.do")
    public ModelAndView reportTaxiIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_TAXI_IN);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_taxi-in");
		model.addObject("report_no", report_no);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/management.do")
    public ModelAndView management(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("management/managementMain");
		model.addObject("page_title", lang.getStringHazardReportReview());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/readFlightInformation.do")
    public ModelAndView flightInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<FlightInfo> list = reportService.readFLightInfoList();
			
		ModelAndView model = new ModelAndView("report/FlightInfoList");
		model.addObject("lang", lang);
		model.addObject("flightInfoList", list);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		return model;
	}
	
	@RequestMapping("/managementReviewReportList.do")
    public ModelAndView managementReviewReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListReviewAll();
		
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : list){
			if(status.equalsIgnoreCase("all"))
				filteredList.add(report);
			else if(status.equalsIgnoreCase(report.getState()))
				filteredList.add(report);
		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", filteredList);
		
		return model;
	}
	
	@RequestMapping("/managementHazardIdentificationReportList.do")
    public ModelAndView managementHazardIdentificationReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListReviewAll();
		
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : list){
			if(status.equalsIgnoreCase("all"))
				filteredList.add(report);
			else if(status.equalsIgnoreCase(report.getState()))
				filteredList.add(report);
		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", filteredList);
		
		return model;
	}
	
	@RequestMapping("/managementRiskAnalysisReportList.do")
    public ModelAndView managementRiskAnalysisReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListRiskAnalysisHazardToBeAnalyzed();
		
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : list){
			if(status.equalsIgnoreCase("all"))
				filteredList.add(report);
			else if(status.equalsIgnoreCase(report.getState()))
				filteredList.add(report);
		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", filteredList);
		
		return model;
	}
	
	@RequestMapping("/managementRiskAssessmentReportList.do")
    public ModelAndView managementRiskAssessmentReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListRiskAssessmentHazardsToBeAssessed();
		
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : list){
			if(status.equalsIgnoreCase("all"))
				filteredList.add(report);
			else if(status.equalsIgnoreCase(report.getState()))
				filteredList.add(report);
		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", filteredList);
		
		return model;
	}
	
	@RequestMapping("/managementMitigationReportList.do")
    public ModelAndView managementMitigationReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListMitigationHazardsToBeMitigated();
		
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : list){
			if(status.equalsIgnoreCase("all"))
				filteredList.add(report);
			else if(status.equalsIgnoreCase(report.getState()))
				filteredList.add(report);
		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", filteredList);
		
		return model;
	}
	
	@RequestMapping("/reportToIdentifyList.do")
    public ModelAndView reportToIdentifyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Report> list = reportService.readReportListReviewAll();
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", list);
		
		return model;
	}
	
	@RequestMapping("/attachedFileList.do")
    public ModelAndView attachedFileList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/attachedFileList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/attachedFileListFN.do")
    public ModelAndView attachedFileListFN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/attachedFileListFN");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementReview.do")
    public ModelAndView managementReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/managementReview");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementRiskAnalysis.do")
    public ModelAndView managementRiskAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/managementRiskAnalysis");
		model.addObject("lang", lang); 
		return model;
	}
	
	@RequestMapping("/managementRiskAssessment.do")
    public ModelAndView managementRiskAssessment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/managementRiskAssessment");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementHazardIdentification.do")
    public ModelAndView managementHazardIdentification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/managementHazardIdentification");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementMitigation.do")
    public ModelAndView managementMitigation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/managementMitigation");
		model.addObject("lang", lang);
		return model;
	}
	
	
	
	@RequestMapping("/managementDetailMain.do")
    public ModelAndView managementDetailMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String activateTab = ServletRequestUtils.getStringParameter(request, "activateTab", "0");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailMain");
		
		System.out.println("activate Tab : "+activateTab);
		model.addObject("lang", lang);
		model.addObject("activateTab", activateTab);
		return model;
	}
	
	@RequestMapping("/managementDetailReview.do")
    public ModelAndView managementDetailReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailReview");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailReviewReport.do")
    public ModelAndView managementDetailReviewReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String category = ServletRequestUtils.getStringParameter(request, "category", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailReviewReport");
		model.addObject("lang", lang);
		model.addObject("report_no", report_no);
		model.addObject("category", category);
		model.addObject("type", type);
		return model;
	}
	
	@RequestMapping("/managementDetailHazardIdentification.do")
    public ModelAndView managementDetailHazardIdentification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailHazardIdentification");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysis.do")
    public ModelAndView managementDetailRiskAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysis");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisViewReport.do")
    public ModelAndView managementDetailRiskAnalysisViewReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisViewReport");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihood.do")
    public ModelAndView managementDetailRiskAnalysisLikelihood(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisLikelihood");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihoodLikelihoodList.do")
    public ModelAndView managementDetailRiskAnalysisLikelihoodLikelihoodList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisLikelihoodLikelihoodList");
		model.addObject("lang", lang);
		return model;
	}

	@RequestMapping("/managementDetailRiskAnalysisLikelihoodExistingControlsList.do")
    public ModelAndView managementDetailRiskAnalysisLikelihoodExistingControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisLikelihoodExistingControlsList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihoodNewControlsList.do")
    public ModelAndView managementDetailRiskAnalysisLikelihoodNewControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisLikelihoodNewControlsList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeveritySeverityList.do")
    public ModelAndView managementDetailRiskAnalysisSeveritySeverityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisSeveritySeverityList");
		model.addObject("lang", lang);
		return model;
	}

	@RequestMapping("/managementDetailRiskAnalysisSeverityExistingControlsList.do")
    public ModelAndView managementDetailRiskAnalysisSeverityExistingControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisSeverityExistingControlsList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeverityNewControlsList.do")
    public ModelAndView managementDetailRiskAnalysisSeverityNewControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisSeverityNewControlsList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeverity.do")
    public ModelAndView managementDetailRiskAnalysisSeverity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisSeverity");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAssessment.do")
    public ModelAndView managementDetailRiskAssessment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAssessment");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailMitigation.do")
    public ModelAndView managementDetailMitigation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailMitigation");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/managementDetailRegistered.do")
    public ModelAndView managementDetailRegistered(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRegistered");
		model.addObject("lang", lang);
		return model;
	}
	

	@RequestMapping("/createAircraftInformation.do")
    public ModelAndView createAircraftInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		System.out.println("Add aircraftInformation..");
		AircraftInfo ai = new AircraftInfo();
		ai.setManufacturer("BOEING");
		ai.setModel("B737-8000");
		ai.setSerial_no("51-11012");
		ai.setRegi_no("HL7229");
		ai.setNo_seat_crew(2);
		ai.setNo_seat_cabin(10);
		ai.setNo_seat_passenger(100);
		ai.setLast_inspection_type("1");
		ai.setLast_inspection_date("06/12/2012");
		reportService.createAircraftInformation(ai);
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/createFlightInformation.do")
    public ModelAndView createFlightInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String flightNo = ServletRequestUtils.getStringParameter(request, "flightNo", "KE1239");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		AircraftInfo ai = new AircraftInfo();
		ai.setSerial_no("51-11012");
		AircraftInfo rai = reportService.readAIrcraftInformation(ai);
		System.out.println("Retrieved ai : "+rai);
		
		System.out.println("Add flightInformation..");
		
		//String flightNo = "KE1238";
		FlightInfo fi = new FlightInfo();
		fi.setFlight_date("04/12/2012");
		fi.setFlight_no(flightNo);
		fi.setAirline("7C");
		fi.setRoute_from("GMP");
		fi.setRoute_to("KIX");
		fi.setRoute_diverted("ITM");
		fi.setFlight_type("SCHE");
		fi.setDom_int_type("DOM");
		fi.setCargo_operation("PASSENGER");
		fi.setNo_crew(5);
		fi.setNo_cabin(10);
		fi.setNo_passenger(100);
		fi.setAircraft_info_id(rai.getId());
		
		reportService.createFlightInfo(fi);
		
		FlightInfo rfi = reportService.readFlightInfo(fi);
		System.out.println("Retrieved fi : "+rai);
		
		Report report = new Report();
		
		report.setReport_date("07/13/2012");
		report.setReport_no("RP071312"+flightNo);
		report.setCrew_fatalities(10);
		report.setCrew_injuries(10);
		report.setCabin_fatalities(20);
		report.setCabin_injuries(20);
		report.setPassenger_fatalities(30);
		report.setPassenger_injuries(31);
		report.setAircraft_damage("NONE");
		report.setDelay_time("NONE");
		report.setState("NOT REPORTED");
		report.setFlight_info_id(rfi.getId());
		
		reportService.createReport(report);
		
		Report rreport = reportService.readReport(report);
		
		System.out.println("rreport:"+rreport);
		
		Vector<String> reportItemNameList = new Vector<String>();
		reportItemNameList.add(ReportItem.TYPE_BASIC);
		reportItemNameList.add(ReportItem.TYPE_TAXI_OUT);
		reportItemNameList.add(ReportItem.TYPE_TAKE_OFF);
		reportItemNameList.add(ReportItem.TYPE_CLIMB);
		reportItemNameList.add(ReportItem.TYPE_EN_ROUTE);
		reportItemNameList.add(ReportItem.TYPE_DECENT);
		reportItemNameList.add(ReportItem.TYPE_APPROACH);
		reportItemNameList.add(ReportItem.TYPE_LANDING);
		reportItemNameList.add(ReportItem.TYPE_TAXI_IN);
		
		for(String reportItemName : reportItemNameList){
			ReportItem ri = new ReportItem();
			ri.setType(reportItemName);
			ri.setReport_id(rreport.getId());
			ri.setStatus("NOTSUBMITTED");
			reportService.createReportItem(ri);
		}
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/createReport.do")
    public ModelAndView createReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		System.out.println("Add report..");
		
		Report report = new Report();
		
		report.setReport_date("07/13/2012");
		report.setReport_no("RP123123");
		report.setCrew_fatalities(10);
		report.setCrew_injuries(10);
		report.setCabin_fatalities(20);
		report.setCabin_injuries(20);
		report.setPassenger_fatalities(30);
		report.setPassenger_injuries(31);
		report.setAircraft_damage("NONE");
		report.setDelay_time("NONE");
		report.setState("NOT REPORTED");
		report.setFlight_info_id(2);
		
		reportService.createReport(report);
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("lang", lang);
		return model;
	}

	@RequestMapping("/fileUploadForm.do")
    public ModelAndView fileUploadForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String language = (String)request.getSession().getAttribute("lang");
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setType(report_item_type);
		ReportItem rri = reportService.readReportItem(ri);
		AttachedItem ai = new AttachedItem();
		ai.setReport_item_id(rri.getId());
		List<AttachedItem> itemList = reportService.readAttachedItemList(ai);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("report/file_upload_form");
		model.addObject("lang", lang);
		model.addObject("report_no", report_no);
		model.addObject("report_item_type", report_item_type);
		model.addObject("attachedItemList", itemList);
		return model;
	}
	
	
}
