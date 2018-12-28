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
package br.com.portozoca.importation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Manipulates the data of importation 
 * @param <T>
 */
public class Data<T> {
    
    /** Import data */
    private final List<T> data;
    
    public Data() {
        data = new ArrayList<T>();
    }
    
    /**
     * Add a new records to the data store and returns a 
     * number of records
     * 
     * @param bean
     * @return number of records
     */
    public int add(T bean) {
        data.add(bean);
        return data.size();
    }
    
    /**
     * Returs the recors in the line
     * 
     * @param line
     * @return BaseEntity
     */
    public T get(int line) {
        return data.get(line);
    }
    
    /**
     * Returns the data list
     * 
     * @return List<T>
     */
    public List<T> getData() {
        return data;
    }
    
    /**
     * Performs the given action for each element of the data
     * 
     * @param action 
     */
    public void forEach(Consumer<T> action) {
        Objects.requireNonNull(action);
        for (T d : data) {
            action.accept(d);
        }
    }
}
