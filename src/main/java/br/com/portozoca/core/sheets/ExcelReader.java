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
import br.com.portozoca.core.i18n.MessageSourceExternalizer;
import java.io.File;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to read Excel files
 */
@Service
public class ExcelReader {

    @Autowired
    private MessageSourceExternalizer msgs;

    /**
     * Read the excel file
     *
     * @param <T>
     * @param filename
     * @param page
     * @param clazz
     * @return Data
     * @throws ImportationException
     */
    public <T extends ImportableEntity> Data<T> read(String filename, int page, Class<T> clazz) throws ImportationException {
        try {
            Workbook workbook = WorkbookFactory.create(new File(filename));
            return read(workbook, page, clazz);
        } catch (IOException ex) {
            throw new ImportationException(msgs.get("file.read.fail", filename), ex);
        } catch (InvalidFormatException ex) {
            throw new ImportationException(msgs.get("file.format.invalid", filename), ex);
        }
    }

    /**
     * Read the excel file
     *
     * @param <T>
     * @param workbook
     * @param page
     * @param clazz
     * @return Data
     * @throws ImportationException
     */
    public <T extends ImportableEntity> Data<T> read(Workbook workbook, int page, Class<T> clazz) throws ImportationException {
        try {
            Data<T> data = new Data();
            Sheet sheet = workbook.getSheetAt(page);
            for (Row row : sheet) {
                data.add(buildRecord(row, clazz));
            }
            return data;
        } catch (EncryptedDocumentException ex) {
            throw new ImportationException(msgs.get("file.content.encrypted"), ex);
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
            return (T) clazz.newInstance().buildFromRow(row);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ImportationException(msgs.get("fail.build.record"), ex);
        }
    }
}
