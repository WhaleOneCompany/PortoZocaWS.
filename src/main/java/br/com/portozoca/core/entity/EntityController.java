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

import br.com.portozoca.core.error.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.portozoca.core.db.BaseEntity;
import br.com.portozoca.core.search.SearchCriteria;
import br.com.portozoca.core.search.SearchSpecification;
import br.com.portozoca.core.search.SearchCriteriaFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import javax.transaction.Transactional;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for all the entities
 */
@RestController
@RequestMapping(path = "v1/{entity}", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntityController {

    @Autowired
    private EntityPersistor persistence;
    @Autowired
    private EntityQuerier query;
    @Autowired
    private SearchCriteriaFactory searchFactory;
    @Autowired
    private PagedResourcesAssembler assembler;

    /**
     * Endpoint for creating resources
     *
     * @param entity
     * @param data
     * @return ResponseEntity
     * @throws ResourceException
     */
    @Transactional
    @PostMapping
    public ResponseEntity<BaseEntity> create(@PathVariable String entity,
            @RequestBody String data) throws ResourceException {
        BaseEntity obj = persistence.save(entity, data);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    /**
     * Endpoint for reading a page of resources
     *
     * @param entity
     * @param search
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<PagedResources> read(@PathVariable String entity, Pageable pageable,
            @Nullable @RequestParam String search) {
        SearchCriteria criteria = searchFactory.create(search);
        Page page = query.findAll(entity, SearchSpecification.create(criteria), pageable);
        return new ResponseEntity<>(assembler.toResource(page), HttpStatus.OK);
    }

    /**
     * Endpoint for reading a resource
     *
     * @param entity
     * @param id
     * @return ResponseEntity
     * @throws ResourceException
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> read(@PathVariable String entity,
            @PathVariable Long id) throws ResourceException {
        BaseEntity obj = query.findById(entity, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Endpoint for updating a resource
     *
     * @param entity
     * @param id
     * @param data
     * @return ResponseEntity
     * @throws ResourceException
     */
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<BaseEntity> update(@PathVariable String entity,
            @PathVariable Long id, @RequestBody String data) throws ResourceException {
        BaseEntity obj = persistence.save(entity, id, data);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Endpoint for removing a resource
     *
     * @param entity
     * @param id
     * @return ResponseEntity
     * @throws ResourceException
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseEntity> delete(@PathVariable String entity,
            @PathVariable Long id) throws ResourceException {
        BaseEntity obj = persistence.delete(entity, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
