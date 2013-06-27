package ac.kaist.cts.web;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import language.LanguagePack;
import language.LanguageServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	@RequestMapping("/report.do")
    public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportMain");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportBasic.do")
    public ModelAndView reportBasic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_basic");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTaxiOut.do")
    public ModelAndView reportTaxiOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_taxi-out");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTakeOff.do")
    public ModelAndView reportTakeOff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_take-off");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportClimb.do")
    public ModelAndView reportClimb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_climb");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/report_en_route.do")
    public ModelAndView reportEnRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_en-route");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportDecent.do")
    public ModelAndView reportDecent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_decent");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportApproach.do")
    public ModelAndView reportApproach(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_approach");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportLanding.do")
    public ModelAndView reportLanding(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_landing");
		model.addObject("page_title", lang.getStringPilotReport());
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportTaxiIn.do")
    public ModelAndView reportTaxiIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		ModelAndView model = new ModelAndView("report/reportItem_taxi-in");
		model.addObject("page_title", lang.getStringPilotReport());
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
		ModelAndView model = new ModelAndView("report/FlightInfoList");
		model.addObject("lang", lang);
		return model;
	}
	
	@RequestMapping("/reportToIdentifyList.do")
    public ModelAndView reportToIdentifyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		model.addObject("lang", lang);
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
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		ModelAndView model = new ModelAndView("management/detail/managementDetailMain");
		model.addObject("lang", lang);
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
	
}
