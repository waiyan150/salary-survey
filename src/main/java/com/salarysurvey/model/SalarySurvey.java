package com.salarysurvey.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity(name = "salary_survey")
@Generated
@Table(name = "salary_survey")
@JsonFilter("SalarySurvey")
public class SalarySurvey extends RepresentationModel<SalarySurvey> implements Serializable {
    @Id
    @JsonFormat
    @Column
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime timestamp;

    @JsonFormat
    @Column
    private String employer;

    @JsonFormat
    @Column
    private String location;

    @JsonFormat
    @Column
    private String jobTitle;

    @JsonFormat
    @Column
    private String yearsAtEmployer;

    @JsonFormat
    @Column
    private String yearsOfExperience;

    @JsonFormat
    @Column
    private String salary;

    @JsonFormat
    @Column
    private String signingBonus;

    @JsonFormat
    @Column
    private String annualBonus;

    @JsonFormat
    @Column
    private String annualStockValueBonus;

    @JsonFormat
    @Column
    private String gender;

    @JsonFormat
    @Column
    private String additionalComments;
}
