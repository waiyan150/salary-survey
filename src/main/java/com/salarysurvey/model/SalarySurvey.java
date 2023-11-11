package com.salarysurvey.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Generated
@Getter
@Setter
@Table(name = "salary_survey")
@JsonFilter("SalarySurvey")
public class SalarySurvey extends RepresentationModel<SalarySurvey> implements Serializable {
    @Id
    private Integer id;
    private LocalDateTime timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfExperience;
    private String salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValueBonus;
    private String gender;
    private String additionalComments;
}
