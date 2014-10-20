/*
 * Copyright 2014 Jozua Sijsling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.binaryimpact.showcase.util.xml;

import android.content.ContentValues;
import android.database.Cursor;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.StringWriter;

import nl.binaryimpact.showcase.model.veiligstallen.OpeningHours;
import nl.qbusict.cupboard.convert.EntityConverter;
import nl.qbusict.cupboard.convert.FieldConverter;
import timber.log.Timber;

/**
 * Openings hours are annoying to store in SQLite so we just store them as plain xml by
 * converting with Simple whenever we read from or write to the local database.
 */
public class OpeningsHoursConverter implements FieldConverter<OpeningHours> {

    /**
     * Parses a OpeningHours value from a (database) cursor.
     *
     * @param cursor      Contains opening hours at specified index.
     * @param columnIndex The specified column index.
     * @return Null if any parse error occurs.
     */
    @Override public OpeningHours fromCursorValue(Cursor cursor, int columnIndex) {
        String xmlString = cursor.getString(columnIndex);
        if (xmlString == null || xmlString.length() == 0) {
            return null;
        }
        try {
            return new Persister().read(OpeningHours.class, xmlString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Stores an <code>OpeningHours</code> value as XML into a <code>ContentValues</code> object.
     *
     * @param value  Value to write.
     * @param key    Content values index.
     * @param values Object to write to.
     */
    @Override public void toContentValue(OpeningHours value, String key, ContentValues values) {
        StringWriter writer = new StringWriter();
        try {
            new Persister().write(value, writer);
            values.put(key, writer.toString());
        } catch (Exception e) {
            // TODO replace with custom exception
            throw new RuntimeException("Failed to convert value to xml", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                Timber.e("Could not close I/O stream!", e);
            }
        }
    }

    /**
     * Store plain XML as the SQLite TEXT type.
     *
     * @return The following type: {@link EntityConverter.ColumnType#TEXT ColumnType.TEXT}.
     */
    @Override public EntityConverter.ColumnType getColumnType() {
        return EntityConverter.ColumnType.TEXT;
    }
}
