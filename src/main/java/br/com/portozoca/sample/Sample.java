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
package br.com.portozoca.sample;

import br.com.portozoca.core.db.ImportableEntity;
import br.com.portozoca.core.utils.ExcelRowInterpreter;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * Sample entity
 */
@Entity(name = "Sample")
@Component("sample")
public class Sample extends ImportableEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ImportableEntity buildFromRow(Row row) {
        String[] cell = ExcelRowInterpreter.interpreter(row).toArray(new String[] {});
        this.id = Long.parseLong(cell[0]);
        this.name = cell[1];
        this.version = Long.parseLong(cell[2]);
        return this;
    }

    @Override
    public String toString() {
        return "Sample{" + "Id=" + id + " name=" + name + " version=" + version + '}';
    }
}
