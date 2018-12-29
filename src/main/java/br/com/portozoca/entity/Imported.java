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
package br.com.portozoca.entity;

import br.com.portozoca.core.db.ImportableEntity;
import br.com.portozoca.core.utils.ExcelRowInterpreter;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class to represent a imported register
 */
public class Imported extends ImportableEntity {
    
    /** Bill of lading */
    private String bl;
    /** Customer */
    private String customer;
    /** Product */
    private String product;
    /** Serie */
    private String serie;
    /** Gross weight */
    private Long grossWeight;
    /** Net weight */
    private Long netWeight;
    /** Ship */
    private String ship;

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Long grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Long getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Long netWeight) {
        this.netWeight = netWeight;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }
    
    @Override
    public ImportableEntity buildFromRow(Row row) {
        String[] cells = ExcelRowInterpreter.interpreter(row).toArray(new String[]{});
        bl = cells[1];
        customer = cells[4];
        product = cells[5];
        serie = cells[6];
        try {
            grossWeight = Long.parseLong(cells[9]);
        } catch (NumberFormatException ex){
            grossWeight = 0L;
        }
        try {
            netWeight = Long.parseLong(cells[10]);
        } catch (NumberFormatException ex){
            netWeight = 0L;
        }
        ship = cells[11];
        return this;
    }
    
}
