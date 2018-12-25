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

import br.com.portozoca.core.db.DAORepository;
import br.com.portozoca.core.db.BaseEntity;
import br.com.portozoca.core.error.ResourceException;
import br.com.portozoca.core.error.ResourceNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

/**
 * Service to persist entities
 */
@Service
public class EntityPersistor {

    @Autowired
    private EntityService entities;
    @Autowired
    protected EntityManager em;

    /**
     * Write resources
     *
     * @param entity
     * @param data
     * @return BaseEntity
     * @throws ResourceException
     */
    public final BaseEntity save(String entity, String data) throws ResourceException {
        DAORepository<? extends BaseEntity, Long> dao = entities.repository(entity);
        return dao.save(entities.object(entity, data));
    }

    /**
     * Update resources
     *
     * @param entity
     * @param id
     * @param data
     * @return BaseEntity
     * @throws ResourceException
     */
    public final BaseEntity save(String entity, Long id, String data) throws ResourceException {
        BaseEntity managed = entities.object(entity, data);
        managed.setId(id);
        return em.merge(managed);
    }

    /**
     * Delete resources
     *
     * @param entity
     * @param id
     * @return BaseEntity Deleted resource
     * @throws ResourceNotFoundException
     */
    public final BaseEntity delete(String entity, Long id) throws ResourceNotFoundException {
        DAORepository<? extends BaseEntity, Long> dao = entities.repository(entity);
        Optional<? extends BaseEntity> optional = dao.findById(id);
        // If it exists, it's deleted
        if (optional.isPresent()) {
            dao.deleteById(id);
            return optional.get();
        }
        throw new ResourceNotFoundException(id);
    }

}
