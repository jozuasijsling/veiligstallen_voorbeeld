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

package nl.binaryimpact.showcase.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import nl.binaryimpact.showcase.util.annotation.GsonIgnore;


/**
 * Allows fields or classes annotated with {@link nl.binaryimpact.showcase.util.annotation.GsonIgnore} to be excluded
 * from serialization and deserialization by Gson.
 */
public class GsonIgnoreExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(GsonIgnore.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(GsonIgnore.class) != null;
    }
}
