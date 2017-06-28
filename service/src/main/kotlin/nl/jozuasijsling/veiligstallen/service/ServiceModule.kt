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

package nl.jozuasijsling.veiligstallen.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import nl.jozuasijsling.veiligstallen.service.SafeStorageService.Companion.SERVICE_URL
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Provides API utilities and retrofit-backed service implementations.
 */
class ServiceModule {

    fun provideDataService(): SafeStorageService {
        val simpleXml = SimpleXmlConverterFactory.create() // createNonStrict();
        val retrofit = Retrofit.Builder().baseUrl(SERVICE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(simpleXml)
                .build()
        return retrofit.create(SafeStorageService::class.java)
    }
}
