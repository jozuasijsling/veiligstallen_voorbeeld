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

package nl.jozuasijsling.veiligstallen

import android.app.Activity
import nl.jozuasijsling.veiligstallen.view.screen.MapScreen
import java.util.*

class ScreenNavigation {

    private val activityStack = ArrayDeque<Activity>(4)
    private val screenStack = ArrayDeque<Any>(4)

    fun attach(activity: Activity) {
        activityStack.push(activity)

        if (screenStack.isEmpty()) {
            screenStack.push(MapScreen())
            // Wire logic
        }

        screenStack.peekLast()
    }

    fun detach(activity: Activity) {
        activityStack.remove(activity)
    }



}
