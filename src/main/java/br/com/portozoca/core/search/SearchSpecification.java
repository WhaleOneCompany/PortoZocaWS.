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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Search specification for the database
 */
public class SearchSpecification implements Specification {

    private final SearchCriteria searchCriteria;

    private SearchSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public final Predicate toPredicate(Root root, CriteriaQuery q, CriteriaBuilder b) {
        List<Predicate> pList = new ArrayList<>();
        final int size = searchCriteria.getItems().size();
        for (int i = 0; i < size; i++) {
            SearchParam sc = searchCriteria.get(i);
            pList.add(createPredicate(root, b, sc));
        }
        return b.and(pList.toArray(new Predicate[size]));
    }

    private static Predicate createPredicate(Root root, CriteriaBuilder builder, SearchParam p) {
        Path field = root.get(p.getKey());
        String value = p.getValue().toString();
        switch (p.getOp()) {
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
                return builder.like(field, value);
            case NLIKE:
                return builder.notLike(field, value);
            case CT:
                return builder.like(field, "%" + value + "%");
            case NCT:
                return builder.notLike(field, "%" + value + "%");
        }
        return null;
    }

    public static final Specification create(SearchCriteria criteria) {
        if (criteria == null || criteria.isEmpty()) {
            return null;
        }
        return new SearchSpecification(criteria);
    }

}
