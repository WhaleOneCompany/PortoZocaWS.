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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Class to manipulates files
 */
public class Files {
    
    /**
     * Read file from inputStrem
     * 
     * @param is
     * @return byte[]
     * @throws java.io.IOException 
     */
    public static byte[] fromStream(InputStream is) throws IOException {
        return IOUtils.toByteArray(is);
    }
    
    /**
     * save a byteArray in File
     * 
     * @param path
     * @param buffer
     * @return File
     * @throws IOException 
     */
    public static File save(String path, byte[] buffer) throws IOException {
        File file = new File(path);
        FileUtils.writeByteArrayToFile(file, buffer);
        return file;
    }
    
}
