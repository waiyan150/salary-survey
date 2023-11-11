package com.salarysurvey.controller;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyModelAssembler;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@CrossOrigin
public class SalarySurveyController {
    @Autowired
    private SalarySurveyService service;
    @Autowired
    private SalarySurveyModelAssembler assembler;
    @Autowired
    private PagedResourcesAssembler<SalarySurvey> pagedResourcesAssembler;

    @GetMapping("/{id}")
    public SalarySurvey getOne(@PathVariable Integer id) {
        return service.getOne(id);
    }

    @GetMapping()
    public PagedModel<SalarySurvey> getAll(Pageable pageable) {
        Page<SalarySurvey> results = service.getAll(pageable);
        return pagedResourcesAssembler.toModel(results, assembler);
    }
}