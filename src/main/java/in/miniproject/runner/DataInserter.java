package in.miniproject.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.miniproject.binding.CitizenPlan;
import in.miniproject.repository.CitizenPlanRepository;

@Component
public class DataInserter implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CitizenPlan cp = new CitizenPlan();
		cp.setCustomerName("Sai");
		cp.setGender("Male");
		cp.setPhoneNumber("152536373");
		cp.setPlanName("Snap");
		cp.setPlanStatus("Approved");
		cp.setSsn((long) 8678);
		
		CitizenPlan cp1 = new CitizenPlan();
		cp1.setCustomerName("kumar");
		cp1.setGender("Male");
		cp1.setPhoneNumber("152536373");
		cp1.setPlanName("Snap");
		cp1.setPlanStatus("Denied");
		cp1.setSsn((long) 8678);
		
	List<CitizenPlan> list = Arrays.asList(cp,cp1);
	repo.saveAll(list);
		
	}

}
