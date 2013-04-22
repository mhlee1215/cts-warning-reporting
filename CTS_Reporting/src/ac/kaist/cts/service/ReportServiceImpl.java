package ac.kaist.cts.service;

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

}
