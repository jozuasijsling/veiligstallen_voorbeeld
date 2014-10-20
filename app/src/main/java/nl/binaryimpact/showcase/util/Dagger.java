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

package nl.binaryimpact.showcase.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;

import dagger.ObjectGraph;
import nl.binaryimpact.showcase.ShowcaseApplication;

/**
 * Provides simplified access to the application's object graph.
 */
public class Dagger {

    /**
     * Convenience method for injecting dependencies on an activity.
     *
     * @param activity The activity to inject.
     */
    public static void inject(Activity activity) {
        og(activity).inject(activity);
    }

    /**
     * Convenience method for injecting dependencies on an fragment.
     *
     * @param fragment The fragment to inject.
     */
    public static void inject(Fragment fragment) {
        og(fragment).inject(fragment);
    }

    /**
     * Convenience method for injecting dependencies on a service.
     *
     * @param service The service to inject.
     */
    public static void inject(Service service) {
        og(service).inject(service);
    }

    /**
     * Convenience method for injecting dependencies on a context.
     *
     * @param context The context to inject.
     */
    public static void inject(Context context) {
        og(context).inject(context);
    }

    /**
     * Convenience method for fetching the object graph from an activity.
     *
     * @param activity The activity from which the object graph is requested.
     * @return The application's object graph.
     */
    public static ObjectGraph og(Activity activity) {
        return ((ShowcaseApplication) activity.getApplication()).getObjectGraph();
    }

    /**
     * Convenience method for fetching the object graph from a fragment.
     *
     * @param fragment The fragment from which the object graph is requested.
     * @return The application's object graph.
     */
    public static ObjectGraph og(Fragment fragment) {
        return og(fragment.getActivity());
    }

    /**
     * Convenience method for fetching the object graph from a service.
     *
     * @param service The service from which the object graph is requested.
     * @return The application's object graph.
     */
    public static ObjectGraph og(Service service) {
        return ((ShowcaseApplication) service.getApplication()).getObjectGraph();
    }

    /**
     * Convenience method for fetching the object graph from a context.
     *
     * @param context The context from which the object graph is requested.
     * @return The application's object graph.
     */
    public static ObjectGraph og(Context context) {
        return ((ShowcaseApplication) context.getApplicationContext()).getObjectGraph();
    }
}
