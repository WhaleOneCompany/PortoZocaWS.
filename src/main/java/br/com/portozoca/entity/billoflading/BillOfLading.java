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
package br.com.portozoca.entity.billoflading;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.itemsofbl.ItemsOfBl;
import br.com.portozoca.entity.itemsofbl.ItemsOfBlStatus;
import br.com.portozoca.entity.travel.Travel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;

/**
 * Moviment Bean to represent a BL Moviment register
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = { "travel_id", "bl", "customer" })
})
@Component("billoflading")
public class BillOfLading extends AuditedEntity {

    /** BL */
    @Column(nullable = false)
    private String bl;
    @Column
    private String customer;
    /** Manifested quantity */
    private Long manifested;
    /** Conferred quantity */
    private Long conferred;
    /** Items of bill of lading */
    @OneToMany(
            mappedBy = "billOfLading",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<ItemsOfBl> items;
    /** Travel */
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;
    
    public String getBl() {
        return bl;
    }

    public BillOfLading setBl(String bl) {
        this.bl = bl;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public BillOfLading setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public Long getManifested() {
        manifested = getItems().stream().filter((t) -> {
            return t.getStatus().equals(ItemsOfBlStatus.MANIFESTED);
        }).count();
        return manifested;
    }

    public Long getConferred() {
        conferred = getItems().stream().filter((t) -> {
            return t.getStatus().equals(ItemsOfBlStatus.CONFERRED);
        }).count();
        return conferred;
    }

    public List<ItemsOfBl> getItems() {
        return items;
    }

    public BillOfLading setItems(List<ItemsOfBl> items) {
        this.items = items;
        return this;
    }

    public Travel getTravel() {
        return travel;
    }

    public BillOfLading setTravel(Travel travel) {
        this.travel = travel;
        return this;
    }
    
}
