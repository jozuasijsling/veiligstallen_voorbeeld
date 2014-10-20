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

/**
 * Generated id fields should have the name <code>_id</code> and all such fields are excluded
 * from serialization and deserialization by Gson.
 */
public class GeneratedIdExclusionStrategy implements ExclusionStrategy {

    /**
     * Always skips fields with field name <code>"_id"</code>. These fields are reserved for the local database.
     *
     * @param f Describes the field to be tested for exclusion.
     * @return True if the field name equals <code>"_id"</code>.
     */
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return "_id".equals(f.getName());
    }

    /**
     * Classes are never skipped.
     *
     * @param clazz Describes the class.
     * @return Always false.
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
