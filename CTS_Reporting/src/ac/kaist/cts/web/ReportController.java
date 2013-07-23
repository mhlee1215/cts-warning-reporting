package ac.kaist.cts.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import language.LanguagePack;
import language.LanguageServiceImpl;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
import ac.kaist.cts.domain.Control;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Hazard;
import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.Likelihood;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;
import ac.kaist.cts.domain.ReportParent;
import ac.kaist.cts.domain.RiskOwner;
import ac.kaist.cts.domain.SelectItem;
import ac.kaist.cts.domain.Severity;
import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserHasReport;
import ac.kaist.cts.domain.UserInfo;
import ac.kaist.cts.service.HazardItemService;
import ac.kaist.cts.service.ReportService;
import ac.kaist.cts.service.UserService;

@Controller
public class ReportController {
	
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final ReportService reportService = null;
	
	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final HazardItemService hazardItemService = null;

	@RequestMapping("/report.do")
    public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		//System.out.println("report no : "+report_no);
		
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
	
	@RequestMapping("/reportBASIC.do")
    public ModelAndView reportBasic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String isReadOnly = ServletRequestUtils.getStringParameter(request, "isReadOnly", "N");
		System.out.println("report no : "+report_no);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		Report report = reportService.readReport(rp);
		
		FlightInfo fi = new FlightInfo();
		fi.setReport_id(report.getId());
		//fi.setId(report.getFlight_info_id());
		FlightInfo rfi = reportService.readFlightInfo(fi);
		
		AircraftInfo ai = new AircraftInfo();
		ai.setReport_id(report.getId());
		//ai.setId(rfi.getAircraft_info_id());
		AircraftInfo rai = reportService.readAIrcraftInformation(ai);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		
		
		SelectItem item = new SelectItem();
		item.setCategory("actype");
		List<SelectItem> acModelList = reportService.readSelectItemList(item);

		item.setCategory("manufacturer");
		List<SelectItem> manufacturerList = reportService.readSelectItemList(item);
		
		item.setCategory("aircraft_damage");
		List<SelectItem> aircraftDamageList = reportService.readSelectItemList(item);
		
		item.setCategory("injury");
		List<SelectItem> injuryList = reportService.readSelectItemList(item);
		
		item.setCategory("delay_time");
		List<SelectItem> delayTimeList = reportService.readSelectItemList(item);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(ReportItem.TYPE_BASIC);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		
		//UserHasReport uhr = new UserHasReport();
		//uhr.setReport_id(report.getId());
		//UserHasReport ruhr = userService.readUserHasReport(uhr);
		
		UserInfo uri = null;
		//if(ruhr != null){
			System.out.println("Found user map.");
			UserInfo ui = new UserInfo();
			ui.setReport_id(report.getId());
			//ui.setUser_id(ruhr.getUser_id());
			uri = userService.readUserInfo(ui);
//		}else{
//			System.out.println("Couldn't find user map. This report is newly writed.");
//			UserInfo ui = new UserInfo();
//			int user_id_no = (int) request.getSession().getAttribute("user_id_no");
//			ui.setUser_id(user_id_no);
//			uri = userService.readUserInfo(ui);
//		}
		//ui.setUser_id(reportItem.get)
		
		
		ModelAndView model = new ModelAndView("report/reportItem_basic");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("report_no", report_no);
		model.addObject("report", report);
		model.addObject("flight_info", rfi);
		model.addObject("aircraft_info", rai);
		model.addObject("user_info", uri);
		model.addObject("reportItem", reportItem);
		model.addObject("manufacturerList", manufacturerList);
		model.addObject("acModelList", acModelList);
		model.addObject("injuryList", injuryList);
		model.addObject("aircraftDamageList", aircraftDamageList);
		model.addObject("delayTimeList", delayTimeList);
		model.addObject("lang", lang);
		model.addObject("isReadOnly", isReadOnly);
		return model;
	}
	
	@RequestMapping("/reportBasicUpdate.do")
    public @ResponseBody String reportBasicUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		Report rp = new Report();
		reportService.updateReportItemBasic(rp, request);

		return "Update Success.";
	}
	
	@RequestMapping("/reportBasicSubmit.do")
    public @ResponseBody String reportBasicSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		
		Report rp = new Report();
		reportService.updateReportItemBasic(rp, request);
		
		Report rp2 = new Report();
		rp2.setReport_no(report_no);
		Report rrp = reportService.readReport(rp2);
		
		ReportItem ri = new ReportItem();
		ri.setReport_id(rrp.getId());
		ri.setStatus(ReportItem.STATE_SUBMITTED);
		reportService.updateReportItem(ri, request, ReportItem.TYPE_BASIC);
		
		return "Submit Success.";
	}
	
	@RequestMapping("/reportItem.do")
    public ModelAndView reportTaxiOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		System.out.println("report no : "+report_no);
		
		ReportItem reportItemQuery = new ReportItem();
		reportItemQuery.setReport_no(report_no);
		reportItemQuery.setType(report_item_type);
		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_generic");
		model.addObject("report_no", report_no);
		model.addObject("report_item_type", report_item_type);
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("reportItem", reportItem);
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportItemUpdate.do")
    public @ResponseBody String reportItemUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		Report rrp = reportService.readReport(rp);
		
		ReportItem ri = new ReportItem();
		ri.setReport_id(rrp.getId());
		reportService.updateReportItem(ri, request, report_item_type);

		return "Save Success.";
	}
	
	@RequestMapping("/reportItemSubmit.do")
    public @ResponseBody String reportItemSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		Report rrp = reportService.readReport(rp);
		
		rp.setReport_state(Report.STATUS_REPORTED);
		rp.setManagement_state(Report.STATUS_REVIEW);
		reportService.updateReport(rp);
		
		ReportParent reportParent = new ReportParent();
		reportParent.setId(rrp.getReport_parent_id());
		
		reportParent = reportService.readReportParent(reportParent);
		
		reportParent.setReport_state(Report.STATUS_REPORTED);
		
		if(reportParent.getManagement_state().equals(Report.STATUS_NOT_REPORTED))
			reportParent.setManagement_state(Report.STATUS_REVIEW);
		
		reportService.updateReportParent(reportParent);
		
		ReportItem ri = new ReportItem();
		ri.setReport_id(rrp.getId());
		ri.setStatus(ReportItem.STATE_SUBMITTED);
		reportService.updateReportItem(ri, request, report_item_type);
		
		return "Submit success.";
	}
	
