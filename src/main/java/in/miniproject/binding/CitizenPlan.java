package in.miniproject.binding;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CITIZENS_PLANS_INFO")
@Component
public class CitizenPlan {

    @Id
    @GeneratedValue
    private Integer id; // Updated field name to follow naming conventions
    private String planName;
    private String planStatus;
    private String customerName; // Updated field name to follow naming conventions
    private String customerEmail; // Updated field name to follow naming conventions
    private String gender;
    private String phoneNumber; // Updated field name to follow naming conventions
    private Long ssn;
    
    // Add appropriate getters and setters for the fields (generated by Lombok's @Data)
}
