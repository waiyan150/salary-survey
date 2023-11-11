package com.salarysurvey.model;

import com.salarysurvey.controller.SalarySurveyController;
import jakarta.annotation.Nonnull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class SalarySurveyModelAssembler extends RepresentationModelAssemblerSupport<SalarySurvey, SalarySurvey> {
    public SalarySurveyModelAssembler() {
        super(SalarySurveyController.class, SalarySurvey.class);
    }

    @Override
    @Nonnull
    public SalarySurvey toModel(@Nonnull SalarySurvey entity) {
        return entity;
    }
}