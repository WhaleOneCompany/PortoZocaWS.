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

/**
 *
 * @author Spaniol
 */
public class ImportationException extends Exception {

    /**
     * Creates a new instance of <code>ImportationException</code> without
     * detail message.
     */
    public ImportationException() {
    }

    /**
     * Constructs an instance of <code>ImportationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ImportationException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param msg the detail message.
     * @param cause
     */
    public ImportationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
