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

package nl.binaryimpact.showcase.location.gms;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import nl.binaryimpact.showcase.location.LocationGenerator;
import timber.log.Timber;

/**
 * Location generator that uses the Location API from Google Play services.
 */
public class GmsLocationGenerator implements LocationGenerator, LocationListener {

    private final GoogleApiClient mGmsClient;

    @Inject
    Bus bus;

    public GmsLocationGenerator(Context context) {
        mGmsClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Timber.d("GMS connected with params: " + bundle);
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Timber.w("GMS connection suspended with code: " + i);
                    }
                }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Timber.w("GMS connection failed with description: " + connectionResult);
                    }
                }).build();
    }

    @Override
    public void start() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGmsClient,
                new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setSmallestDisplacement(DISTANCE_THRESHOLD)
                        .setFastestInterval(MINIMUM_UPDATE_INTERVAL)
                        .setInterval(MINIMUM_UPDATE_INTERVAL / 5)
                , this);
        bus.register(this);
    }

    @Override
    public void stop() {
        bus.unregister(this);
        LocationServices.FusedLocationApi.removeLocationUpdates(mGmsClient, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO compare quality of locations?
        bus.post(location);
    }
}
