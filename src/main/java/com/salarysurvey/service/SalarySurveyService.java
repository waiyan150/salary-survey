package com.salarysurvey.service;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalarySurveyService {

    @Autowired
    private SalarySurveyRepository repository;

    public SalarySurvey getOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

//    public SalarySurveyList getAll(Pageable pageable) {
//        Page<SalarySurvey> results = repository.findAll(pageable);
//        return SalarySurveyList.builder()
//                .numberOfItems(results.getTotalElements()).numberOfPages(results.getTotalPages())
//                .salarySurveys(results.getContent())
//                .build();
//    }

    public Page<SalarySurvey> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
