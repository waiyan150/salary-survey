package com.salarysurvey.repository;

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

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurvey, Integer>, QuerydslPredicateExecutor<SalarySurvey>, QuerydslBinderCustomizer<QSalarySurvey> {

    @Override
    default void customize(QuerydslBindings bindings, @Nonnull QSalarySurvey root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        // in between filter
        bindings.bind(root.timestamp).all((path, value) -> {
            Iterator<? extends LocalDateTime> it = value.iterator();
            LocalDateTime from = it.next();
            if (value.size() >= 2) {
                LocalDateTime to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.goe(from));
            }
        });

        bindings.bind(root.id).all((path, values) -> ExpressionProviderFactory.getPredicate(path, values));
    }
}
