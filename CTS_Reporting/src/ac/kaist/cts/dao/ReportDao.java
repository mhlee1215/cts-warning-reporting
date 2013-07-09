package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.Report;

public interface ReportDao {
	public List<Report> readReportListReview();
	public List<Report> readReportListHazardIdentification();
	public List<Report> readReportListRiskAnalysis();
	public List<Report> readReportListRiskAssessment();
	public List<Report> readReportListMitigation();
}
