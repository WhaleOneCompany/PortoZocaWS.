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

import br.com.portozoca.Profiles;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Production profile-only configurations
 */
@Configuration
@Profile(Profiles.PROFILE_PROD)
public class ProductionConfig {

    /** APR Based protocol */
    private static final String APR_BASED_PROTOCOL = "org.apache.coyote.http11.Http11AprProtocol";

    @Value("${server.tomcat.apr.enabled:false}")
    private boolean aprEnabled;

    /**
     * Servlet container
     *
     * @param tomcat
     * @return ServletWebServerFactory
     */
    @Bean
    public ServletWebServerFactory servletContainer(TomcatServletWebServerFactory tomcat) {
        if (aprEnabled) {
            tomcat.addContextLifecycleListeners(new AprLifecycleListener());
            tomcat.setProtocol(APR_BASED_PROTOCOL);
        }
        return tomcat;
    }

}
