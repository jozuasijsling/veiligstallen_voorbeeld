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

import org.simpleframework.xml.core.Persister;

import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * A Retrofit xml converter using Simple.
 */
public class SimpleXmlConverter implements Converter {
    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        try {
            return new Persister().read(type, body.in());
        } catch (Exception e) {
            throw new ConversionException("Could not parse feed, see cause.", e);
        }
    }

    @Override public TypedOutput toBody(Object object) {
        throw new UnsupportedOperationException("Writing XML is not supported");
    }
}
