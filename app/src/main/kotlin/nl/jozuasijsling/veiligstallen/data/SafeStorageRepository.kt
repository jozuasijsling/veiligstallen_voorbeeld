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

package nl.jozuasijsling.veiligstallen.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import nl.jozuasijsling.veiligstallen.data.domain.SafeStorage
import nl.jozuasijsling.veiligstallen.service.ServiceModule

object SafeStorageRepository {

    val subject = BehaviorSubject.create<SafeStorage>()!!
    val errorSubject = PublishSubject.create<Throwable>()!!
    val service = ServiceModule().provideDataService()

    fun getStorage(): Observable<SafeStorage> {
        if (!subject.hasValue()) {
            downloadBikeSheds()
        }
        return subject.hide().observeOn(AndroidSchedulers.mainThread())
    }

    fun observeErrors(): Observable<Throwable> {
        return errorSubject.hide().observeOn(AndroidSchedulers.mainThread())
    }

    private fun downloadBikeSheds() {
        service.safeStorage
                .subscribeOn(Schedulers.io())
                .singleOrError()
                .map { dto -> dto.toDomainObject() }
                .doOnError { Log.w("Download", "Failed because", it) }
                .doOnSuccess { Log.i("Download", "${it.bikeSheds.size} elements downloaded.") }
                .subscribe(subject::onNext, errorSubject::onNext)
    }
}