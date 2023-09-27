package in.miniproject.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.miniproject.binding.CitizenPlan;
import in.miniproject.binding.SearchRequest;
import in.miniproject.service.ReportService;
import in.miniproject.service.ReportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportController {

	@Autowired
	private ReportService service;

	@GetMapping("/planNames")
	public  ResponseEntity<List<String>> getPlanNames(){
		List<String> names = service.getPlanNames();
		return new ResponseEntity<>(names,HttpStatus.OK);
	}	

	@GetMapping("/PlanStatus")
	public ResponseEntity<List<String>> getPlanStatus(){
		List<String> status = service.getPlanStatus();
		return new ResponseEntity<>(status,HttpStatus.OK);

	}

	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> getCitizenPlans(@RequestBody SearchRequest request){
		List<CitizenPlan> plan = service.getCitizenPlans(request);
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String key = "Content-Disposition";
		String value = "attachment;file=citizens.xls";

		response.setHeader(key, value);

		service.exportExcel(response);
		response.flushBuffer();
	}
	
	@GetMapping("/pdf")
	public void exportPDF(HttpServletResponse response) throws Exception{
		
		 // Set the content type and header for PDF file
        response.setContentType("application/pdf");
        
        String key = "Content-Disposition";
		String value = "attachment;file=citizens.pdf";
       response.setHeader(key, value);
       service.exportPDF(response);
	}


}
