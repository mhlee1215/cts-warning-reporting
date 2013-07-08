package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.Report;

public interface ReportDao {
	public List<Report> readReportListReview();
}
