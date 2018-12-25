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

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;

/**
 * Manipulates profiles
 */
public class Profiles {

    /** Spring property - Default Profile */
    public static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    /** Development profile name */
    public static final String PROFILE_DEV = "dev";
    /** Homologation profile name */
    public static final String PROFILE_HOM = "hom";
    /** Production profile name */
    public static final String PROFILE_PROD = "prod";

    /**
     * Adds a default profile to the application
     *
     * The default profile to use when no other profiles are defined This cannot
     * be set in the <code>application.yml</code> file. See
     * https://github.com/spring-projects/spring-boot/issues/1219
     *
     * @param app
     */
    public static final void addDefault(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put(SPRING_PROFILE_DEFAULT, PROFILE_DEV);
        app.setDefaultProperties(defProperties);
    }

}
