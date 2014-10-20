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
import org.simpleframework.xml.Root;

import nl.binaryimpact.showcase.util.annotation.UsedByDeserializers;

/**
 */
@Root(name = "Sectie")
public class Section {

    @Element(name = "ID") private String id;
    @Element(name = "Naam") private String name;
    @Element(name = "Capaciteit") private int capacity;
    @Element(name = "Vrij") private int unoccupied;
    @Element(name = "Bezet") private int occupied;

    @UsedByDeserializers
    public Section() {
    }

    public Section(String name, int capacity, int unoccupied, int occupied) {
        this.name = name;
        this.capacity = capacity;
        this.unoccupied = unoccupied;
        this.occupied = occupied;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUnoccupied() {
        return unoccupied;
    }

    public void setUnoccupied(int unoccupied) {
        this.unoccupied = unoccupied;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }
}
