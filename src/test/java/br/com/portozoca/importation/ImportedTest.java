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

import br.com.portozoca.core.entity.EntityService;
import br.com.portozoca.core.error.ImportationException;
import br.com.portozoca.core.sheets.Importer;
import br.com.portozoca.entity.travel.Travel;
import java.io.File;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Spaniol
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportedTest {
    
    @Autowired
    private Importer importer;    
    @Autowired
    private EntityService entities;
    
    private static final String DATA_EXCEL = "test/travel.xlsx";
    
    @Test
    public void createEntities() throws ImportationException, URISyntaxException {
        importer.config(true);
        File file = new File(getClass().getClassLoader().getResource(DATA_EXCEL).toURI());
        Imported imp = new Imported(file.getAbsolutePath(), importer);
        
        Travel t = imp.buildTravel("Ship", "Travel");
        
        Assert.assertEquals("CSUSFS025", t.getBls().get(0).getItems().get(0).getBillOfLading().getBl());
        Assert.assertEquals("BXJ1610022509L", t.getBls().get(0).getItems().get(0).getSerie());
        Assert.assertEquals("BXJ1610012513L", t.getBls().get(0).getItems().get(1).getSerie());
        Assert.assertEquals("CSUSFS004", t.getBls().get(1).getItems().get(0).getBillOfLading().getBl());
        Assert.assertEquals("L6003458811A1000", t.getBls().get(1).getItems().get(0).getSerie());
        Assert.assertEquals("L6003458811A2000", t.getBls().get(1).getItems().get(1).getSerie());
        Assert.assertEquals("L6003458811A3000", t.getBls().get(1).getItems().get(2).getSerie());
        Assert.assertEquals("TXGSFDS10", t.getBls().get(2).getItems().get(0).getBillOfLading().getBl());
        Assert.assertEquals("YC20160831C08", t.getBls().get(2).getItems().get(0).getSerie());
    }
    
}