//	@RequestMapping("/reportTAKE-OFF.do")
//    public ModelAndView reportTakeOff(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_TAKE_OFF);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_TAKE_OFF);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportCLIMB.do")
//    public ModelAndView reportClimb(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_CLIMB);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_CLIMB);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportEN-ROUTE.do")
//    public ModelAndView reportEnRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_EN_ROUTE);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_EN_ROUTE);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportDECENT.do")
//    public ModelAndView reportDecent(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_DECENT);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_DECENT);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportAPPROACH.do")
//    public ModelAndView reportApproach(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_APPROACH);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_APPROACH);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportLANDING.do")
//    public ModelAndView reportLanding(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_LANDING);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_LANDING);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
//	
//	@RequestMapping("/reportTAXI-IN.do")
//    public ModelAndView reportTaxiIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String language = (String)request.getSession().getAttribute("lang");
//		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
//		System.out.println("report no : "+report_no);
//		
//		ReportItem reportItemQuery = new ReportItem();
//		reportItemQuery.setReport_no(report_no);
//		reportItemQuery.setType(ReportItem.TYPE_TAXI_IN);
//		ReportItem reportItem = reportService.readReportItem(reportItemQuery);
//		
//		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
//		
//		ModelAndView model = new ModelAndView("report/reportItem_generic");
//		model.addObject("report_no", report_no);
//		model.addObject("report_item_type", ReportItem.TYPE_TAXI_IN);
//		model.addObject("page_title", lang.getStringPilotReport());
//		model.addObject("reportItem", reportItem);
//		model.addObject("lang", lang);
//		return model;
//	}
	
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
		String user_type = (String)request.getSession().getAttribute("user_type");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		FlightInfo fi = new FlightInfo();
		fi.setReport_type(user_type);
		List<FlightInfo> list = reportService.readFLightInfoList(fi);
			
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
		
		//List<ReportParent> list = null;
		//reportService.readReportParentList(reportParent);
		
		List<ReportParent> list = null;
		if(status.equalsIgnoreCase("all")){
			ReportParent rp = new ReportParent();
			rp.setReport_state(Report.STATUS_REPORTED);
			list = reportService.readReportParentList(rp);
		}else{
			ReportParent rp = new ReportParent();
			rp.setManagement_state(status);
			list = reportService.readReportParentList(rp);
		}
					
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", list);
		
		return model;
	}
	
	@RequestMapping("/managementHazardIdentificationReportList.do")
    public ModelAndView managementHazardIdentificationReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<ReportParent> list = null;

		ReportParent rp = new ReportParent();
		rp.setManagement_state(status);
		list = reportService.readReportParentList(rp);
	
		
