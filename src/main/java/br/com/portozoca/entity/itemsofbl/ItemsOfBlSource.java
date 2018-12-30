/*
 * Copyright (C) 2018 Spaniol
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
package br.com.portozoca.entity.itemsofbl;

/**
 * Source of the item of bl
 */
public enum ItemsOfBlSource {
    /** Indicates the item is manifested */
    IMPORTED(0);
    
    private final int value;
    ItemsOfBlSource(int value) {
        this.value = value;
    }
    
    public ItemsOfBlSource valueof (ItemsOfBlSource other) {
        for (ItemsOfBlSource t : values()) {
            if(t.equals(other)){
                return t;
            }
        }
        return null;
    };    
}
