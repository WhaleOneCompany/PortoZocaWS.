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

/**
 * Search operators
 */
public enum SearchOperator {

    EQ, NEQ, GT, LT, GTE, LTE, LIKE, NLIKE, CT, NCT;

    public SearchOperator valueOf(Object other) {
        for (SearchOperator v : values()) {
            if (v.name().equalsIgnoreCase(other.toString())) {
                return v;
            }
        }
        return null;
    }

}
