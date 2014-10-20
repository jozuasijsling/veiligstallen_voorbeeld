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

package nl.binaryimpact.showcase.util.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.binaryimpact.showcase.json.GeneratedIdExclusionStrategy;
import nl.binaryimpact.showcase.json.GsonIgnoreExclusionStrategy;
import nl.binaryimpact.showcase.util.xml.SimpleXmlConverter;
import nl.binaryimpact.showcase.util.annotation.Autocomplete;
import nl.binaryimpact.showcase.util.annotation.VeiligStallen;
import nl.binaryimpact.showcase.webservice.GooglePlacesService;
import nl.binaryimpact.showcase.webservice.VeiligStallenDataService;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Provides API utilities and retrofit-backed service implementations.
 */
@Module(injects = {Gson.class}, library = true)
public class ApiModule {

    @Provides
    @Singleton
    public Gson provideJsonParser() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setExclusionStrategies(new GsonIgnoreExclusionStrategy(),
                        new GeneratedIdExclusionStrategy())
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                        // TODO optimization: replace GSON's DateTypeAdapter with custom adapter
                        // Gson standard DateTypeAdapter is very sub-optimal. It (1) uses SimpleDateFormat
                        // which needs to be synchronized because it is not thread safe. And (2) it handles
                        // error cases very poorly, repeatedly trying two date formats before ending up with
                        // the usual ISO8601. We can optimize issue (1) by wrapping a the DateFormat in a
                        // ThreadLocal subclass and we can optimize issue (2) by specifying one date format
                        // rather than defaulting to three different date format patterns.
                .create();
    }

    @Provides
    @Singleton
    @VeiligStallen
    public RestAdapter provideRestAdapter() {
        Endpoint endpoint = Endpoints.newFixedEndpoint(VeiligStallenDataService.URL);
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setEndpoint(endpoint)
                .setConverter(new SimpleXmlConverter())
                .build();
    }

    @Provides
    @Singleton
    @Autocomplete
    public RestAdapter provideRestAdapter(Gson gson) {
        Endpoint endpoint = Endpoints.newFixedEndpoint("https://maps.googleapis.com/maps/api/place/");
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides
    @Singleton
    public GooglePlacesService provideGooglePlacesService(RestAdapter restAdapter) {
        return restAdapter.create(GooglePlacesService.class);
    }

}
