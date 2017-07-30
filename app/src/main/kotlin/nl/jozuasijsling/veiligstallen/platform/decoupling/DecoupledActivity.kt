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

package nl.jozuasijsling.veiligstallen.platform.decoupling

import android.app.Activity
import android.content.ComponentCallbacks2
import android.os.Bundle
import io.reactivex.subjects.PublishSubject
import nl.jozuasijsling.veiligstallen.platform.decoupling.PlatformCallback.*
import nl.jozuasijsling.veiligstallen.view.adapters.lifecycleStream
import nl.jozuasijsling.veiligstallen.view.window.WindowModelStack


class DecoupledActivity : Activity() {

    private val _events = PublishSubject.create<PlatformCallback>()!!
    val events = _events.hide()!!

    private val component = DecoupledActivityComponent(events, lifecycleStream())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowModelStack.bind(this, component)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_COMPLETE) {
            _events.onNext(OnLowMemory())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        _events.onNext(OnBackPressed())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        _events.onNext(OnSaveInstanceState(outState))
    }

    override fun onDestroy() {
        super.onDestroy()
        _events.onComplete()
    }

}

sealed class PlatformCallback {
    class OnLowMemory : PlatformCallback()
    class OnBackPressed : PlatformCallback()
    class OnSaveInstanceState(val outState: Bundle) : PlatformCallback()
}