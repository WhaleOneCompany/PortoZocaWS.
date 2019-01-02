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
package br.com.portozoca.core.search;

import br.com.portozoca.core.error.InvalidParamException;
import br.com.portozoca.core.i18n.MessageSourceExternalizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Perin
 */
@Service
public class SearchCriteriaFactory {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MessageSourceExternalizer msgs;

    public final SearchCriteria create(String body) {
        if (body != null && !body.trim().isEmpty()) {
            try {
                return mapper.readValue(body, SearchCriteria.class);
            } catch (IOException ex) {
                throw new InvalidParamException(msgs.get("bad.filter"));
            }
        }
        return null;
    }

}
