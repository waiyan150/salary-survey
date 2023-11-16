package com.salarysurvey.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.salarysurvey.model.QSalarySurvey;
import com.salarysurvey.model.SalarySurvey;
import jakarta.annotation.Nonnull;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurvey, Integer>, QuerydslPredicateExecutor<SalarySurvey>, QuerydslBinderCustomizer<QSalarySurvey> {

    @Override
    default void customize(QuerydslBindings bindings, @Nonnull QSalarySurvey root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
//        bindings.bind(root.salary).all(ExpressionProviderFactory::getPredicate);
//        bindings.bind(root.timestamp).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(root.salary).first((path, value) -> {
            return path.eq(value);
        });
    }
}
