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

package nl.binaryimpact.showcase.model.veiligstallen;

import org.simpleframework.xml.Element;

/**
 */
public class OpeningHours {

    @Element(name = "Ma") private Day monday;
    @Element(name = "Di") private Day tuesday;
    @Element(name = "Wo") private Day wednesday;
    @Element(name = "Do") private Day thursday;
    @Element(name = "Fr") private Day friday;
    @Element(name = "Za") private Day saturday;
    @Element(name = "Zo") private Day sunday;

    public OpeningHours() {
        super();
    }

    public static class Day {
        @Element(name = "Open")
        private String open;
        @Element(name = "Dicht")
        private String closed;
    }
}
