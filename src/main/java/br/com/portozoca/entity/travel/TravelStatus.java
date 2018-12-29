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
package br.com.portozoca.entity.travel;

/**
 * Class to represent a travel status
 */
public enum TravelStatus {
    /** Indicates need check */
    TO_CHECK(0),
    /** Indicates the check is pending */
    PENDING(2),
    /** Indicates the check is finished */
    CHECKED(3);
    
    private final int value;
    TravelStatus(int value) {
        this.value = value;
    }
    
    public TravelStatus valueof (TravelStatus other) {
        for (TravelStatus t : values()) {
            if(t.equals(other)){
                return t;
            }
        }
        return null;
    };
    
}
