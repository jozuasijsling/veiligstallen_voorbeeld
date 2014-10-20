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

package nl.binaryimpact.showcase.location.android;

import android.location.Location;
import android.location.LocationManager;

import nl.binaryimpact.showcase.location.LocationStrategies;
import nl.binaryimpact.showcase.location.PassiveUserLocator;


public class NativePassiveUserLocator implements PassiveUserLocator {

    private LocationStrategies mLocationStrategies;
    private LocationManager mLocationManager;

    public NativePassiveUserLocator(LocationStrategies locationStrategies, LocationManager locationManager) {
        mLocationStrategies = locationStrategies;
        mLocationManager = locationManager;
    }

    @Override
    public Location getLastKnownLocation() {
        Location lastKnownGpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location lastKnownNetworkLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (lastKnownNetworkLocation == null) {
            return lastKnownGpsLocation;
        }
        if (lastKnownGpsLocation == null) {
            return lastKnownNetworkLocation;
        }
        return mLocationStrategies.isBetterCurrentLocation(lastKnownGpsLocation, lastKnownNetworkLocation)
                ? lastKnownGpsLocation : lastKnownNetworkLocation;
    }
}
