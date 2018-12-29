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
package br.com.portozoca.entity.moviment;

import br.com.portozoca.core.db.AuditedEntity;

/**
 * Moviment Bean to represent a BL Moviment register
 */
public class Moviment extends AuditedEntity{
    
    /** Travel reference */
    private Long travel;
    /** BL */
    private String bl;
    /** Expected quantity */
    private int expected;
    /** Conferred quantity */
    private int conferred;

    public Long getTravel() {
        return travel;
    }

    public void setTravel(Long travel) {
        this.travel = travel;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public int getExpected() {
        return expected;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    public int getConferred() {
        return conferred;
    }

    public void setConferred(int conferred) {
        this.conferred = conferred;
    }    
    
}
