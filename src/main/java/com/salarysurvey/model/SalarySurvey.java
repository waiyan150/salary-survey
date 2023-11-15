package com.salarysurvey.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:ss")
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
    private BigDecimal salary;

    @JsonFormat
    private BigDecimal signingBonus;

    @JsonFormat
    private BigDecimal annualBonus;

    @JsonFormat
    private BigDecimal annualStockValueBonus;

    @JsonFormat
    private String gender;

    @JsonFormat
    private String additionalComments;
}
