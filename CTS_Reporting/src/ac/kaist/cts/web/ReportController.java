package ac.kaist.cts.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	@RequestMapping("/report.do")
    public ModelAndView report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("report/reportMain");
		model.addObject("page_title", "PILOT REPORT");
		System.out.println("add object!!");
		return model;
	}
	
	@RequestMapping("/management.do")
    public ModelAndView management(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/managementMain");
		model.addObject("page_title", "HAZARD REPORT REVIEW");
		return model;
	}
	
	@RequestMapping("/readFlightInformation.do")
    public ModelAndView flightInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("report/FlightInfoList");
		return model;
	}
	
	@RequestMapping("/reportToIdentifyList.do")
    public ModelAndView reportToIdentifyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/reportToIdentifyList");
		return model;
	}
	
	@RequestMapping("/managementDetailMain.do")
    public ModelAndView managementDetailMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailMain");
		return model;
	}
	
	@RequestMapping("/managementDetailReview.do")
    public ModelAndView managementDetailReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailReview");
		return model;
	}
	
	@RequestMapping("/managementDetailHazardIdentification.do")
    public ModelAndView managementDetailHazardIdentification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailHazardIdentification");
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAnalysis.do")
    public ModelAndView managementDetailRiskAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAnalysis");
		return model;
	}
	
	@RequestMapping("/managementDetailRiskAssessment.do")
    public ModelAndView managementDetailRiskAssessment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailRiskAssessment");
		return model;
	}
	
	@RequestMapping("/managementDetailMitigation.do")
    public ModelAndView managementDetailMitigation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailMitigation");
		return model;
	}
	
	@RequestMapping("/managementDetailRegistered.do")
    public ModelAndView managementDetailRegistered(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("management/detail/managementDetailRegistered");
		return model;
	}
	
}
