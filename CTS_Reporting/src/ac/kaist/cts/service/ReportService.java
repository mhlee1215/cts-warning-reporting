package ac.kaist.cts.service;

import java.util.List;

import ac.kaist.cts.domain.Report;

public interface ReportService {
	public int 			createReport(Report report);
	public List<Report> readReportsAll();
	public List<Report> readReporstByUser(Report report);
	public Report 		readReport(Report report);
	public int 			editReport(Report report);
	public int 			deleteReport(Report report);
	
}
