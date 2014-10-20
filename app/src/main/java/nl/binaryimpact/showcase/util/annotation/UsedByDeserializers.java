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

package nl.binaryimpact.showcase.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Useful for suppressing 'unused' warnings on default no-args constructors required by Gson.
 * <p>
 * Model objects may not have a default no-args constructor, but it is recommended by Gson to
 * provide one. Since Gson only calls the constructor through dynamic reflection, the compiler
 * may determine that the constructor is unused and issue a warning. Use this annotation to
 * suppress warnings for such constructors.
 * </p>
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.CONSTRUCTOR)
public @interface UsedByDeserializers {
}
