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
package br.com.portozoca.core.sheets;

import br.com.portozoca.core.error.ImportationException;
import br.com.portozoca.core.db.ImportableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Excel importer
 */
@Service
public class Importer {

    @Autowired
    private ExcelReader excel;

    /**
     * Import Excel file
     *
     * @param <T>
     * @param file
     * @param clazz
     * @return Datas
     * @throws br.com.portozoca.core.error.ImportationException
     */
    public <T extends ImportableEntity> Data<T> fromExcel(String file, Class<T> clazz) throws ImportationException {
        return excel.read(file, 0, clazz);
    }

}
