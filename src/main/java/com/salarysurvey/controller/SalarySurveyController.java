package com.salarysurvey.controller;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyList;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@CrossOrigin
public class SalarySurveyController {
    @Autowired
    private SalarySurveyService service;

    @GetMapping("/{id}")
    public SalarySurvey getOne(@PathVariable Integer id) {
        return service.getOne(id);
    }

    @GetMapping()
    public SalarySurveyList getAll(Pageable pageable) {
        return service.getAll(pageable);
    }
}