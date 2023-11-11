package com.salarysurvey.model;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Generated
@Data
@Builder
public class SalarySurveyList {
    private List<SalarySurvey> salarySurveys;
    private Long numberOfItems;
    private int numberOfPages;
}
