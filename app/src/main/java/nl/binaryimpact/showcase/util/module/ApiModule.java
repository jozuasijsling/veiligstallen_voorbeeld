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
import nl.binaryimpact.showcase.json.GeneratedIdExclusionStrategy;
import nl.binaryimpact.showcase.json.GsonIgnoreExclusionStrategy;
import nl.binaryimpact.showcase.webservice.VeiligStallenDataService;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Provides API utilities and retrofit-backed service implementations.
 */
public class ApiModule {

  public Gson provideJsonParser() {
    return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
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

  public VeiligStallenDataService provideDataService() {
    SimpleXmlConverterFactory simpleXml = SimpleXmlConverterFactory.create(); // createNonStrict();
    Retrofit retrofit = new Retrofit.Builder().baseUrl(VeiligStallenDataService.URL)
        .addConverterFactory(simpleXml)
        .build();
    return retrofit.create(VeiligStallenDataService.class);
  }
}
