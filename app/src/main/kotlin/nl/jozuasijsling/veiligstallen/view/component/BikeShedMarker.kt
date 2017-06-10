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

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import nl.jozuasijsling.veiligstallen.data.domain.BikeShed
import nl.jozuasijsling.veiligstallen.view.extensions.asLatLng


class BikeShedMarker(private val shed: BikeShed) : ClusterItem {

    override fun getSnippet(): String
            = shed.description

    override fun getTitle(): String
            = shed.name

    override fun getPosition(): LatLng
            = shed.coordinates!!.asLatLng()
}