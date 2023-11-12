package com.salarysurvey.service;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SalarySurveyService {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private SalarySurveyRepository repository;

    public SalarySurvey getOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Page<SalarySurvey> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<SalarySurvey> findAllWithFilter(final Specification<SalarySurvey> spec, final Pageable pageable) {
        LOGGER.info("find surveys, {}", pageable);
        return repository.findAll(spec, pageable);
    }

    /*public SalarySurvey update(final Integer id, final SalarySurvey request) {
        return repository.findById(id).map(entity -> {
            if (request.getSalary() != null) {
                entity.setSalary(request.getSalary());
            }
            return repository.save(entity);
        }).orElseThrow(() -> new EntityNotFoundException("Not found"));
    }*/
}
