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
 *
 * @author jonas
 */
public class SearchCriteria {

    private List<SearchParam> itens;

    public List<SearchParam> getItens() {
        return itens;
    }

    public void setItens(List<SearchParam> itens) {
        this.itens = itens;
    }

    public final SearchParam get(int idx) {
        return itens.get(idx);
    }

    public final boolean isEmpty() {
        return this.itens == null || this.itens.isEmpty();
    }
}
