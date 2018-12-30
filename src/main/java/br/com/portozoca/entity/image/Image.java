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
package br.com.portozoca.entity.image;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.itemsofbl.ItemsOfBl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Image 
 */
@Entity
public class Image extends AuditedEntity {
    
    /** Url of image */
    @Column(
            nullable = false,
            unique = true
            )
    private String url;
    /** Item of bill of landing reference */
    @Column(nullable = false)
    private Long itemOfBl;
    /** Item of bill of landing */
    @ManyToOne
    @JoinColumn(name = "itemOfBl_id")
    private ItemsOfBl item;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getItemOfBl() {
        return itemOfBl;
    }

    public void setItemOfBl(Long itemOfBl) {
        this.itemOfBl = itemOfBl;
    }

    public ItemsOfBl getItem() {
        return item;
    }

    public void setItem(ItemsOfBl item) {
        this.item = item;
    }
    
    
}
