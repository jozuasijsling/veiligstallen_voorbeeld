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

package nl.binaryimpact.showcase.log;

import android.util.Log;

import timber.log.Timber;

/**
 * Logging profile for staging builds.
 */
public class StagingProfileTree extends Timber.HollowTree {

    @Override
    public void i(String message, Object... args) {
//        Crashlytics.log(Log.INFO, "Staging", formatString(message, args));
        Log.i("Staging", message);
    }

    @Override
    public void w(String message, Object... args) {
//        Crashlytics.log(Log.WARN, "Staging", formatString(message, args));
        Log.w("Staging", message);
    }

    @Override
    public void e(String message, Object... args) {
//        Crashlytics.log(Log.ERROR, "Staging", formatString(message, args));
        Log.e("Staging", message);
    }

    public static String formatString(String message, Object... args) {
        // If no varargs are supplied, treat it as a request to log the string without formatting.
        return args.length == 0 ? message : String.format(message, args);
    }
}
