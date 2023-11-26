package com.salarysurvey.config;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Object> args = castArguments(root);
        Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {
            case EQUAL: {
                return switch (argument) {
                    case String ignored -> builder.like(root.get(property), argument.toString().replace('*', '%'));
                    case null -> builder.isNull(root.get(property));
                    case LocalDateTime dateTimeArgument -> builder.equal(root.get(property), dateTimeArgument);
                    default -> builder.equal(root.get(property), argument);
                };
            }
            case NOT_EQUAL: {
                return switch (argument) {
                    case String ignored -> builder.notLike(root.get(property), argument.toString().replace('*', '%'));
                    case null -> builder.isNotNull(root.get(property));
                    case LocalDateTime dateTimeArgument -> builder.notEqual(root.get(property), dateTimeArgument);
                    default -> builder.notEqual(root.get(property), argument);
                };
            }
            case GREATER_THAN: {
                if (argument instanceof LocalDateTime dateTimeArgument) {
                    return builder.greaterThan(root.get(property), dateTimeArgument);
                } else {
                    return builder.greaterThan(root.get(property), argument.toString());
                }
            }
            case GREATER_THAN_OR_EQUAL: {
                if (argument instanceof LocalDateTime dateTimeArgument) {
                    return builder.greaterThanOrEqualTo(root.get(property), dateTimeArgument);
                } else {
                    return builder.greaterThanOrEqualTo(root.get(property), argument.toString());
                }
            }
            case LESS_THAN: {
                if (argument instanceof LocalDateTime dateTimeArgument) {
                    return builder.lessThan(root.get(property), dateTimeArgument);
                } else {
                    return builder.lessThan(root.get(property), argument.toString());
                }
            }
            case LESS_THAN_OR_EQUAL: {
                if (argument instanceof LocalDateTime dateTimeArgument) {
                    return builder.lessThanOrEqualTo(root.get(property), dateTimeArgument);
                } else {
                    return builder.lessThanOrEqualTo(root.get(property), argument.toString());
                }
            }
            case IN:
                return root.get(property).in(args);
            case NOT_IN:
                return builder.not(root.get(property).in(args));
        }

        return null;
    }

    private List<Object> castArguments(final Root<T> root) {

        Class<? extends Object> type = root.get(property).getJavaType();

        List<Object> args = arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else if (type.equals(LocalDateTime.class)) {
                return LocalDateTime.parse(arg);
            } else {
                return arg;
            }
        }).collect(Collectors.toList());

        return args;
    }

    // standard constructor, getter, setter
}
