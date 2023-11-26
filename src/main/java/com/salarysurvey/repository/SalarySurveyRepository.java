package com.salarysurvey.repository;

import com.salarysurvey.model.SalarySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurvey, Integer>, JpaSpecificationExecutor<SalarySurvey> {
}
