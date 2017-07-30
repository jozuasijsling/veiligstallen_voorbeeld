/*
 * Copyright (c) 2017 Jozua Sijsling
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

package nl.jozuasijsling.veiligstallen.view.adapters

import android.databinding.BindingAdapter
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import nl.jozuasijsling.veiligstallen.platform.decoupling.DecoupledActivityComponent
import nl.jozuasijsling.veiligstallen.platform.decoupling.PlatformCallback.OnLowMemory
import nl.jozuasijsling.veiligstallen.platform.decoupling.PlatformCallback.OnSaveInstanceState
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleEvent.*

@BindingAdapter("onMapReady")
fun setGoogleMapCallback(component: DecoupledActivityComponent,
                         mapView: MapView,
                         callback: OnMapReadyCallback) {

    mapView.getMapAsync(callback::onMapReady)

    component.lifecycle.subscribe {
        when (it) {
            is Create -> mapView.onCreate(it.bundle)
            is Start -> mapView.onStart()
            is Resume -> mapView.onResume()
            is Pause -> mapView.onPause()
            is Stop -> mapView.onStop()
            is Destroy -> mapView.onDestroy()
        }
    }
    component.platformCallbacks.subscribe {
        when (it) {
            is OnSaveInstanceState -> mapView.onSaveInstanceState(it.outState)
            is OnLowMemory -> mapView.onLowMemory()
        }
    }
}