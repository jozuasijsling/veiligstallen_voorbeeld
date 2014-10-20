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

package nl.binaryimpact.showcase.location;

import android.location.Location;

/**
 */
public interface LocationStrategies {

    /**
     * Determines which if a location update is considered 'better' than the current best last
     * known location.
     *
     * @param location            the location to compare
     * @param currentBestLocation the last location considered better
     * @return True only if location1 is considered better than location2
     */
    boolean isBetterCurrentLocation(Location location, Location currentBestLocation);
}
