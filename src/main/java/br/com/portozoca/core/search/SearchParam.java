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
 *
 * @author jonas
 */
public class SearchParam {

    private String key;
    private SearchOperator operator;
    private Object value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setOperator(SearchOperator operator) {
        this.operator = operator;
    }

    public void setValue(Object value) {
        this.value = value;
    }

//    public SearchParam(String key, SearchOperator operator, String value) {
//        this.key = key;
//        this.operator = operator;
//        this.value = value;
//    }
    public String getKey() {
        return key;
    }

    public SearchOperator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

}
