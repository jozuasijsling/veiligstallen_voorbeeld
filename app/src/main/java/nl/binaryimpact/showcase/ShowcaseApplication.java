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
import nl.binaryimpact.showcase.model.veiligstallen.OpeningHours;
import nl.binaryimpact.showcase.util.xml.OpeningsHoursConverter;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

public class ShowcaseApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    // enable cupboard annotations and register custom converters
    CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations()
        .registerFieldConverter(OpeningHours.class, new OpeningsHoursConverter())
        .build());
  }
}
