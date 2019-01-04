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
import br.com.portozoca.entity.billoflading.BillOfLading;
import br.com.portozoca.entity.itemsofbl.ItemsOfBl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;

/**
 * Travel bean to represent a Ship Travel register
 */
@Entity
@Component("travel")
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = { "ship", "travel" })
})
public class Travel extends AuditedEntity {

    /** Ship */
    @Column(nullable = false)
    private String ship;
    /** Travel */
    @Column(nullable = false)
    private String travel;
    /** customer */
    @Column
    private String customer;
    /** Importation date */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date importationDate;
    /** Status */
    @Column
    @Enumerated(EnumType.ORDINAL)
    private TravelStatus status;
        @OneToMany(
            mappedBy = "travel",
            targetEntity = BillOfLading.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<BillOfLading> bls;

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

    public List<BillOfLading> getBls() {
        return bls;
    }

    public void setBls(List<BillOfLading> bls) {
        this.bls = bls;
    }
    
}
