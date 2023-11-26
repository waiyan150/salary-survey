package com.salarysurvey.controller;

import com.salarysurvey.config.CustomRsqlVisitor;
import com.salarysurvey.model.SalarySurvey;
import com.salarysurvey.service.SalarySurveyService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
            @RequestParam(value = "survey") String search,
            Pageable pageable) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<SalarySurvey> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return service.findAll(spec, pageable, fields);
    }
}