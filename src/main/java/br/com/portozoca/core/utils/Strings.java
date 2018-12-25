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

/**
 * String utilites
 */
public class Strings {

    /**
     * Capitalizes a string
     *
     * @param input
     * @return String
     */
    public static String capitalize(String input) {
        String low = input.toLowerCase();
        return input.substring(0, 1).toUpperCase().concat(low.substring(1, low.length()));
    }

}
