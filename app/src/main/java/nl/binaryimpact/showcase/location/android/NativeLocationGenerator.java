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

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import nl.binaryimpact.showcase.location.LocationGenerator;
import nl.binaryimpact.showcase.util.Dagger;
import timber.log.Timber;

/**
 * Broadcasts locations that uses Android's native location API.
 */
public class NativeLocationGenerator implements LocationGenerator {

    @Inject Bus bus;
    @Inject LocationManager locationManager;

    private GpsLocationListener gpsListener = new GpsLocationListener();
    private GenericLocationListener alternativeListener = new GenericLocationListener();

    public NativeLocationGenerator(Context context) {
        Dagger.og(context).inject(this);
    }

    @Override
    public void start() {
        bus.register(this);
        startLocationUpdates();
    }

    @Override
    public void stop() {
        stopLocationUpdates();
        bus.unregister(this);
    }

    private void startLocationUpdates() {
        requestLocationUpdates(LocationManager.GPS_PROVIDER, gpsListener);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            requestLocationUpdates(findBestEnabledProvider(), alternativeListener);
        }
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(gpsListener);
        locationManager.removeUpdates(alternativeListener);
    }

    private void requestLocationUpdates(String provider, LocationListener listener) {
        locationManager.removeUpdates(listener);
        locationManager.requestLocationUpdates(provider, MINIMUM_UPDATE_INTERVAL, DISTANCE_THRESHOLD, listener);
    }

    private String findBestEnabledProvider() {
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setBearingRequired(true);
        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
        return locationManager.getBestProvider(criteria, true);
    }

    private class GpsLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Timber.d("New GPS location found: " + location);
            bus.post(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    String alternativeProvider = findBestEnabledProvider();
                    Timber.i("GPS out of service, switching to " + alternativeProvider + " provider");
                    requestLocationUpdates(alternativeProvider, alternativeListener);
                    break;
                case LocationProvider.AVAILABLE:
                    locationManager.removeUpdates(alternativeListener);
                    Timber.i("GPS available, resuming GPS tracking");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Timber.w("GPS temporarily unavailable, waiting for it to come back.");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            Timber.i("GPS enabled, switching to GPS");
            locationManager.removeUpdates(alternativeListener);
        }

        @Override
        public void onProviderDisabled(String provider) {
            String alternativeProvider = findBestEnabledProvider();
            Timber.i("GPS disabled, enabling " + alternativeProvider + " provider");
            requestLocationUpdates(alternativeProvider, alternativeListener);
        }
    }

    private class GenericLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            bus.post(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                    String alternativeProvider = findBestEnabledProvider();
                    Timber.i(provider + " location provider out of service, switching to " + alternativeProvider + " provider");
                    requestLocationUpdates(alternativeProvider, alternativeListener);
                    break;
                case LocationProvider.AVAILABLE:
                    Timber.d(provider + " location provider available, but this is ignored");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Timber.w(provider + " location provider temporarily unavailable, waiting for it to come back.");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            Timber.d(provider + " location provider enabled by user, but this is ignored");
        }

        @Override
        public void onProviderDisabled(String provider) {
            String alternativeProvider = findBestEnabledProvider();
            if (LocationManager.GPS_PROVIDER.equals(alternativeProvider)) {
                Timber.i(provider + " location provider disabled by user, switching to GPS");
                locationManager.removeUpdates(alternativeListener);
            } else {
                Timber.i(provider + " location provider disabled by user, switching to " + alternativeProvider);
                requestLocationUpdates(provider, alternativeListener);
            }
        }
    }
}
