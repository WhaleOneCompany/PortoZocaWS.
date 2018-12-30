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
package br.com.portozoca.entity.itemsofbl;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.billoflading.BillOfLading;
import br.com.portozoca.entity.dimension.Dimension;
import br.com.portozoca.entity.image.Image;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Conference bean to represent a conference register
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bl", "serie"}),
})
public class ItemsOfBl extends AuditedEntity {
    
    /** Bill Of Lading reference */
    @Column(nullable = false)
    private Long bl;
    /** Serie of product */
    @Column(nullable = false)
    private String serie;
    /** Status of the item*/
    @Column
    @Enumerated(EnumType.ORDINAL)
    private ItemsOfBlStatus status;
    /** Conference Date/Time */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date conferenceDate;
    /** Dimension reference */
    @Column
    private Long dimension;
    /** Observation */
    @Column
    private String observation;
    /** Source of the item */
    @Column
    @Enumerated(EnumType.ORDINAL)
    private ItemsOfBlSource source;
    /** Bill Of Lading */
    @ManyToOne
    @JoinColumn(name = "billOfLading_id")
    private BillOfLading billOfLading;
    /** Dimension */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dimension_id")
    private Dimension dimensionObj;
    /** Images */
    @OneToMany(
            mappedBy = "itemOfBl",
            targetEntity = Image.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Image> images;
    
    public Long getBl() {
        return bl;
    }

    public void setBl(Long bl) {
        this.bl = bl;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public ItemsOfBlStatus getStatus() {
        return status;
    }

    public void setStatus(ItemsOfBlStatus status) {
        this.status = status;
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public Long getDimension() {
        return dimension;
    }

    public void setDimension(Long dimension) {
        this.dimension = dimension;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ItemsOfBlSource getSource() {
        return source;
    }

    public void setSource(ItemsOfBlSource source) {
        this.source = source;
    }
    
    public BillOfLading getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(BillOfLading billOfLading) {
        this.billOfLading = billOfLading;
    }

    public Dimension getDimensionObj() {
        return dimensionObj;
    }

    public void setDimensionObj(Dimension dimensionObj) {
        this.dimensionObj = dimensionObj;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    
}
