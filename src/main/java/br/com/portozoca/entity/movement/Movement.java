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
package br.com.portozoca.entity.movement;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.conference.Conference;
import br.com.portozoca.entity.travel.Travel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Moviment Bean to represent a BL Moviment register
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"travel", "bl"})
})
public class Movement extends AuditedEntity{
    
    /** Travel reference */
    @NotNull
    @ManyToOne
    private Travel travel;
    /** BL */
    @Column
    @NotNull
    private String bl;
    /** Expected quantity */
    @Column
    private int expected;
    /** Conferred quantity */
    @Column
    private int conferred;
    
    
    @JoinColumn(referencedColumnName = "idMovement")
    private Conference conferece;

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
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
