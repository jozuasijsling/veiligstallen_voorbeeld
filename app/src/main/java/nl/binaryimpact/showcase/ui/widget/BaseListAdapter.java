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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List adapter with generics and better view decoration abstraction.
 * <p>
 * <p/>
 * </p>
 *
 * @param <T> The class held by the list.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    private final ArrayList<T> mItems = new ArrayList<T>();

    /**
     * Replaces the list by a new set of items and calls {@link #notifyDataSetChanged()}.
     *
     * @param items The new set of items to replace the old with.
     */
    public void swapItems(@Nullable List<T> items) {
        mItems.clear();
        if (items != null) {
            mItems.addAll(items);
        }
        mItems.trimToSize();
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mItems.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's data set.
     * @return The data at the specified position.
     */
    @NonNull
    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Constructs and decorates views to ready them for display.
     * Calls {@link #newView(int, android.view.ViewGroup)} if the view needs to be created,
     * then calls {@link #bindView(android.view.View, int)} to decorate the current view.
     *
     * @param position    Position of the current item.
     * @param convertView View that was created for the current item view type.
     * @param parent      Contains layout parameters that should be applied to the current view.
     * @return A view that is ready to display the contents of the current item.
     * @see #getItemViewType(int)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(position, parent);
        }
        bindView(convertView, position);
        return convertView;
    }

    /**
     * Allows implementing classes to iterate the contents of the adapter.
     *
     * @return an iterator for the item list
     */
    protected Iterator<T> iterator() {
        return mItems.iterator();
    }

    /**
     * Called if a view for the current view type has not yet been created. Implementing classes
     * should create or inflate a view for the current view type.
     * <p>
     * It is advisable to apply the view holder pattern to the generated view. This removes
     * unnecessary child view lookup calls and optimizes access to the view hierarchy.
     * </p>
     *
     * @param position Position of the item for which to create a new view.
     * @param parent   Contains layout parameters that should be applied to the new view.
     * @return The newly created view.
     * @see #getItemViewType(int)
     */
    protected abstract View newView(int position, ViewGroup parent);

    /**
     * Called if a view needs to be decorated before display. Implementing classes should decorate
     * the view and its hierarchy based on the current item data, view type and/or its position.
     *
     * @param view     View to decorate.
     * @param position Position of the item for which to decorate the view.
     * @see #getItem(int)
     * @see #getItemViewType(int)
     */
    protected abstract void bindView(View view, int position);
}
