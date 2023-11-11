package com.salarysurvey.service;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalarySurveyServiceImpl implements SalarySurveyService {

    @Autowired
    private SalarySurveyRepository repository;

    @Override
    public SalarySurvey save(SalarySurvey entity) {
        return repository.save(entity);
    }

    @Override
    public List<SalarySurvey> getAll() {
        return repository.findAll();
    }
}
