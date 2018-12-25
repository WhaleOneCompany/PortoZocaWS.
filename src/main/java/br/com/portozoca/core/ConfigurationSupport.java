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

import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Web MVC configuration
 */
@Configuration
public class ConfigurationSupport extends WebMvcConfigurationSupport {

    /** Internalization (i18n) messages */
    private static final String MESSAGE_SOURCE = "/strings/messages";
    /** Resources URL Handler */
    private static final String RESOURCES_HANDLER = "/**";
    /** CORS URL Handler */
    private static final String CORS_HANDLER = "/**";
    /** Public Resources location */
    private static final String[] RESOURCES_LOCATION = {
        "classpath:/static/", "classpath:/public/"
    };
    /** Cache (in seconds) for messages - Forever (-1 means forever) */
    private static final int MSG_CACHE_SECONDS = 60;

    /**
     * Gerenciadores e Mapeadores de requisições
     *
     * @return RequestMappingHandlerMapping
     */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
        return requestMappingHandlerMapping;
    }

    /**
     * Interceptors
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * Static resources
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }

    /**
     * CORs
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CORS_HANDLER).allowCredentials(true);
    }

    /**
     * Validators
     *
     * @return Validator
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messagesSource());
        return validator;
    }

    /**
     * Argument resolvers
     *
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    /**
     * Message sources
     *
     * @return MessageSource
     */
    private MessageSource messagesSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(MSG_CACHE_SECONDS);
        return messageSource;
    }

}
