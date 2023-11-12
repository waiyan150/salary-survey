package com.salarysurvey.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyModelAssembler;
import com.salarysurvey.model.SalarySurveySpecificationsBuilder;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
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
    public MappingJacksonValue search(
            @RequestParam(required = false, value = "fields") String fields,
            @RequestParam(required = false, value = "search") String search,
            Pageable pageable) {
        String[] fieldsArray;
        SalarySurveySpecificationsBuilder builder = new SalarySurveySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<SalarySurvey> spec = builder.build();
        var results = pagedResourcesAssembler.toModel(service.findAllWithFilter(spec, pageable), assembler);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(results);
        FilterProvider filters;
        if (StringUtils.hasLength(fields)) {
            fieldsArray = fields.split(",");
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsArray));
        } else {
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.serializeAll());
        }
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}