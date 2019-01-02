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
package br.com.portozoca.sample;

import br.com.portozoca.core.storage.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Sample controller
 */
@RestController
public class SampleController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping(value = { "/hello", "/sample", "/test", "/teste" })
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping(value = { "/file" })
//    public ResponseEntity<String> file(@RequestParam("file") MultipartFile file) {
    public ResponseEntity<String> file(@RequestParam("file") MultipartFile file, @RequestParam("data") String body) throws IOException {
        Sample data = mapper.readValue(body, Sample.class);
        System.out.println(body);
        storageService.save(file);
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

}
