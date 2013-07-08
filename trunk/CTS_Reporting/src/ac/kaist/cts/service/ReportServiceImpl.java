package ac.kaist.cts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kaist.cts.dao.ReportDao;
import ac.kaist.cts.domain.Report;

@Service
public class ReportServiceImpl implements ReportService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ReportDao		reportDao;
	
	@Override
	public int createReport(Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Report> readReportsAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReporstByUser(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report readReport(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int editReport(Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReport(Report report) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Report> readReportListReviewAll() {
		// TODO Auto-generated method stub
		return reportDao.readReportListReview();
	}

	@Override
	public List<Report> readReportListReviewReview() {
		// TODO Auto-generated method stub
		
		List<Report> listAll = reportDao.readReportListReview();
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : listAll){
			
		}
		
		
		return null;
	}

	@Override
	public List<Report> readReportListReviewAccepted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewRejected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewInvestigation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewRegistered() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListHazardIdentificationReportsToIdentify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListHazardIdentificationIdentifiedReports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAnalysisHazardToBeAnalyzed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAnalysisAnalyzedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAssessmentHazardsToBeAssessed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAssessmentAssessedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListMitigationHazardsToBeMitigated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListMitigationMitigatedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

}
