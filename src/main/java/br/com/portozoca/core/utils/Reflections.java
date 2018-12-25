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
package br.com.portozoca.core.utils;

import java.lang.reflect.Method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

/**
 * Reflection utilities
 */
public final class Reflections {

    /**
     * Returns true if the HandlerMethod of a controlller have an annotation
     *
     * @param handlerMethod
     * @param anottation
     * @return boolean
     */
    public static boolean hasAnnotation(HandlerMethod handlerMethod, Class anottation) {
        Method method = handlerMethod.getMethod();
        Class controller = method.getDeclaringClass();
        if (controller.isAnnotationPresent(RestController.class) || controller.isAnnotationPresent(Controller.class)) {
            if (method.isAnnotationPresent(anottation) || controller.isAnnotationPresent(anottation)) {
                return true;
            }
        }
        return false;
    }

}
