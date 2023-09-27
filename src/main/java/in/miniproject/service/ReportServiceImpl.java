package in.miniproject.service;

import java.awt.Color;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.miniproject.binding.CitizenPlan;
import in.miniproject.binding.SearchRequest;
import in.miniproject.repository.CitizenPlanRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {


	@Autowired
	private	CitizenPlanRepository cprepo;
	
	@Override
	public List<String> getPlanNames() {
		return cprepo.getPlanNames();

	}

	@Override
	public List<String> getPlanStatus() {
		return cprepo.getPlanStatus();

	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if(request.getPlanName()!=null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<CitizenPlan> example = Example.of(entity);

		List<CitizenPlan> records = cprepo.findAll(example);
		return records;

	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Citzens Info");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("SSN");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");

		List<CitizenPlan> records = cprepo.findAll();
		int dataRowIndex =1;

		for(CitizenPlan record : records) {

			XSSFRow dataRow = sheet.createRow(dataRowIndex);

			dataRow.createCell(0).setCellValue(record.getId());
			dataRow.createCell(1).setCellValue(record.getCustomerName());
			dataRow.createCell(2).setCellValue(record.getSsn());
			dataRow.createCell(3).setCellValue(record.getGender());
			dataRow.createCell(4).setCellValue(record.getPlanName());
			dataRow.createCell(5).setCellValue(record.getPlanStatus());

			dataRowIndex++;

		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

	@Override
	public void exportPDF(HttpServletResponse response) throws Exception {

		List<CitizenPlan> records = cprepo.findAll();

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document,response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.blue);

		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f,3.5f,3.0f,3.0f,1.5f,1.5f});
		table.setSpacingBefore(10);

		//set table header data

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("ID", font1));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font1));
		table.addCell(cell); 

		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell); 

		//set table data

		List<CitizenPlan> records1= cprepo.findAll();

		for(CitizenPlan record : records1) {
			table.addCell(String.valueOf(record.getId()));
			table.addCell(record.getCustomerName());
			table.addCell(String.valueOf(record.getId()));
			table.addCell(record.getGender());
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());

		}

		document.add(table);
		document.close();


	}

}
