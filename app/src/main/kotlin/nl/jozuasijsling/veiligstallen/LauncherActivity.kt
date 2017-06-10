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
import android.os.Bundle
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleCallbacks
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleSource
import nl.jozuasijsling.veiligstallen.view.window.WindowModelStack
import java.util.*


class LauncherActivity() : Activity(), LifecycleSource {

    private val replayStack = ArrayDeque<() -> Unit>(3)

    override var listener: LifecycleCallbacks? = null
        set(value) {
            field = value
            while (!replayStack.isEmpty()) {
                replayStack.removeLast().invoke()
            }
        }

    val callbacks: LifecycleCallbacks = object : LifecycleCallbacks {

        override fun onCreated(bundle: Bundle?) {
            notifyOrPushToStack { it.onCreated(bundle) }
        }

        override fun onStarted() {
            notifyOrPushToStack { it.onStarted() }
        }

        override fun onResumed() {
            notifyOrPushToStack { it.onResumed() }
        }

        override fun onPaused() {
            notifyAndReduceStack(2) { it.onPaused() }
        }

        override fun onStopped() {
            notifyAndReduceStack(1) { it.onPaused() }
        }

        override fun onDestroyed() {
            notifyAndReduceStack(0) { it.onPaused() }
        }

        override fun onSaveInstanceState(bundle: Bundle?) {
            listener?.onSaveInstanceState(bundle)
        }

        override fun onLowMemory() {
            listener?.onLowMemory()
        }

        private fun notifyOrPushToStack(body: (LifecycleCallbacks) -> Unit) {
            if (listener == null) {
                replayStack.push { body(listener!!) }
            } else {
                body(listener!!)
            }
        }

        private fun notifyAndReduceStack(level: Int, body: (LifecycleCallbacks) -> Unit) {
            while (replayStack.size > level) {
                replayStack.pop()
            }

            if (listener != null) {
                body(listener!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowModelStack.bind(this)
        callbacks.onCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        callbacks.onStarted()
    }

    override fun onResume() {
        super.onResume()
        callbacks.onResumed()
    }

    override fun onPause() {
        super.onPause()
        callbacks.onPaused()
    }

    override fun onStop() {
        super.onStop()
        callbacks.onStopped()
    }

    override fun onDestroy() {
        super.onDestroy()
        callbacks.onDestroyed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        callbacks.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        callbacks.onLowMemory()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