//		
//		List<Report> list = reportService.readReportListReviewAll();
//		
//		List<Report> filteredList = new ArrayList<Report>();
//		
//		for (Report report : list){
//			if(status.equalsIgnoreCase("all"))
//				filteredList.add(report);
//			else if(status.equalsIgnoreCase(report.getReport_state()))
//				filteredList.add(report);
//		}
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("reports", list);
		
		return model;
	}
	
	@RequestMapping("/managementRiskAnalysisReportList.do")
    public ModelAndView managementRiskAnalysisReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", Hazard.STATUS_IDENTIFIED);
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Hazard h = new Hazard();
		h.setState(status);
		List<Hazard> list = reportService.readHazardList(h);
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("hazards", list);
		
		return model;
	}
	
	@RequestMapping("/managementRiskAssessmentReportList.do")
    public ModelAndView managementRiskAssessmentReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Hazard h = new Hazard();
		h.setState(status);
		List<Hazard> list = reportService.readHazardList(h);
				
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("hazards", list);
		
		return model;
	}
	
	@RequestMapping("/managementMitigationReportList.do")
    public ModelAndView managementMitigationReportList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String status = ServletRequestUtils.getStringParameter(request, "status", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Hazard h = new Hazard();
		h.setState(status);
		List<Hazard> list = reportService.readHazardList(h);
			
		ModelAndView model = new ModelAndView("management/reportToIdentifyList2");
		model.addObject("lang", lang);
		model.addObject("page", 1);
		model.addObject("total_pages", 1);
		model.addObject("total_count", list.size());
		model.addObject("hazards", list);
		
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
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
	
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailMain");
		
		System.out.println("activate Tab : "+activateTab);
		model.addObject("lang", lang);
		model.addObject("activateTab", activateTab);
		model.addObject("report_no", report_no);
		model.addObject("hazard_no", hazard_no);
		return model;
	}
	
	@RequestMapping("/managementDetailReview.do")
    public ModelAndView managementDetailReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		
		System.out.println("<<report_no : "+report_no);
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp.setManagement_state(Report.STATUS_REVIEW);
		List<Report> rpList = reportService.readReportChildrenList(rp);
		
		rp.setManagement_state(Report.STATUS_ACCEPTED);
		List<Report> rpList2 = reportService.readReportChildrenList(rp);
		
		rpList.addAll(rpList2);
		
		
		List<String> userTypeList = User.getUserTypeList();
		Map<String, String> userTypeNameList = User.getUserTypeNameMap();
		
		List<String> reportItemNameList = ReportItem.getReportItemNameList();
		
		
		Map<String, List<Report> > classifiedRpList = new TreeMap<String, List<Report> >();
		for(String userType : userTypeList){
			classifiedRpList.put(userType, new ArrayList<Report>());
		}
		
		Map<String, Map<String, List<ReportItem> > > reportItemList = new TreeMap<String, Map<String, List<ReportItem> > >();
		Map<String, Integer> reportItemSize = new TreeMap<String, Integer>();
		
		for(String reportTypeName : reportItemNameList){
			System.out.println("reportTypeName: "+reportTypeName);
			reportItemList.put(reportTypeName, new TreeMap<String, List<ReportItem> >());
			reportItemSize.put(reportTypeName, 0);
			for(String userType : userTypeList){
				reportItemList.get(reportTypeName).put(userType, new ArrayList<ReportItem>());
			}
		}
		
		for(Report r : rpList){
			classifiedRpList.get(r.getType().substring(0, 1)).add(r);
			
			ReportItem ri = new ReportItem();
			ri.setReport_no(r.getReport_no());
			ri.setStatus(ReportItem.STATE_SUBMITTED);
			List<ReportItem> riList = reportService.readReportItemList(ri);
			String userType = r.getType().substring(0, 1);
			String userTypeIndex = r.getType().substring(1, 2);
			for(ReportItem reportItem : riList){
				if(!ReportItem.TYPE_BASIC.equalsIgnoreCase(reportItem.getType())){
					reportItem.setType_index(userTypeIndex);
					//System.out.println(reportItem.getType()+": "+reportItemList.get(reportItem.getType()));
					reportItemList.get(reportItem.getType()).get(userType).add(reportItem);
					reportItemSize.put(reportItem.getType(), reportItemSize.get(reportItem.getType())+1);
				}
			}
		}
				
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailReview");
		model.addObject("lang", lang);
		model.addObject("report_no", report_no);
		model.addObject("reportItemNameList", reportItemNameList);
		model.addObject("reportItemSize", reportItemSize);
		model.addObject("userTypeList", userTypeList);
		model.addObject("userTypeNameList", userTypeNameList);
		model.addObject("classifiedRpList", classifiedRpList);
		model.addObject("reportItemList", reportItemList);
		
		return model;
	}
	
	@RequestMapping("/managementDetailReviewReport.do")
    public ModelAndView managementDetailReviewReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String category = ServletRequestUtils.getStringParameter(request, "category", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp = reportService.readReport(rp);
		rp.setType(rp.getType().substring(0, 1));
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setType(type);
		ri = reportService.readReportItem(ri);
		
		Map<String, String> userTypeNameList = User.getUserTypeNameMap();
		
		Map<String, String> statusReviewMap = new TreeMap<String, String>();
		statusReviewMap.put("ACCEPTED", ReportItem.STATUS_REVIEW_ACCEPTED);
		statusReviewMap.put("REJECTED", ReportItem.STATUS_REVIEW_REJECTED);
		statusReviewMap.put("NEEDINVESTIGATION", ReportItem.STATUS_REVIEW_NEED_INVESTIGATION);
		
		SelectItem item = new SelectItem();
		item.setCategory("priority");
		List<SelectItem> priorityList = reportService.readSelectItemList(item);
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailReviewReport");
		model.addObject("lang", lang);
		model.addObject("report_no", report_no);
		model.addObject("type", type);
		model.addObject("category", category);
		model.addObject("report", rp);
		model.addObject("reportItem", ri);
		model.addObject("userTypeNameList", userTypeNameList);
		model.addObject("statusReviewMap", statusReviewMap);
		model.addObject("priorityList", priorityList);
		
		return model;
	}
	
	@RequestMapping("/managementDetailReviewReportUpdate.do")
    public @ResponseBody String managementDetailReviewReportUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp = reportService.readReport(rp);
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setReport_id(rp.getId());
		ri.setType(type);
		reportService.updateReportItemReview(ri, request, type);
		
		
		return "Update Success";
	}
	
	@RequestMapping("/managementDetailReviewReportSubmit.do")
    public @ResponseBody String managementDetailReviewReportSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp = reportService.readReport(rp);
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setReport_id(rp.getId());
		ri.setType(type);
		ri.setStatus_review(ReportItem.STATE_SUBMITTED);
		ri.setStatus_hazard_id(ReportItem.STATE_NOT_SUBMITTED);
		
		reportService.updateReportItemReview(ri, request, type);
		
		ReportItem rr = new ReportItem();
		rr.setReport_parent_id(rp.getReport_parent_id());
		rr.setStatus(ReportItem.STATE_SUBMITTED);
		List<ReportItem> riListAll = reportService.readReportItemListAll(rr);
		
		rr = new ReportItem();
		rr.setReport_parent_id(rp.getReport_parent_id());
		rr.setStatus(ReportItem.STATE_SUBMITTED);
		rr.setStatus_review(ReportItem.STATE_SUBMITTED);
		List<ReportItem> riListAllSubmitted = reportService.readReportItemListAll(rr);
		
		boolean isAccept = false;
		boolean isInvestigation = false;
		//If all submitted
		if(riListAll.size() == riListAllSubmitted.size()){
			for(ReportItem reportItem : riListAll){
				if(ReportItem.STATUS_REVIEW_NEED_INVESTIGATION.equals(reportItem.getStatus_determine()))
					isInvestigation = true;
				else if(ReportItem.STATUS_REVIEW_ACCEPTED.equals(reportItem.getStatus_determine()))
					isAccept = true;
			}
			
			ReportParent reportParent = new ReportParent();
			reportParent.setId(rp.getReport_parent_id());
			Report report = new Report();
			report.setReport_no(report_no);
			if(isInvestigation){
				reportParent.setManagement_state(Report.STATUS_IN_INVESTIGATION);
				report.setManagement_state(Report.STATUS_IN_INVESTIGATION);
			}else if(isAccept){
				reportParent.setManagement_state(Report.STATUS_ACCEPTED);
				report.setManagement_state(Report.STATUS_ACCEPTED);
			}else{
				reportParent.setManagement_state(Report.STATUS_REJECTED);
				report.setManagement_state(Report.STATUS_REJECTED);
			}
			
			reportService.updateReportParent(reportParent);
			reportService.updateReport(report);
		}

		return "Submit Success";
	}
	
	@RequestMapping("/managementDetailHazardIdentification.do")
    public ModelAndView managementDetailHazardIdentification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		
		System.out.println("<<report_no : "+report_no);
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp.setManagement_state(Report.STATUS_ACCEPTED);
		List<Report> rpList = reportService.readReportChildrenList(rp);
		
		List<String> userTypeList = User.getUserTypeList();
		Map<String, String> userTypeNameList = User.getUserTypeNameMap();
		
		List<String> reportItemNameList = ReportItem.getReportItemNameList();
		
		
		Map<String, List<Report> > classifiedRpList = new TreeMap<String, List<Report> >();
		for(String userType : userTypeList){
			classifiedRpList.put(userType, new ArrayList<Report>());
		}
		
		Map<String, Map<String, List<ReportItem> > > reportItemList = new TreeMap<String, Map<String, List<ReportItem> > >();
		Map<String, Integer> reportItemSize = new TreeMap<String, Integer>();
		
		for(String reportTypeName : reportItemNameList){
			System.out.println("reportTypeName: "+reportTypeName);
			reportItemList.put(reportTypeName, new TreeMap<String, List<ReportItem> >());
			reportItemSize.put(reportTypeName, 0);
			for(String userType : userTypeList){
				reportItemList.get(reportTypeName).put(userType, new ArrayList<ReportItem>());
			}
		}
		
		for(Report r : rpList){
			classifiedRpList.get(r.getType().substring(0, 1)).add(r);
			
			ReportItem ri = new ReportItem();
			ri.setReport_no(r.getReport_no());
			ri.setStatus(ReportItem.STATE_SUBMITTED);
			List<ReportItem> riList = reportService.readReportItemList(ri);
			String userType = r.getType().substring(0, 1);
			String userTypeIndex = r.getType().substring(1, 2);
			for(ReportItem reportItem : riList){
				if(!ReportItem.TYPE_BASIC.equalsIgnoreCase(reportItem.getType())){
					reportItem.setType_index(userTypeIndex);
					reportItemList.get(reportItem.getType()).get(userType).add(reportItem);
					reportItemSize.put(reportItem.getType(), reportItemSize.get(reportItem.getType())+1);
				}
			}
		}
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailHazardIdentification");
		model.addObject("lang", lang);
		model.addObject("report_no", report_no);
		model.addObject("reportItemNameList", reportItemNameList);
		model.addObject("reportItemSize", reportItemSize);
		model.addObject("userTypeList", userTypeList);
		model.addObject("userTypeNameList", userTypeNameList);
		model.addObject("classifiedRpList", classifiedRpList);
		model.addObject("reportItemList", reportItemList);
		return model;
	}
	
	@RequestMapping("/managementDetailHazardIdentificationReport.do")
    public ModelAndView managementDetailHazardIdentificationReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String isreadonly = ServletRequestUtils.getStringParameter(request, "isreadonly", "N");
		String category = ServletRequestUtils.getStringParameter(request, "category", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		ReportParent reportParent = new ReportParent();
		ReportItem ri = new ReportItem();
		
		if(report_no.isEmpty()){
			if(!hazard_no.isEmpty()){
				Hazard h = new Hazard();
				h.setHazard_no(hazard_no);
				h = reportService.readHazard(h);
				
				
				ri.setId(h.getReport_item_id());
				ri = reportService.readReportItem(ri); 
				
				rp.setId(ri.getReport_id());
				rp = reportService.readReport(rp);
				
				reportParent.setId(rp.getReport_parent_id());
				reportParent = reportService.readReportParent(reportParent);
			}
		}else{
			rp.setReport_no(report_no);
			rp = reportService.readReport(rp);
			rp.setType(rp.getType().substring(0, 1));
			
			
			reportParent.setId(rp.getReport_parent_id());
			reportParent = reportService.readReportParent(reportParent);
			
			
			ri.setReport_no(report_no);
			ri.setType(type);
			ri = reportService.readReportItem(ri);
		}
		
		
		
		
		Map<String, String> userTypeNameList = User.getUserTypeNameMap();
		
		Map<String, String> statusReviewMap = new TreeMap<String, String>();
		statusReviewMap.put("ACCEPTED", ReportItem.STATUS_REVIEW_ACCEPTED);
		statusReviewMap.put("REJECTED", ReportItem.STATUS_REVIEW_REJECTED);
		statusReviewMap.put("NEEDINVESTIGATION", ReportItem.STATUS_REVIEW_NEED_INVESTIGATION);
			
		Map<String, String> priorityColorMap = SelectItem.getPriorityColorMap();
		
		SelectItem item = new SelectItem();
		item.setCategory("priority");
		List<SelectItem> priorityList = reportService.readSelectItemList(item);
		
		Map<String, String> priorityNameMap = new TreeMap<String, String>();
		for(SelectItem selectItem : priorityList)
			priorityNameMap.put(selectItem.getValue(), selectItem.getName());
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailHazardIdentificationReport");
		model.addObject("lang", lang);
		model.addObject("report_parent_no", reportParent.getReport_no());
		model.addObject("report_no", rp.getReport_no());
		model.addObject("type", ri.getType());
		model.addObject("category", rp.getType());
		model.addObject("report", rp);
		model.addObject("reportItem", ri);
		model.addObject("userTypeNameList", userTypeNameList);
		model.addObject("statusReviewMap", statusReviewMap);
		model.addObject("priorityColorMap", priorityColorMap);
		model.addObject("priorityNameMap", priorityNameMap);
		model.addObject("hazard_no", hazard_no);
		model.addObject("isreadonly", isreadonly);
		
		return model;
	}
	
	@RequestMapping("/managementDetailHazardIdentificationUpdate.do")
    public @ResponseBody String managementDetailHazardIdentificationUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp = reportService.readReport(rp);
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setReport_id(rp.getId());
		ri.setType(type);
		reportService.updateReportItemReview(ri, request, type);
		
		
		return "Update Success";
	}
	
	@RequestMapping("/managementDetailHazardIdentificationSubmit.do")
    public @ResponseBody String managementDetailHazardIdentificationSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Report rp = new Report();
		rp.setReport_no(report_no);
		rp = reportService.readReport(rp);
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setReport_id(rp.getId());
		ri.setType(type);
		ri.setStatus_hazard_id(ReportItem.STATE_SUBMITTED);
		reportService.updateReportItemReview(ri, request, type);
		
		ri = reportService.readReportItem(ri);
		Hazard h = new Hazard();
		h.setReport_item_id(ri.getId());
		h.setState(Hazard.STATUS_IDENTIFIED);
		reportService.updateHazard(h);
		
		
		return "Submit Success";
	}
	
	@RequestMapping("/managementDetailRiskAnalysis.do")
    public ModelAndView managementDetailRiskAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		System.out.println("hazard_no  :"+hazard_no);
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysis");
		model.addObject("lang", lang);
		model.addObject("hazard_no", hazard_no);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisViewReport.do")
    public ModelAndView managementDetailRiskAnalysisViewReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisViewReport");
		model.addObject("lang", lang);
		model.addObject("hazard_no", hazard_no);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihood.do")
    public ModelAndView managementDetailRiskAnalysisLikelihood(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		SelectItem si = new SelectItem();
		si.setCategory("likelihood");
		List<SelectItem> likelihoodList = reportService.readSelectItemList(si);
			
		Hazard h = new Hazard();
		h.setHazard_no(hazard_no);
		h = reportService.readHazard(h);
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisLikelihood");
		model.addObject("lang", lang);
		model.addObject("hazard_no", hazard_no);
		model.addObject("likelihoodList", likelihoodList);
		model.addObject("hazard", h);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihoodUpdate.do")
    public @ResponseBody String managementDetailRiskAnalysisLikelihoodUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		String issubmit = ServletRequestUtils.getStringParameter(request, "issubmit", "");
		
		Hazard hz = new Hazard();
		hz.setHazard_no(hazard_no);
		if(issubmit.equalsIgnoreCase("Y")){
			if(type.equalsIgnoreCase("likelihood"))
				hz.setState_likelihood(Hazard.STATE_SUBMITTED);
			else if(type.equalsIgnoreCase("severity"))
				hz.setState_severity(Hazard.STATE_SUBMITTED);
		}
		reportService.managementDetailRiskAnalysisUpdate(hz, request, type);
		
		hz = reportService.readHazard(hz);
		
		if(Hazard.STATE_SUBMITTED.equals(hz.getState_likelihood()) && Hazard.STATE_SUBMITTED.equals(hz.getState_severity())){
			Hazard hazard = new Hazard();
			hazard.setHazard_no(hazard_no);
			hazard.setState(Hazard.STATUS_ANALYZED);
			reportService.updateHazard(hazard);
		}
		
		if("y".equalsIgnoreCase(issubmit))
			return "Submit Success.";
		else if("n".equalsIgnoreCase(issubmit))
			return "Update Success.";
		else
			return "parameter missed.";
	}
	
	@RequestMapping("/managementDetailRiskAnalysisLikelihoodLikelihoodList.do")
    public ModelAndView managementDetailRiskAnalysisLikelihoodLikelihoodList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		int year = ServletRequestUtils.getIntParameter(request, "year", 3);
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Likelihood> likelihoodList = reportService.readLikelihoodList(year);
		
		ModelAndView model = new ModelAndView("management/detail/likelihoodsList");
		model.addObject("lang", lang);
		model.addObject("likelihoodList", likelihoodList);
		
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
	
	@RequestMapping("/managementDetailRiskAnalysisSeverity.do")
    public ModelAndView managementDetailRiskAnalysisSeverity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		SelectItem si = new SelectItem();
		si.setCategory("severity");
		List<SelectItem> severityList = reportService.readSelectItemList(si);
		
		Hazard h = new Hazard();
		h.setHazard_no(hazard_no);
		h = reportService.readHazard(h);
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysisSeverity");
		model.addObject("lang", lang);
		model.addObject("hazard_no", hazard_no);
		model.addObject("severityList", severityList);
		model.addObject("hazard", h);
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeverityUpdate.do")
    public @ResponseBody String managementDetailRiskAnalysisSeverityUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Hazard hz = new Hazard();
		reportService.hazardViewContentUpdate(hz, request);
		return "Update Success.";
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeveritySeverityList.do")
    public ModelAndView managementDetailRiskAnalysisSeveritySeverityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		int year = ServletRequestUtils.getIntParameter(request, "year", 3);
		
		List<Severity> severityList = reportService.readSeverityList(year);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/severitiesList");
		model.addObject("lang", lang);
		model.addObject("severityList", severityList);
		
		return model;
	}

	@RequestMapping("/managementDetailRiskAnalysisSeverityExistingControlsList.do")
    public ModelAndView managementDetailRiskAnalysisSeverityExistingControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		int year = ServletRequestUtils.getIntParameter(request, "year", 3);
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Control> controlList = reportService.readExistingControlList(year);
		System.out.println(controlList);
		ModelAndView model = new ModelAndView("management/detail/controlsList");
		model.addObject("lang", lang);
		model.addObject("controlList", controlList);
		
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysisSeverityNewControlsList.do")
    public ModelAndView managementDetailRiskAnalysisSeverityNewControlsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		int year = ServletRequestUtils.getIntParameter(request, "year", 1);
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		List<Control> controlList = reportService.readNewControlList(year);
		
		ModelAndView model = new ModelAndView("management/detail/controlsList");
		model.addObject("lang", lang);
		model.addObject("controlList", controlList);
		return model;
	}
	

	
	@RequestMapping("/managementDetailRiskAssessment.do")
    public ModelAndView managementDetailRiskAssessment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		
		Hazard h = new Hazard();
		h.setHazard_no(hazard_no);
		h = reportService.readHazard(h);
		
		
		SelectItem item = new SelectItem();
		item.setCategory("likelihood");
		List<SelectItem> likelihoodList = reportService.readSelectItemList(item);
		
		Map<String, String> likelihoodNameMap = new TreeMap<String, String>();
		for(SelectItem selectItem : likelihoodList)
			likelihoodNameMap.put(selectItem.getValue(), selectItem.getName());
		
		item.setCategory("severity");
		List<SelectItem> severityList = reportService.readSelectItemList(item);
		
		Map<String, String> severityNameMap = new TreeMap<String, String>();
		for(SelectItem selectItem : severityList)
			severityNameMap.put(selectItem.getValue(), selectItem.getName());
		
		String lii = h.getLikelihood_initial_likelihood();
		if(lii.equals("0"))
			lii = "1";
		int lii_index = Integer.parseInt(lii);
		String lri = h.getLikelihood_residual_likelihood();
		if(lri.equals("0"))
			lri = "1";
		int lri_index = Integer.parseInt(lri);
		String sii = h.getSeverity_initial_likelihood();
		if(sii.equals("0"))
			sii = "1";
		int sii_index = Integer.parseInt(sii);
		String sri = h.getSeverity_residual_likelihood();
		if(sri.equals("0"))
			sri = "1";
		int sri_index = Integer.parseInt(sri);
		
		lii_index = 6- lii_index;
		lri_index = 6- lri_index;
		
