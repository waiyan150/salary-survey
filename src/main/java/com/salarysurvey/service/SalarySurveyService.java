package com.salarysurvey.service;

import com.querydsl.core.types.Predicate;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalarySurveyService {
    private static final Logger LOGGER = LogManager.getLogger();

    private final SalarySurveyRepository repository;

    public SalarySurveyService(final SalarySurveyRepository repositoryValue) {
        repository = repositoryValue;
    }

    public SalarySurvey getOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Page<SalarySurvey> findAllWithFilter(final Predicate spec, final Pageable pageable) {
        return repository.findAll(spec, pageable);
    }
}
