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

package nl.jozuasijsling.veiligstallen.view.adapters

import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableField
import io.reactivex.Observable


/**
 * @see <a href="https://github.com/TangoAgency/android-data-binding-rxjava/blob/master/app/src/main/java/agency/tango/databindingrxjava/RxUtils.java">GitHub | android-data-binding-rxjava</a>
 */

// Note: Stream may ( will ! ) contain `null` values which will not be allowed in RxJava 2
fun <T> ObservableField<T>.asRxObservable(): Observable<ObservableField<T>> {
    val observableField = this
    return Observable.create<ObservableField<T>> { emitter ->
        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(dataBindingObservable: android.databinding.Observable, propertyId: Int) {
                if (dataBindingObservable === observableField) {
                    emitter.onNext(observableField)
                }
            }
        }

        observableField.addOnPropertyChangedCallback(callback)

        emitter.setCancellable { observableField.removeOnPropertyChangedCallback(callback) }
    }
}