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
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Conference bean to represent a conference register
 */
public class Conference extends AuditedEntity {
    
    /** Movement reference */
    private Long movement;
    /** Serie of product */
    private String serie;
    /** Conference Date/Time */
    @Temporal(TemporalType.TIMESTAMP)
    private Date conferenceDate;
    /** Dimension reference */
    private Long dimension;
    /** Observation */
    private String observation;

    public Long getMovement() {
        return movement;
    }

    public void setMovement(Long movement) {
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
    
}
