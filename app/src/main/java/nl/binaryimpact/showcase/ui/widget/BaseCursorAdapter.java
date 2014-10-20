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

package nl.binaryimpact.showcase.ui.widget;

import android.content.Context;
import android.widget.CursorAdapter;

/**
 * Simplifies the construction of a cursor adapter for our purpose.
 * <p>
 * A cursor adapter can be initialized with a valid cursor, but we never
 * do that and always pass {@code null} instead. Also, a boolean can be used
 * to indicate whether the cursor should re-query the database when changes
 * are detected, but this executed on the UI thread and has been deprecated.
 * </p>
 */
public abstract class BaseCursorAdapter extends CursorAdapter {

    /**
     * Constructs a simplified {@link android.widget.CursorAdapter} using the given context.
     *
     * @param context the context
     */
    public BaseCursorAdapter(Context context) {
        super(context, null, false);
    }
}
