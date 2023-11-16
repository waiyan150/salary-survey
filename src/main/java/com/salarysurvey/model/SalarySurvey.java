package com.salarysurvey.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Generated
@Getter
@Setter
@Table(name = "salary_survey")
@JsonFilter("SalarySurvey")
@QueryEntity
public class SalarySurvey extends RepresentationModel<SalarySurvey> implements Serializable {
    @Id
    @JsonFormat
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonFormat
    private String employer;

    @JsonFormat
    private String location;

    @JsonFormat
    private String jobTitle;

    @JsonFormat
    private String yearsAtEmployer;

    @JsonFormat
    private String yearsOfExperience;

    @JsonFormat
    private String salary;

    @JsonFormat
    private String signingBonus;

    @JsonFormat
    private String annualBonus;

    @JsonFormat
    private String annualStockValueBonus;

    @JsonFormat
    private String gender;

    @JsonFormat
    private String additionalComments;
}
