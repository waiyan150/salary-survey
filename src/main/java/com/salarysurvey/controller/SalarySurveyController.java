package com.salarysurvey.controller;

import com.querydsl.core.types.Predicate;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.repository.SalarySurveyRepository;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@CrossOrigin
public class SalarySurveyController {
    private final SalarySurveyService service;

    public SalarySurveyController(final SalarySurveyService serviceValue) {
        service = serviceValue;
    }

    @GetMapping("/{id}")
    public SalarySurvey getOne(@PathVariable Integer id) {
        return service.getOne(id);
    }

    @GetMapping()
    public MappingJacksonValue search(
            @RequestParam(required = false, value = "fields") String fields,
            @QuerydslPredicate(root = SalarySurvey.class, bindings = SalarySurveyRepository.class) Predicate predicate,
            Pageable pageable) {
        return service.findAll(predicate, pageable, fields);
    }
}