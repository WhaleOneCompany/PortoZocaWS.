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
package br.com.portozoca.billoflading;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.itemsofbl.ItemsOfBl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;

/**
 * Moviment Bean to represent a BL Moviment register
 */
@Entity
@Component("billoflading")
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"travel", "bl", "customer"}),})
public class BillOfLading extends AuditedEntity {

    /** Travel reference */
    @Column(nullable = false)
    private Long travel;
    /** BL */
    @Column(nullable = false)
    private String bl;
    @Column
    private String customer;
    /** Items of bill of lading */
    @OneToMany(
            mappedBy = "bl",
            targetEntity = ItemsOfBl.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    ) @JsonIgnore
    private List<ItemsOfBl> items;

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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<ItemsOfBl> getItems() {
        return items;
    }

    public void setItems(List<ItemsOfBl> items) {
        this.items = items;
    }

}
