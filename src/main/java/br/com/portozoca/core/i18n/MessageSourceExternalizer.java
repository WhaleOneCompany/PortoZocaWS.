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
package br.com.portozoca.core.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Internationalizes all messages
 */
@Component
public final class MessageSourceExternalizer {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceExternalizer() {
    }

    /**
     * Returns a string from the StringBundle
     *
     * @param code
     * @return String
     */
    public String get(String code) {
        return get(code, new Object[0]);
    }

    /**
     * Returns a string from the StringBundle, with args that can be formatted
     *
     * @param code
     * @param args
     * @return String
     */
    public String get(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
