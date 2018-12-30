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

import br.com.portozoca.PortoZocaWS;
import br.com.portozoca.core.db.DAORepository;
import br.com.portozoca.core.db.BaseEntity;
import br.com.portozoca.core.error.ResourceException;
import br.com.portozoca.core.utils.Strings;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * Service to work with entities
 */
@Service
public class EntityService {

    /** Base entity package */
    private static final String BASE_PKG = PortoZocaWS.class.getPackage().getName();

    /** All entity DAOs */
    @Autowired
    private @Nullable
    Map<String, DAORepository<? extends BaseEntity>> daos;
    @Autowired
    private ObjectMapper mapper;

    /**
     * Returns the repository (DAO) of the desired entity
     *
     * @param entity
     * @return DAORepository
     */
    public final DAORepository<? extends BaseEntity> repository(String entity) {
        String key = name(entity);
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
        try {
            return (T) mapper.readValue(body, clazz(entity));
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
        try {
            return (Class<? extends BaseEntity>) Class.forName(
                    packageClass(entity)
            );
        } catch (ClassNotFoundException ex) {
            throw new ResourceException();
        }
    }

    /**
     * Returns full package and class name acording to entity name
     *
     * @param entity
     * @return Class
     */
    public final String packageClass(String entity) {
        return new StringBuilder().
                append(BASE_PKG).
                append('.').
                append(entity.toLowerCase()).
                append('.').
                append(name(entity)).
                toString();
    }

    /**
     * Returns the formated entity name
     *
     * @param entity
     * @return String
     */
    public final String name(String entity) {
        return Strings.capitalize(entity);
    }

}
