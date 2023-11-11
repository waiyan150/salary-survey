package com.salarysurvey.controller;

import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyModelAssembler;
import com.salarysurvey.model.SalarySurveySpecificationsBuilder;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public PagedModel<SalarySurvey> search(@RequestParam(required = false, value = "search") String search, Pageable pageable) {
        SalarySurveySpecificationsBuilder builder = new SalarySurveySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<SalarySurvey> spec = builder.build();
        return pagedResourcesAssembler.toModel(service.findAllWithFilter(spec, pageable), assembler);
    }
}