//		System.out.println("h: "+h);
//		System.out.println("lii_index :"+lii_index);
//		System.out.println("sii_index :"+sii_index);
//		System.out.println("lri_index :"+lri_index);
//		System.out.println("sri_index :"+sri_index);
		
		int initial_matrix_value = lii_index + sii_index;
		int residual_matrix_value = lri_index + sri_index;
		
		String initial_acceptable_text = "";
		String residual_acceptable_text = "";
		
		String initial_acceptable_color = "";
		String residual_acceptable_color = "";
		
		String initial_text_color = "";
		String residual_text_color = "";
		
		String initial_risk_text = "";
		String residual_risk_text = "";
		
		//Green zone
		if(initial_matrix_value >= 2 && initial_matrix_value <= 4){
			initial_acceptable_text = "Acceptable";
			initial_acceptable_color = "Green";
			initial_risk_text = "Low Risk";
			initial_text_color = "white";
		}
		//Yellow zone
		else if(initial_matrix_value == 5){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Yellow";
			initial_risk_text = "Moderate-B";
			initial_text_color = "black";
		}
		//Orange zone
		else if(initial_matrix_value >= 6 && initial_matrix_value <= 7){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Orange";
			initial_risk_text = "Moderate-A";
			initial_text_color = "white";
		}
		//Red zone
		else if(initial_matrix_value > 7){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Red";
			initial_risk_text = "High Risk";
			initial_text_color = "white";
		}
		
		//Green zone
		if(residual_matrix_value >= 2 && residual_matrix_value <= 4){
			residual_acceptable_text = "Acceptable";
			residual_acceptable_color = "Green";
			residual_risk_text = "Low Risk";
			residual_text_color = "white";
		}
		//Yellow zone
		else if(residual_matrix_value == 5){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Yellow";
			residual_risk_text = "Moderate-B";
			residual_text_color = "black";
		}
		//Orange zone
		else if(residual_matrix_value >= 6 && residual_matrix_value <= 7){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Orange";
			residual_risk_text = "Moderate-A";
			residual_text_color = "white";
		}
		//Red zone
		else if(residual_matrix_value > 7){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Red";
			residual_risk_text = "High Risk";
			residual_text_color = "white";
		}
		
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAssessment");
		model.addObject("lang", lang);
		model.addObject("hazard_no", hazard_no);
		model.addObject("hazard", h);
		model.addObject("likelihoodNameMap", likelihoodNameMap);
		model.addObject("severityNameMap", severityNameMap);
		model.addObject("initial_acceptable_text", initial_acceptable_text);
		model.addObject("residual_acceptable_text", residual_acceptable_text);
		model.addObject("initial_acceptable_color", initial_acceptable_color);
		model.addObject("residual_acceptable_color", residual_acceptable_color);
		model.addObject("initial_risk_text", initial_risk_text);
		model.addObject("residual_risk_text", residual_risk_text);
		model.addObject("initial_text_color", initial_text_color);
		model.addObject("residual_text_color", residual_text_color);
		
		
		return model;
	}
	
	
	@RequestMapping("/managementDetailRiskAssessmentUpdate.do")
    public @ResponseBody String managementDetailRiskAssessmentUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String issubmit = ServletRequestUtils.getStringParameter(request, "issubmit", "");
		
		Hazard hz = new Hazard();
		hz.setHazard_no(hazard_no);
		if(issubmit.equalsIgnoreCase("Y")){
			hz.setState_assessment(Hazard.STATE_SUBMITTED);
			hz.setState(Hazard.STATUS_RISK);
		}
		reportService.managementDetailRiskAssessmentUpdate(hz, request);
		
		if("y".equalsIgnoreCase(issubmit))
			return "Submit Success.";
		else if("n".equalsIgnoreCase(issubmit))
			return "Update Success.";
		else
			return "parameter missed.";
	}
	
	@RequestMapping("/managementDetailViewStandardt.do")
    public ModelAndView managementDetailViewStandardt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");


		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/viewStandard");
		model.addObject("lang", lang);	
		return model;
	}
	
	@RequestMapping("/managementDetailMitigation.do")
    public ModelAndView managementDetailMitigation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		Hazard h = new Hazard();
		h.setHazard_no(hazard_no);
		h = reportService.readHazard(h);
		
		SelectItem item = new SelectItem();
		item.setCategory("likelihood");
		List<SelectItem> likelihoodList = reportService.readSelectItemList(item);
		
		Map<String, String> likelihoodNameMap = new TreeMap<String, String>();
		for(SelectItem selectItem : likelihoodList)
			likelihoodNameMap.put(selectItem.getValue(), selectItem.getName());
		
		item.setCategory("severity");
		List<SelectItem> severityList = reportService.readSelectItemList(item);
		
		Map<String, String> severityNameMap = new TreeMap<String, String>();
		for(SelectItem selectItem : severityList)
			severityNameMap.put(selectItem.getValue(), selectItem.getName());
		
		item = new SelectItem();
		item.setCategory("division");
		List<SelectItem> divisionList = reportService.readSelectItemList(item);
		
		RiskOwner ro = new RiskOwner();
		ro.setHazard_id(h.getId());
		List<RiskOwner> riskOwnerList = reportService.readRiskOwnerList(ro);
		
		for(int i = riskOwnerList.size(); i < 2 ; i++){
			RiskOwner riskOwner = new RiskOwner();
			riskOwner.setHazard_id(h.getId());
			reportService.createRiskOwner(riskOwner);
			riskOwnerList.add(riskOwner);
		}
		
		String lii = h.getLikelihood_initial_likelihood();
		if(lii.equals("0"))
			lii = "1";
		int lii_index = Integer.parseInt(lii);
		String lri = h.getLikelihood_residual_likelihood();
		if(lri.equals("0"))
			lri = "1";
		int lri_index = Integer.parseInt(lii);
		String sii = h.getSeverity_initial_likelihood();
		if(sii.equals("0"))
			sii = "1";
		int sii_index = Integer.parseInt(lii);
		String sri = h.getSeverity_residual_likelihood();
		if(sri.equals("0"))
			sri = "1";
		int sri_index = Integer.parseInt(lii);
		
		lii_index = 6- lii_index;
		lri_index = 6- lri_index;
		
		int initial_matrix_value = lii_index + sii_index;
		int residual_matrix_value = lri_index + sri_index;
		
		String initial_acceptable_text = "";
		String residual_acceptable_text = "";
		
		String initial_acceptable_color = "";
		String residual_acceptable_color = "";
		
		String initial_text_color = "";
		String residual_text_color = "";
		
		String initial_risk_text = "";
		String residual_risk_text = "";
		
		//Green zone
		if(initial_matrix_value >= 2 && initial_matrix_value <= 4){
			initial_acceptable_text = "Acceptable";
			initial_acceptable_color = "Green";
			initial_risk_text = "Low Risk";
			initial_text_color = "white";
		}
		//Yellow zone
		else if(initial_matrix_value == 5){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Yellow";
			initial_risk_text = "Moderate-B";
			initial_text_color = "black";
		}
		//Orange zone
		else if(initial_matrix_value >= 6 && initial_matrix_value <= 7){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Orange";
			initial_risk_text = "Moderate-A";
			initial_text_color = "white";
		}
		//Red zone
		else if(initial_matrix_value > 7){
			initial_acceptable_text = "Unacceptable";
			initial_acceptable_color = "Red";
			initial_risk_text = "High Risk";
			initial_text_color = "white";
		}
		
		//Green zone
		if(residual_matrix_value >= 2 && residual_matrix_value <= 4){
			residual_acceptable_text = "Acceptable";
			residual_acceptable_color = "Green";
			residual_risk_text = "Low Risk";
			residual_text_color = "white";
		}
		//Yellow zone
		else if(residual_matrix_value == 5){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Yellow";
			residual_risk_text = "Moderate-B";
			residual_text_color = "black";
		}
		//Orange zone
		else if(residual_matrix_value >= 6 && residual_matrix_value <= 7){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Orange";
			residual_risk_text = "Moderate-A";
			residual_text_color = "white";
		}
		//Red zone
		else if(residual_matrix_value > 7){
			residual_acceptable_text = "Unacceptable";
			residual_acceptable_color = "Red";
			residual_risk_text = "High Risk";
			residual_text_color = "white";
		}
		
		ModelAndView model = new ModelAndView("management/detail/managementDetailMitigation");
		model.addObject("lang", lang);
		model.addObject("divisionList", divisionList);
		model.addObject("hazard", h);
		model.addObject("riskOwnerList", riskOwnerList);
		model.addObject("hazard_no", hazard_no);
		model.addObject("likelihoodNameMap", likelihoodNameMap);
		model.addObject("severityNameMap", severityNameMap);
		model.addObject("initial_acceptable_text", initial_acceptable_text);
		model.addObject("residual_acceptable_text", residual_acceptable_text);
		model.addObject("initial_acceptable_color", initial_acceptable_color);
		model.addObject("residual_acceptable_color", residual_acceptable_color);
		model.addObject("initial_risk_text", initial_risk_text);
		model.addObject("residual_risk_text", residual_risk_text);
		model.addObject("initial_text_color", initial_text_color);
		model.addObject("residual_text_color", residual_text_color);
		
		return model;
	}
	
	@RequestMapping("/managementDetailMitigationUpdate.do")
    public @ResponseBody String managementDetailMitigationUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String issubmit = ServletRequestUtils.getStringParameter(request, "issubmit", "");
		
		Hazard hz = new Hazard();
		hz.setHazard_no(hazard_no);
		if(issubmit.equalsIgnoreCase("Y")){
			hz.setState_mitigation(Hazard.STATE_SUBMITTED);
			hz.setState(Hazard.STATUS_MITIGATED);
		}
		reportService.managementDetailMitigationUpdate(hz, request);
		
		if("y".equalsIgnoreCase(issubmit))
			return "Submit Success.";
		else if("n".equalsIgnoreCase(issubmit))
			return "Update Success.";
		else
			return "parameter missed.";
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
		//fi.setAircraft_info_id(rai.getId());
		
		reportService.createFlightInfo(fi);
		
		FlightInfo rfi = reportService.readFlightInfo(fi);
		System.out.println("Retrieved fi : "+rai);
		
		Report report = new Report();
		
		report.setReport_date("07/13/2012");
		report.setReport_no("RP071312"+flightNo);
		//report.setCrew_fatalities(10);
		//report.setCrew_injuries(10);
		//report.setCabin_fatalities(20);
		//report.setCabin_injuries(20);
		//report.setPassenger_fatalities(30);
		//report.setPassenger_injuries(31);
		report.setAircraft_damage("NONE");
		report.setDelay_time("NONE");
		report.setReport_state(Report.STATUS_NOT_REPORTED);
		//report.setFlight_info_id(rfi.getId());
		
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
		String flightNo = ServletRequestUtils.getStringParameter(request, "flightNo", "KE1239");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		System.out.println("Add report..");
		
		ReportParent rp = new ReportParent();
		DateTime curTime = new DateTime();
		String dateStr = curTime.toString("ddMMyy");
		rp.setReport_no("R"+dateStr+flightNo);
		rp.setReport_date(curTime.toString("yyyy-MM-dd"));
		rp.setReport_state(Report.STATUS_NOT_REPORTED);
		rp.setManagement_state(Report.STATUS_NOT_REPORTED);
		reportService.createReportParent(rp);
		
		ReportParent rpp = reportService.readReportParent(rp);
		
		Vector<String> userTypeList = new Vector<String>();
		userTypeList.add(User.TYPE_PILOT);
		userTypeList.add(User.TYPE_CABIN);
		userTypeList.add(User.TYPE_GROUND);
		userTypeList.add(User.TYPE_MAINTENANCE);
		userTypeList.add(User.TYPE_DISPATCHER);
		
		
		Report report = new Report();
		report.setReport_parent_id(rpp.getId());
		report.setReport_date(rp.getReport_date());
		report.setCrew_fatalities("0");
		report.setCrew_minor_injuries("0");
		report.setCrew_serious_injuries("0");
		report.setCabin_fatalities("0");
		report.setCabin_minor_injuries("0");
		report.setCabin_serious_injuries("0");
		report.setPassenger_fatalities("0");
		report.setPassenger_minor_injuries("0");
		report.setPassenger_serious_injuries("0");
		report.setAircraft_damage("1");
		report.setDelay_time("1");
		report.setInjury("1");
		report.setReport_state("NOT REPORTED");
		report.setPriority("1");
		
		for(String userType : userTypeList){
			for(int i = 0 ; i < 3 ; i++){
				report.setReport_no("R"+userType+dateStr+flightNo+"-"+Integer.toString(i));
				report.setType(userType+Integer.toString(i));
				reportService.createReport(report);
				
				Report rpr = reportService.readReport(report);
				
				FlightInfo fi = new FlightInfo();
				fi.setFlight_date(rp.getReport_date());
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
				fi.setReport_id(rpr.getId());
				reportService.createFlightInfo(fi);
				
				UserInfo ui = new UserInfo();
				ui.setReport_id(rpr.getId());
				userService.createUserInfo(ui);
				
				AircraftInfo ai = new AircraftInfo();
				ai.setManufacturer("BOEING");
				ai.setModel("1");
				ai.setSerial_no("51-11012");
				ai.setRegi_no("HL7229");
				ai.setNo_seat_crew(2);
				ai.setNo_seat_cabin(10);
				ai.setNo_seat_passenger(100);
				ai.setLast_inspection_type("1");
				ai.setLast_inspection_date("2012-12-06");
				ai.setReport_id(rpr.getId());
				reportService.createAircraftInformation(ai);
				
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
					ri.setReport_id(rpr.getId());
					ri.setStatus("NOTSUBMITTED");
					reportService.createReportItem(ri);
				}
			}
		}
		
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("lang", lang);
		return model;
	}

	@RequestMapping("/fileUploadForm.do")
    public ModelAndView fileUploadForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String isReadOnly = ServletRequestUtils.getStringParameter(request, "isReadOnly", "N");
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
		model.addObject("isReadOnly", isReadOnly);
		model.addObject("report_item_type", report_item_type);
		model.addObject("attachedItemList", itemList);
		return model;
	}
	
	@RequestMapping("/hazardView.do")
    public ModelAndView hazardView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_parent_no = ServletRequestUtils.getStringParameter(request, "report_parent_no", "");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		int hazard_index = ServletRequestUtils.getIntParameter(request, "hazard_index", 1);
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String isreadonly = ServletRequestUtils.getStringParameter(request, "isreadonly", "N");
		String state_hazard_id = ServletRequestUtils.getStringParameter(request, "state_hazard_id", "");
		String language = (String)request.getSession().getAttribute("lang");
		
		ReportItem ri = new ReportItem();
		ri.setReport_no(report_no);
		ri.setType(report_item_type);
		ri = reportService.readReportItem(ri);
		
		
		
		Hazard hazard = new Hazard();
		hazard.setReport_item_id(ri.getId());
		List<Hazard> hazardList = reportService.readHazardPureList(hazard);
		System.out.println("=========="+hazardList);
		int hazard_num = hazardList.size();
		if(hazard_num < 1 ) hazard_num = 1;

	
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/hazardView");
		model.addObject("lang", lang);
		model.addObject("report_parent_no",report_parent_no);
		model.addObject("report_no", report_no);
		model.addObject("report_item_type", report_item_type);
		model.addObject("isreadonly", isreadonly);
		model.addObject("hazard_no", hazard_no);
		model.addObject("report_item_type", report_item_type);
		model.addObject("hazardNum", hazard_num);
		model.addObject("hazard_index", hazard_index);
		model.addObject("state_hazard_id", state_hazard_id);
		
		return model;
	}
	
	@RequestMapping("/hazardViewContent.do")
    public ModelAndView hazardViewContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String report_parent_no = ServletRequestUtils.getStringParameter(request, "report_parent_no", "");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		int hazard_index = ServletRequestUtils.getIntParameter(request, "hazard_index", 1);
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String isreadonly = ServletRequestUtils.getStringParameter(request, "isreadonly", "N");
		String language = (String)request.getSession().getAttribute("lang");
		
		
		
		
		
		Hazard h = new Hazard();
		int hazard_num = 0;
		if(!hazard_no.isEmpty()){
			Hazard hh = new Hazard();
			hh.setHazard_no(hazard_no);
			h = reportService.readHazard(hh);
			hazard_num = 1;
		}else{
			ReportItem ri = new ReportItem();
			ri.setReport_no(report_no);
			ri.setType(report_item_type);
			ri = reportService.readReportItem(ri);
		
			Hazard hazard = new Hazard();
			hazard.setReport_item_id(ri.getId());
			List<Hazard> hazardList = reportService.readHazardPureList(hazard);
			
			hazard_num = hazardList.size();
			if(hazard_num < 1 ) hazard_num = 1;
			
			if(hazardList.size() >= hazard_index ){
				h = hazardList.get(hazard_index-1);
			}
		}
			
			
		
		
		List<HazardItem> hazardItemList = null;
		HazardItem hazardItem = new HazardItem();
		hazardItem.setHazard_id(h.getId());
		hazardItemList = hazardItemService.readHazardItemList(hazardItem);
		
		if(hazardItemList == null){
			hazardItemList = new ArrayList<HazardItem>();
			for(int i = 0 ; i < 5 ; i++)
				hazardItemList.add(new HazardItem());
		}
		
		SelectItem item = new SelectItem();
		item.setCategory("occurrence");
		List<SelectItem> occurrenceList = reportService.readSelectItemList(item);
		
		item.setCategory("injury");
		List<SelectItem> injuryList = reportService.readSelectItemList(item);
		
		item.setCategory("aircraft_damage");
		List<SelectItem> damageList = reportService.readSelectItemList(item);
		
		item.setCategory("delay_time");
		List<SelectItem> delayList = reportService.readSelectItemList(item);
		
		item.setCategory("priority");
		List<SelectItem> priorityList = reportService.readSelectItemList(item);
		
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/hazardViewContent");
		model.addObject("lang", lang);
		model.addObject("report_parent_no",report_parent_no);
		model.addObject("report_no", report_no);
		model.addObject("occurrenceList", occurrenceList);
		model.addObject("injuryList", injuryList);
		model.addObject("damageList", damageList);
		model.addObject("delayList", delayList);
		model.addObject("priorityList", priorityList);
		model.addObject("isreadonly", isreadonly);
		model.addObject("hazard_no", hazard_no);
		model.addObject("report_item_type", report_item_type);
		model.addObject("hazardNum", hazard_num);
		model.addObject("hazard_index", hazard_index);
		model.addObject("hazardItemList", hazardItemList);
		//model.addObject("hazard_no", hazard_no);
		model.addObject("hazard", h);
		return model;
	}
	
	@RequestMapping("/hazardViewContentUpdate.do")
    public @ResponseBody String hazardViewContentUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		Hazard hz = new Hazard();
	
		reportService.hazardViewContentUpdate(hz, request);

		return hz.getHazard_no()+"_/"+"Update Success.";
	}
	
//	public static void main(String[] argv){
//		DateTime curTime = new DateTime();
//		System.out.println(curTime.toString("ddMMyy"));
//	}
	
}
