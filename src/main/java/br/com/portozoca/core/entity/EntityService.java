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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work with entities
 */
@Service
public class EntityService {

    /** Repository suffix */
    private static final String REPOSITORY = "Repository";

    /** All entity DAOs */
    @Autowired
    private Map<String, DAORepository<? extends BaseEntity>> daos;
    /** All entity class names */
    private Set<Class<? extends BaseEntity>> entities;

    @Autowired
    private ObjectMapper mapper;

    /**
     * Returns the repository (DAO) of the desired entity
     *
     * @param clazz
     * @return DAORepository
     */
    public final <T> DAORepository<T> repository(Class<T> clazz) {
        return (DAORepository<T>) repository(name(clazz.getSimpleName()));
    }

    /**
     * Returns the repository (DAO) of the desired entity
     *
     * @param entity
     * @return DAORepository
     */
    public final DAORepository<? extends BaseEntity> repository(String entity) {
        String key = name(entity).concat(REPOSITORY);
        if (!daos.containsKey(key)) {
            throw new InvalidParameterException(entity);
        }
        return daos.get(key);
    }

    /**
     * Casts the json body string to it's entity class
     *
     * @param <T>
     * @param entity
     * @param body
     * @return BaseEntity
     * @throws br.com.portozoca.core.error.ResourceException
     */
    public final <T extends BaseEntity> T object(String entity, String body) throws ResourceException {
        return object(clazz(entity), body);
    }

    /**
     * Casts the json body string to it's entity class
     *
     * @param <T>
     * @param clazz
     * @param body
     * @return BaseEntity
     * @throws br.com.portozoca.core.error.ResourceException
     */
    public final <T extends BaseEntity> T object(Class<? extends BaseEntity> clazz, String body) throws
            ResourceException {
        try {
            return (T) mapper.readValue(body, clazz);
        } catch (IOException ex) {
            throw new ResourceException();
        }
    }

    /**
     * Find entity clas by it's name
     *
     * @param entity
     * @return Class
     * @throws br.com.portozoca.core.error.ResourceException
     */
    public final Class<? extends BaseEntity> clazz(String entity) throws ResourceException {
        Iterator<Class<? extends BaseEntity>> it = entities.iterator();
        while (it.hasNext()) {
            Class<? extends BaseEntity> e = it.next();
            if (e.getSimpleName().equalsIgnoreCase(name(entity))) {
                return e;
            }
        }
        throw new ResourceException();
    }

    /**
     * Retruns the full name of the class, including the package, according to the entity
     *
     * @param entity
     * @return Class
     * @throws br.com.portozoca.core.error.ResourceException
     */
    public final String packageClass(String entity) throws ResourceException {
        return clazz(entity).getPackage().getName();
    }

    /**
     * Returns the formated entity name
     *
     * @param entityClazz
     * @return String
     */
    public final String name(Class<? extends BaseEntity> entityClazz) {
        return entityClazz.getSimpleName().toLowerCase();
    }

    /**
     * Returns the formated entity name
     *
     * @param entity
     * @return String
     */
    public final String name(String entity) {
        return entity.toLowerCase();
    }

    /**
     * Inject all the entities via Reflections API
     *
     * @param reflections
     */
    @Autowired
    public void setEntidades(Reflections reflections) {
        this.entities = reflections.getSubTypesOf(BaseEntity.class);
    }

}
