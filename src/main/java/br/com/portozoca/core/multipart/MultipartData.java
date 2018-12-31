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
package br.com.portozoca.core.multipart;

import br.com.portozoca.core.db.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * A class to represent multipart data
 *
 * @param <T>
 */
public class MultipartData<T extends BaseEntity> {

    private final MultipartFile file;
    private final T data;

    public MultipartData(MultipartFile file, T data) {
        this.file = file;
        this.data = data;
    }

    public MultipartFile getFile() {
        return file;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "MultipartData{" + "file=" + file.getOriginalFilename() + ", body=" + data + '}';
    }

}
