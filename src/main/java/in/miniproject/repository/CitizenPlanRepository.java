package in.miniproject.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.miniproject.binding.CitizenPlan;

@Repository
public interface CitizenPlanRepository extends JpaRepository<CitizenPlan,Serializable> {

	@Query("select DISTINCT(planName) from CitizenPlan")
	public List<String> getPlanNames();

	@Query("select DISTINCT(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();




}
