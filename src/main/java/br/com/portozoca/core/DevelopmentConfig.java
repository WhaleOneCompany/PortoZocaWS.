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
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

/**
 * Development profile-only configurations
 *
 * https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-one-configuration/
 */
@Configuration
@Profile(Profiles.PROFILE_DEV)
public class DevelopmentConfig {

    /** JSON with data to initialize a database */
    private static final String DATA_JSON = "test/data.json";

    /**
     * Called after the object initialization
     *
     * @throws java.lang.Exception
     */
    @PostConstruct
    public final void onInit() throws Exception {
    }

    /**
     * Bean to populate the development database
     *
     * @return Jackson2RepositoryPopulatorFactoryBean
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setMapper(new ObjectMapper().setVisibility(FIELD, ANY));
        factory.setResources(new Resource[] { new ClassPathResource(DATA_JSON) });
        factory.setBeanClassLoader(ApplicationConfig.class.getClassLoader());
        return factory;
    }

}
