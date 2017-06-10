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

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.databinding.BindingAdapter
import android.os.Bundle
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

@BindingAdapter("onMapReady")
fun setGoogleMapCallback(mapView: MapView, callback: OnMapReadyCallback) {

    mapView.getMapAsync(callback::onMapReady)

    val activity = mapView.context.getActivityContext()
    val source = activity as LifecycleSource
    source.listener = object : LifecycleCallbacks {
        override fun onCreated(bundle: Bundle?) {
            mapView.onCreate(bundle)
        }

        override fun onStarted() {
            mapView.onStart()
        }

        override fun onResumed() {
            mapView.onResume()
        }

        override fun onPaused() {
            mapView.onPause()
        }

        override fun onStopped() {
            mapView.onStop()
        }

        override fun onSaveInstanceState(bundle: Bundle?) {
            mapView.onSaveInstanceState(bundle)
        }

        override fun onDestroyed() {
            mapView.onDestroy()
        }

        override fun onLowMemory() {
            mapView.onLowMemory()
        }
    }
}

private fun Context.getActivityContext(): Activity {
    when (this) {
        is Activity -> return this
        is ContextWrapper -> return this.baseContext.getActivityContext()
        else -> throw IllegalArgumentException("Context must be an activity")
    }
}