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

package nl.binaryimpact.showcase.webservice;

import nl.binaryimpact.showcase.model.google.GoogleResponse;
import nl.binaryimpact.showcase.model.google.places.autocomplete.Prediction;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Defines the interfacing with Google's Places API.
 */
public interface GooglePlacesService {

    public static final String URL = "https://maps.googleapis.com/maps/api/place/";

    @GET("autocomplete/json?key={key}&input={input}")
    public GoogleResponse<Prediction> autocomplete(@Path("key") String key,
                                                   @Path("input") String input);

    @GET("autocomplete/json?key={key}&input={input}&language={language}")
    public GoogleResponse<Prediction> autocomplete(@Path("key") String key,
                                                   @Path("input") String input,
                                                   @Path("language") String language);


}
