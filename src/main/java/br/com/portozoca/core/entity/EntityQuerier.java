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
package br.com.portozoca.core.entity;

import br.com.portozoca.core.db.BaseEntity;
import br.com.portozoca.core.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * A service to query data
 */
@Service
public class EntityQuerier {

    @Autowired
    private EntityService entities;

    /**
     * Query a resource by it's ID
     *
     * @param entity
     * @param id
     * @return Entidade
     * @throws ResourceNotFoundException
     */
    public final BaseEntity findById(String entity, Long id) throws ResourceNotFoundException {
        return entities.repository(entity).findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Query a resources data page
     *
     * @param entity
     * @param pageable
     * @return Page
     */
    public final Page findAll(String entity, Pageable pageable) {
        return entities.repository(entity).findAll(pageable);
    }

}
