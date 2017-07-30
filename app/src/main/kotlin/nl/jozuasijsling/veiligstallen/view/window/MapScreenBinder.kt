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

package nl.jozuasijsling.veiligstallen.view.window

import android.app.Activity
import android.databinding.DataBindingUtil
import nl.jozuasijsling.veiligstallen.R
import nl.jozuasijsling.veiligstallen.databinding.MapScreenBinding
import nl.jozuasijsling.veiligstallen.platform.decoupling.DecoupledActivityComponent
import nl.jozuasijsling.veiligstallen.view.screen.MapScreen

class MapScreenBinder(val viewModel: MapScreen) : WindowModelBinder {

    override fun bind(activity: Activity, component: DecoupledActivityComponent) {
        val binding = DataBindingUtil.setContentView<MapScreenBinding>(
                activity, R.layout.map_screen, component)
        binding.map = viewModel.map
        binding.searchBar = viewModel.searchBar
    }
}
