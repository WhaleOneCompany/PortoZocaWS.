/*
 * Copyright (C) 2018 PortoZocaWS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.portozoca.core.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Search specification for the
 */
public class SearchSpecification implements Specification {

    public static final Specification toSpec(SearchCriteria criteria) {
        if (criteria == null || criteria.isEmpty()) {
            return null;
        }
        return new SearchSpecification(criteria.get(0));
    }

    private final SearchParam searchCriteria;

    private SearchSpecification(SearchParam searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        Path field = root.get(searchCriteria.getKey());
        String value = searchCriteria.getValue().toString();
        switch (searchCriteria.getOp()) {
            case EQ:
                return builder.equal(field, value);
            case NEQ:
                return builder.notEqual(field, value);
            case GT:
                return builder.greaterThan(field, value);
            case GTE:
                return builder.greaterThanOrEqualTo(field, value);
            case LT:
                return builder.lessThan(field, value);
            case LTE:
                return builder.lessThanOrEqualTo(field, value);
            case LIKE:
                return builder.like(field, "%" + value + "%");
            case NLIKE:
                return builder.notLike(field, "%" + value + "%");
        }
        return null;
    }

}
