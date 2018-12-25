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
package br.com.portozoca;

import br.com.portozoca.core.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class
 */
@Configuration
@EnableScheduling
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableConfigurationProperties
public class PortoZocaWS {

    /**
     * Main entry point
     *
     * @param args
     */
    public static final void main(String[] args) {
        SpringApplication app = new SpringApplication(PortoZocaWS.class);
        Profiles.addDefault(app);
        app.run(args).getBean(ApplicationConfig.class).config(args);
    }

}
