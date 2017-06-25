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

package nl.jozuasijsling.veiligstallen.view.component

import android.databinding.ObservableField
import android.databinding.ObservableFloat
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import nl.jozuasijsling.veiligstallen.data.SafeStorageRepository
import nl.jozuasijsling.veiligstallen.data.domain.BikeShed
import nl.jozuasijsling.veiligstallen.data.domain.BikeShedType
import nl.jozuasijsling.veiligstallen.data.domain.GeoLocation
import nl.jozuasijsling.veiligstallen.view.adapters.asRxObservable
import nl.jozuasijsling.veiligstallen.view.extensions.asLatLng


class Map {

    val cameraLocation = ObservableField<GeoLocation>(GeoLocation(52.3702, 4.8952))
    val cameraZoomLevel = ObservableFloat()


    private lateinit var violetMarker: BitmapDescriptor
    private lateinit var blueMarker: BitmapDescriptor
    private lateinit var azureMarker: BitmapDescriptor
    private lateinit var orangeMarker: BitmapDescriptor
    private lateinit var greenMarker: BitmapDescriptor
    private lateinit var roseMarker: BitmapDescriptor
    private lateinit var magentaMarker: BitmapDescriptor
    private lateinit var redMarker: BitmapDescriptor

    private lateinit var googleMap: GoogleMap

    fun onGoogleMapReady(map: GoogleMap) {

        googleMap = map
        prepareCamera(map)

        violetMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
        blueMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        azureMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
        orangeMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        greenMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        roseMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
        magentaMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
        redMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)

        SafeStorageRepository.getStorage()
                .flatMapIterable { it.bikeSheds }
                .filter { it.coordinates != null }
                .forEach { googleMap.addMarker(MarkerOptions().asBikeShedMarker(it)) }
    }

    private fun prepareCamera(map: GoogleMap) {
        cameraZoomLevel.set(map.cameraPosition.zoom)
        map.moveCamera(cameraLocation.get().toCameraUpdate())

        cameraLocation.asRxObservable().forEach {
            map.animateCamera(it.get().toCameraUpdate())
        }
    }

    private fun MarkerOptions.asBikeShedMarker(shed: BikeShed): MarkerOptions {
        return position(shed.coordinates!!.asLatLng())
                .title(shed.name)
                .snippet(shed.description)
                .icon(when (shed.type) {
                    BikeShedType.GUARDED -> orangeMarker
                    BikeShedType.SAFE_DEPOSIT -> greenMarker
                    BikeShedType.AUTOMATED -> magentaMarker
                    BikeShedType.UNGUARDED -> redMarker
                    BikeShedType.CIVILIAN -> blueMarker
                    BikeShedType.SUPERVISED -> violetMarker
                    BikeShedType.PERSONAL_DRUM -> roseMarker
                    BikeShedType.UNKNOWN -> azureMarker
                })
    }

    private fun GeoLocation.toCameraUpdate(): CameraUpdate {
        return CameraUpdateFactory.newLatLng(asLatLng())
    }
}
