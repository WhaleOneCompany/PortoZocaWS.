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

import br.com.portozoca.core.db.BaseEntity;
import br.com.portozoca.core.entity.EntityPersistor;
import br.com.portozoca.core.error.MultipartException;
import br.com.portozoca.core.error.ResourceException;
import br.com.portozoca.core.i18n.MessageSourceExternalizer;
import br.com.portozoca.core.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service to store multipart data
 */
@Service
public class MultipartService {

    @Autowired
    private StorageService storageService;
    @Autowired
    private EntityPersistor persistor;
    @Autowired
    private MessageSourceExternalizer msgs;

    public final MultipartData<BaseEntity> saveAndStore(String entity, String body, MultipartFile file) throws MultipartException {
        try {
            storageService.save(file);
            return new MultipartData<>(file, persistor.save(entity, body));
        } catch (ResourceException e) {
            storageService.delete(file.getOriginalFilename());
            throw new MultipartException(msgs.get("multipart.error", e));
        }
    }

}
