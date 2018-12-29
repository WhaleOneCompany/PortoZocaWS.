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
package br.com.portozoca.entity.conference;

import br.com.portozoca.core.db.AuditedEntity;
import br.com.portozoca.entity.Imported.Manifested;
import br.com.portozoca.entity.dimension.Dimension;
import br.com.portozoca.entity.movement.Movement;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Conference bean to represent a conference register
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"movement", "serie"})
})
public class Conference extends AuditedEntity {
    
    @Column
    private Long idMovement;
    /** Movement */
    @NotNull
    @ManyToOne
    private Movement movement;
    /** Serie of product */
    @Column
    @NotNull
    private String serie;
    /** Conference Date/Time */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date conferenceDate;
    /** Dimension */
    @OneToOne
    private Dimension dimension;
    /** Observation */
    @Column
    private String observation;
    /** Reference to the manifested register */
    @ManyToOne
    private Manifested manifested;

    public Long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(Long idMovement) {
        this.idMovement = idMovement;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Manifested getManifested() {
        return manifested;
    }

    public void setManifested(Manifested manifested) {
        this.manifested = manifested;
    }
    
}
