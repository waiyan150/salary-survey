package com.salarysurvey.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.querydsl.core.types.Predicate;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyModelAssembler;
import com.salarysurvey.repository.SalarySurveyRepository;
import com.salarysurvey.service.SalarySurveyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@CrossOrigin
public class SalarySurveyController {
    private final SalarySurveyService service;
    private final SalarySurveyModelAssembler assembler;
    private final PagedResourcesAssembler<SalarySurvey> pagedResourcesAssembler;

    public SalarySurveyController(final SalarySurveyService serviceValue, final SalarySurveyModelAssembler assemblerValue, final PagedResourcesAssembler<SalarySurvey> pagedResourcesAssemblerValue) {
        service = serviceValue;
        assembler = assemblerValue;
        pagedResourcesAssembler = pagedResourcesAssemblerValue;
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

        var results = pagedResourcesAssembler.toModel(service.findAllWithFilter(predicate, pageable), assembler);

        // Paging, field filter and Hateoas
        String[] fieldsArray;
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(results);
        FilterProvider filters;
        if (StringUtils.hasLength(fields)) {
            fieldsArray = fields.replaceAll("\\s+","").split(",");
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsArray));
        } else {
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.serializeAll());
        }
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}