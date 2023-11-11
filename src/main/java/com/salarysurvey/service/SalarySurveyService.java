package com.salarysurvey.service;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SalarySurveyService {

    @Autowired
    private SalarySurveyRepository repository;

    public SalarySurvey getOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Page<SalarySurvey> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<SalarySurvey> findAllWithFilter(final Specification<SalarySurvey> specValue, final Pageable pageableValue) {
        return repository.findAll(specValue, pageableValue);
    }
}
