package com.salarysurvey.service;


import com.salarysurvey.model.SalarySurvey;

import java.util.List;

public interface SalarySurveyService {
    public SalarySurvey save(SalarySurvey entity);
    public List<SalarySurvey> getAll();
}
