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

import java.util.List;

/**
 * Search Criteria
 */
public class SearchCriteria {

    private List<SearchParam> items;

    public List<SearchParam> getItems() {
        return items;
    }

    public void setItems(List<SearchParam> items) {
        this.items = items;
    }

    public List<SearchParam> getItens() {
        return this.items;
    }

    public final SearchParam get(int idx) {
        return items.get(idx);
    }

    public final boolean isEmpty() {
        return this.items == null || this.items.isEmpty();
    }
}
