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
package br.com.portozoca.core.error;

import br.com.portozoca.core.i18n.MessageSourceExternalizer;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Error handling
 */
@RestControllerAdvice
public class ErrorHandler {

    /** Title */
    private static final String TITLE = "title";
    /** Message */
    private static final String ERROR = "error";
    /** Stack trace */
    private static final String STACK = "stack";

    @Autowired
    private MessageSourceExternalizer msgs;
    @Autowired
    private HttpServletRequest request;

    /**
     * Handles resource not found exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Object handleResourceNotFound(ResourceNotFoundException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("resource.not.found", e.getId()));
        map.put(ERROR, e.getMessage());
        map.put(STACK, null);
        return map;
    }

    /**
     * Handles resource exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceException.class)
    public Object handleResourceNotFound(ResourceException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("resource.error"));
        map.put(ERROR, e.getMessage());
        map.put(STACK, prettyPrintStackTrace(e.getStackTrace()));
        return map;
    }

    /**
     * Handles resource exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParamException.class)
    public Object handleResourceNotFound(InvalidParamException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("param.invalid"));
        map.put(ERROR, e.getMessage());
        return map;
    }

    /**
     * Lida com Exceptions genéricas da aplicação
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PortoZocaException.class)
    public Object handleCheckedException(PortoZocaException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("application.error"));
        map.put(ERROR, e.getMessage());
        map.put(STACK, prettyPrintStackTrace(e.getStackTrace()));
        return map;
    }

    /**
     * Handles runtime java exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(RuntimeException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("application.error.runtime"));
        map.put(ERROR, e.getMessage());
        map.put(STACK, prettyPrintStackTrace(e.getStackTrace()));
        return map;
    }

    /**
     * Handles checked java exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object handleCheckedException(Exception e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("application.error.exception"));
        map.put(ERROR, e.getMessage());
        map.put(STACK, prettyPrintStackTrace(e.getStackTrace()));
        return map;
    }

    /**
     * Handles HTTP 404 NOT FOUND exceptions
     *
     * @param e
     * @return String
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handle404Errors(NoHandlerFoundException e) {
        Map map = new LinkedHashMap<>();
        map.put(TITLE, msgs.get("page.not.found", request.getRequestURI()));
        map.put(ERROR, e.getMessage());
        map.put(STACK, prettyPrintStackTrace(e.getStackTrace()));
        return map;
    }

    /**
     * Formats an exception stack trace array
     *
     * @param stackTrace
     * @return String
     */
    private String prettyPrintStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement e : stackTrace) {
            sb.append(e.toString()).append('\r').append('\n');
        }
        return sb.toString();
    }

}
