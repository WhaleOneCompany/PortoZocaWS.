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

import br.com.portozoca.core.db.AuditedEntity;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Travel bean to represent a Ship Travel register
 */
public class Travel extends AuditedEntity {
    
    /** Ship */
    private String ship;
    /** Travel */
    private String travel;
    /** customer */
    private String customer;
    /** Importation date */
    @Temporal(TemporalType.TIMESTAMP)
    private Date importationDate;
    /** Status */
    @Enumerated(EnumType.ORDINAL)
    private TravelStatus status;

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getImportationDate() {
        return importationDate;
    }

    public void setImportationDate(Date importationDate) {
        this.importationDate = importationDate;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }
    
            
}
