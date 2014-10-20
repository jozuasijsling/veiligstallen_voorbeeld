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

package nl.binaryimpact.showcase;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import nl.binaryimpact.showcase.log.DevelopmentProfileTree;
import nl.binaryimpact.showcase.log.StagingProfileTree;
import nl.binaryimpact.showcase.model.veiligstallen.OpeningHours;
import nl.binaryimpact.showcase.util.module.AndroidModule;
import nl.binaryimpact.showcase.util.module.ApiModule;
import nl.binaryimpact.showcase.util.module.EventBusModule;
import nl.binaryimpact.showcase.util.xml.OpeningsHoursConverter;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;
import timber.log.Timber;

/**
 * Created by jozua on 2014/10/10.
 */
public class ShowcaseApplication extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules().toArray());

        if (BuildConfig.DEBUG) {
            Timber.plant(new DevelopmentProfileTree());
        } else {
            Timber.plant(new StagingProfileTree());
        }

        // enable cupboard annotations and register custom converters
        CupboardFactory.setCupboard(new CupboardBuilder()
                .useAnnotations()
                .registerFieldConverter(OpeningHours.class, new OpeningsHoursConverter())
                .build());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new ApiModule(),
                new EventBusModule()
        );
    }

    /**
     * Gets the application's global object graph.
     *
     * @return The global object graph.
     */
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}
