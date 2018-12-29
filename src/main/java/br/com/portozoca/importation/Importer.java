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
package br.com.portozoca.importation;

import br.com.portozoca.core.error.ImportationException;
import br.com.portozoca.core.db.ImportableEntity;

/**
 * Excel importer
 */
public class Importer {
    
    /** File to import */
    private final String file;
    
    /**
     * Create a new immporter
     * 
     * @param file 
     */
    public Importer(String file) {
        this.file = file;
    }
    
    /**
     * Import Excel file
     * 
     * @param <T>
     * @param clazz
     * @return Datas
     * @throws br.com.portozoca.importation.ImportationException
     */
    public <T extends ImportableEntity> Data<T> importExcel(Class<T> clazz) throws ImportationException {
        ExcelReader excel = new ExcelReader(file);
        return excel.read(0, clazz);
    }
}
