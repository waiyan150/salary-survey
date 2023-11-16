package com.salarysurvey.service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.querydsl.core.types.Predicate;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.model.SalarySurveyModelAssembler;
import com.salarysurvey.repository.SalarySurveyRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SalarySurveyService {
    /*private static final Logger LOGGER = LogManager.getLogger();*/
    private final SalarySurveyRepository repository;
    private final SalarySurveyModelAssembler assembler;
    private final PagedResourcesAssembler<SalarySurvey> pagedResourcesAssembler;

    public SalarySurveyService(final SalarySurveyRepository repositoryValue, final SalarySurveyModelAssembler assemblerValue, final PagedResourcesAssembler<SalarySurvey> pagedResourcesAssemblerValue) {
        repository = repositoryValue;
        assembler = assemblerValue;
        pagedResourcesAssembler = pagedResourcesAssemblerValue;
    }

    public SalarySurvey getOne(final Integer id) {
        return repository.findById(id).orElse(null);
    }

    public MappingJacksonValue findAll(final Predicate spec, final Pageable pageable, final String fields) {
        var entities = pagedResourcesAssembler.toModel(repository.findAll(spec, pageable), assembler);

        // Paging, field filter and Hateoas
        String[] fieldsArray;
        MappingJacksonValue result = new MappingJacksonValue(entities);
        FilterProvider filters;
        if (StringUtils.hasLength(fields)) {
            fieldsArray = fields.replaceAll("\\s+", "").split(",");
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsArray));
        } else {
            filters = new SimpleFilterProvider().addFilter("SalarySurvey", SimpleBeanPropertyFilter.serializeAll());
        }
        result.setFilters(filters);
        return result;
    }
}
