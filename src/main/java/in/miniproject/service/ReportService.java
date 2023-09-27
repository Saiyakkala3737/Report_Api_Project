package in.miniproject.service;

import java.util.List;

import in.miniproject.binding.CitizenPlan;
import in.miniproject.binding.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> getCitizenPlans(SearchRequest request);
	public void exportExcel(HttpServletResponse response) throws Exception;
	public void exportPDF(HttpServletResponse response) throws Exception;

}
