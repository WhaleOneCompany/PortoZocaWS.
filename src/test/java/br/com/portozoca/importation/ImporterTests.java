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
import br.com.portozoca.core.sheets.Data;
import br.com.portozoca.core.sheets.Importer;
import br.com.portozoca.sample.Sample;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test Class for excel importation
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImporterTests {
    
    /** Excel with data for testing */
    private static final String DATA_EXCEL = "test/excel.xlsx";
    @Autowired
    private Importer importer;
    /**
     * Test the excel importation
     * @throws br.com.portozoca.core.error.ImportationException
     * @throws java.net.URISyntaxException
     */
    @Test
    public void excelImportarion() throws ImportationException, URISyntaxException {
        File file = new File(getClass().getClassLoader().getResource(DATA_EXCEL).toURI());
        Data data = importer.fromExcel(file.getAbsolutePath(), Sample.class);
        Long line = new Long(0);
        for (Sample s: (List<Sample>) data.getData()) {
            line++;
            Assert.assertEquals(line, s.getId());
            Assert.assertEquals("ID "+ line + " - Version " + (100 - line), s.getName());
            Assert.assertEquals((100 - line), s.getVersion());
        }
    }
    
}
