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

package nl.binaryimpact.showcase.model.google;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import nl.binaryimpact.showcase.util.annotation.UsedByDeserializers;

/**
 * Generic representation of a Google API response. The root of Google responses are typically
 * a status field and a list of elements with the data requested.
 */
public abstract class GoogleResponse<T> {

    /**
     * Annotated methods or parameters are restricted by Android Studio to allow only valid status codes.
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.PARAMETER})
    @StringDef({STATUS_OK, STATUS_ZERO_RESULTS, STATUS_OVER_QUERY_LIMIT, STATUS_REQUEST_DENIED, STATUS_INVALID_REQUEST})
    public @interface StatusCode {
    }

    /**
     * Indicates that no errors occurred and at least one result was returned.
     */
    public static final String STATUS_OK = "OK";

    /**
     * Indicates that the search was successful but returned no results. This may occur if the
     * search was passed a bounds in a remote location.
     */
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS";

    /**
     * Indicates that you are over your quota.
     */
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";

    /**
     * Indicates that your request was denied, generally because of lack of an invalid key parameter.
     */
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED";

    /**
     * Generally indicates that a required input parameter is missing.
     */
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST";

    private List<T> list;
    private String status;

    @UsedByDeserializers
    public GoogleResponse() {
    }

    /**
     * Constructs a new <code>GoogleResponse</code> with specified data and status code.
     *
     * @param list   contains elements with the data requested from Google
     * @param status one of following status codes: {@link #STATUS_OK OK},
     *               {@link #STATUS_INVALID_REQUEST INVALID_REQUEST},
     *               {@link #STATUS_OVER_QUERY_LIMIT OVER_QUERY_LIMIT},
     *               {@link #STATUS_REQUEST_DENIED REQUEST_DENIED},
     *               {@link #STATUS_ZERO_RESULTS ZERO_RESULTS}
     */
    public GoogleResponse(List<T> list, @NonNull @StatusCode String status) {
        this.list = list;
        this.status = status;
    }

    /**
     * List of elements containing requested data.
     *
     * @return generic list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Sets the list of elements containing requested data.
     *
     * @param list generic list of elements containing request requested data.
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Returns a one of the following status codes: {@link #STATUS_OK OK},
     * {@link #STATUS_INVALID_REQUEST INVALID_REQUEST},
     * {@link #STATUS_OVER_QUERY_LIMIT OVER_QUERY_LIMIT},
     * {@link #STATUS_REQUEST_DENIED REQUEST_DENIED},
     * {@link #STATUS_ZERO_RESULTS ZERO_RESULTS}
     *
     * @return status
     */
    @NonNull
    @StatusCode
    public String getStatus() {
        return status;
    }

    /**
     * Sets a status code, must be one of: {@link #STATUS_OK OK},
     * {@link #STATUS_INVALID_REQUEST INVALID_REQUEST},
     * {@link #STATUS_OVER_QUERY_LIMIT OVER_QUERY_LIMIT},
     * {@link #STATUS_REQUEST_DENIED REQUEST_DENIED},
     * {@link #STATUS_ZERO_RESULTS ZERO_RESULTS}
     *
     * @param status the code to set
     */
    public void setStatus(@NonNull @StatusCode String status) {
        this.status = status;
    }

    /**
     * Tests if the status code is {@link #STATUS_OK OK}.
     *
     * @return true only if <code>status</code> is OK
     */
    public boolean isStatusOk() {
        return STATUS_OK.equals(status);
    }
}
