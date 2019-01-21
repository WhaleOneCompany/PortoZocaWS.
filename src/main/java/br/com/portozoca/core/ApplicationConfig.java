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
package br.com.portozoca.core;

import br.com.portozoca.PortoZocaWS;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;
import org.reflections.Reflections;

/**
 * Allow to use command line args to configure the app and specialize spring
 * beans
 */
@Component
public final class ApplicationConfig {

    /** Application default locale */
    private static final Locale DEF_LOCALE = new Locale("pt", "BR");
    /** Pacote base da aplicação */
    private static final String BASE_PKG = PortoZocaWS.class.getPackage().getName();

    /**
     * Realiza a configuração
     *
     * @param args
     */
    public final void config(String[] args) {
    }

    /**
     * Locale view resolver, for the I18N Strings
     * https://github.com/spring-projects/spring-security/blob/master/core/src/main/resources/org/springframework/security/messages_pt_BR.properties
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(DEF_LOCALE);
        return slr;
    }

    /**
     * Reflections lib bean
     *
     * @return Reflections
     */
    @Bean
    public Reflections reflections() {
        return new Reflections(BASE_PKG);
    }

}
