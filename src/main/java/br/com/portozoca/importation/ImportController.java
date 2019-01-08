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
package br.com.portozoca.importation;

import br.com.portozoca.core.entity.EntityPersistor;
import br.com.portozoca.core.entity.EntityService;
import br.com.portozoca.core.error.ImportationException;
import br.com.portozoca.core.error.ResourceException;
import br.com.portozoca.core.sheets.Importer;
import br.com.portozoca.core.utils.Files;
import br.com.portozoca.entity.travel.Travel;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller to import a xls
 */
@RestController
public class ImportController {

    @Autowired
    private EntityService entities;
    @Autowired
    private EntityPersistor persistor;
    @Autowired
    private Importer importer;    
    
    
    /**
     * Import a travel
     *
     * @param file
     * @param travel
     * @param ship
     * @return ResponseEntity
     * @throws br.com.portozoca.core.error.ResourceException
     */
    @PostMapping("v1/import/xls")
    public ResponseEntity importTravel(
            @RequestParam MultipartFile file,
            @RequestParam String travel, @RequestParam String ship) throws ResourceException, IOException, ImportationException {
        System.out.println("File: " + file.getOriginalFilename());
        System.out.println("Travel: " + travel);
        System.out.println("Ship: " + ship);
        byte[] fromStream = Files.fromStream(file.getInputStream());
        File f = Files.save("C:\\TMP\\" + travel + "_" + ship, fromStream);
        importer.config(true);
        Imported imp = new Imported(f.getAbsolutePath(), importer);
        Travel t = imp.buildTravel("Ship", "Travel");
        entities.repository(Travel.class).save(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

}
