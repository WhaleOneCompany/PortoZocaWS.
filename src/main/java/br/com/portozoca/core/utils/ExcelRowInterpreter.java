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

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * Excel row interpreter
 */
public class ExcelRowInterpreter {
    
    /**
     * Returns a String list containing the cell of the row
     * 
     * @param row
     * @return List<String>
     */
    public static List<String> interpreter(Row row) {
        List<String> result = new ArrayList();
        DataFormatter dataFormatter = new DataFormatter();
        for(Cell cell: row) {
            result.add(dataFormatter.formatCellValue(cell));
        }
        return result;
    }
}
