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

import java.util.Date;

/**
 * Manipulates dates
 */
public final class Dates {

    /** Milissegundos in a second */
    private static final int MILLISECONDS_IN_A_SECOND = 1000;

    /**
     * Returns true if the first date is before than the second
     *
     * @param start
     * @param end
     * @return boolean
     */
    public static boolean isBefore(Date start, Date end) {
        if (start == null || end == null) {
            return false;
        }
        return start.getTime() < end.getTime();
    }

    /**
     * Returns true if the first date is after than the second
     *
     * @param start
     * @param end
     * @return boolean
     */
    public static boolean isAfter(Date start, Date end) {
        return !isBefore(start, end);
    }

    /**
     * Returns a new Date object plus some seconds
     *
     * @param start
     * @param numSeconds
     * @return Date
     */
    public static Date addSeconds(Date start, int numSeconds) {
        if (start == null) {
            return null;
        }
        return new Date(start.getTime() + numSeconds * MILLISECONDS_IN_A_SECOND);
    }

}
