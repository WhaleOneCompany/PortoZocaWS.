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
package br.com.portozoca.entity.dimension;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.itemsofbl.ItemsOfBl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;

/**
 * Dimension bean to represent a dimension register
 */
@Entity
@Component("dimension")
public class Dimension extends AuditedEntity {

    /** Weight */
    @Column
    private Float weight;
    /** Thickness */
    @Column
    private String thickness;
    /** Item of bill of landing */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemOfBl_id")
    private ItemsOfBl itemOfBl;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public ItemsOfBl getItemOfBl() {
        return itemOfBl;
    }

    public void setItemOfBl(ItemsOfBl itemOfBl) {
        this.itemOfBl = itemOfBl;
    }

}
