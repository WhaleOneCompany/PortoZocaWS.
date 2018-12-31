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
package br.com.portozoca.core.multipart;

import br.com.portozoca.core.error.MultipartException;
import br.com.portozoca.core.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Multipart controller
 */
@RestController
@RequestMapping(path = "v1/multipart/{entity}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MultipartController {

    @Autowired
    private MultipartService multiparts;
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity save(@PathVariable String entity,
            @RequestParam MultipartFile file,
            @RequestParam String body
    ) throws MultipartException {
        multiparts.saveAndStore(entity, body, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity listFiles(@PathVariable String entity) {
        return new ResponseEntity(storageService.getFiles(), HttpStatus.OK);
    }

}
