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
import br.com.portozoca.entity.billoflading.BillOfLading;
import br.com.portozoca.entity.dimension.Dimension;
import br.com.portozoca.entity.itemsofbl.ItemsOfBl;
import br.com.portozoca.entity.itemsofbl.ItemsOfBlSource;
import br.com.portozoca.entity.itemsofbl.ItemsOfBlStatus;
import br.com.portozoca.entity.travel.Travel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to represent a imported register
 */
public class Imported {

    private final Data<ImportedRecord> records;

    public Imported(String xlsFile, Importer importer) throws ImportationException {
        records = importer.fromExcel(xlsFile, ImportedRecord.class);
    }
    
    /**
     * Build the entities of the travel and yours items
     * 
     * @param ship
     * @param travel 
     * @return Travel
     */
    public Travel buildTravel(String ship, String travel) {
        Travel t = new Travel();
        t.setTravel(travel);
        t.setShip(ship);
        t.setBls(new ArrayList<BillOfLading>());
        String s_bl = "";
        BillOfLading bl = null;
        for (ImportedRecord record: records.getData()) {
            if (!s_bl.equals(record.getBl())) {
                s_bl = record.getBl();
                if (bl != null) {
                    t.getBls().add(bl);
                }
                bl = new BillOfLading();
                bl.setBl(s_bl);
                bl.setTravel(t);
                bl.setCustomer(record.getCustomer());
                bl.setItems(new ArrayList<ItemsOfBl>());
            }
            bl.getItems().add(buildItemOfBl(bl, record));
        }
        if (bl != null) {
            t.getBls().add(bl);
        }
        return t;
    }
    
     
    /* Build the item of the record
     *
     * @param billOfLading
     * @param record
     * @return ItemsOfBl
     */
    private ItemsOfBl buildItemOfBl(BillOfLading billOfLading, ImportedRecord record) {
        ItemsOfBl item = new ItemsOfBl();
        item.setBillOfLading(billOfLading);
        item.setSerie(record.getSerie());
        item.setSource(ItemsOfBlSource.IMPORTED);
        item.setStatus(ItemsOfBlStatus.MANIFESTED);
        Dimension dimension = new Dimension();
        dimension.setWeight(record.getGrossWeight());
        dimension.setItemOfBl(item);
        item.setDimension(dimension);
        return item;
    }
}
