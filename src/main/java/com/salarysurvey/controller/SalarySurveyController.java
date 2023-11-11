package com.salarysurvey.controller;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
@CrossOrigin
public class SalarySurveyController {
    @Autowired
    private SalarySurveyService service;

    @GetMapping()
    public List<SalarySurvey> list() {
        return service.getAll();
    }
}