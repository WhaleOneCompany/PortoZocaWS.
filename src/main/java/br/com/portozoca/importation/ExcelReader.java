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

import java.io.File;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * Class to read Excel files
 */
public class ExcelReader {
    
    private final String file;
    
    public ExcelReader(String file) {
        this.file = file;
    }
    
    /**
     * Read the excel file
     * 
     * @param <T>
     * @param page
     * @param clazz
     * @return Data
     * @throws ImportationException 
     */
    public <T extends ImportableEntity> Data<T> read(int page, Class<T> clazz) throws ImportationException {
        try {
            Workbook workbook = WorkbookFactory.create(new File(file));
            Data<T> data = new Data();
            Sheet sheet = workbook.getSheetAt(page);
            for (Row row: sheet) {
                data.add(buildRecord(row, clazz));
            }
            return data;
        } catch (IOException | InvalidFormatException | EncryptedDocumentException ex) {
            throw new ImportationException("Error to read the file", ex);
        }
    }
    
    /**
     * Build the record and returns a instance of entity
     * 
     * @param <T>
     * @param row
     * @param clazz
     * @return T
     * @throws ImportationException 
     */
    private <T extends ImportableEntity> T buildRecord(Row row, Class<T> clazz) throws ImportationException {
        try {
            T entity = clazz.newInstance();
            entity.buildFromRow(row);
            return entity;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ImportationException("Error to build record", ex);
        }
    }
}
