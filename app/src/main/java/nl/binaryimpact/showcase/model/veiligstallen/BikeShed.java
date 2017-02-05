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

package nl.binaryimpact.showcase.model.veiligstallen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import nl.binaryimpact.showcase.util.annotation.LocalGeneratedId;

@Root(name = "Fietsenstalling")
public class BikeShed {

    @LocalGeneratedId private Long _id;

    @Element(name = "Naam") private String name;
    @Element(name = "Omschrijving", required = false) private String description;
    @Element(name = "ID") private String externalId;
    @Element(name = "Gemeente") private String municipality;
    @Element(name = "Straat") private String street;
    @Element(name = "Postcode") private String postcode;
    @Element(name = "Plaats") private String city;
    @Element(name = "Url") private String url;
    @Element(name = "CapaciteitFiets") private int bikeCapacity;
    @Element(name = "Verwijssysteem") private int referral;
    @Element(name = "CapaciteitTotaal") private int capacityTotal;
    @ElementList(name = "Secties", required = false) private List<Section> sections;
    @Element(name = "Verwijssysteem", required = false) private String referralText;
    @Element(name = "OpeningsTijden") private OpeningHours openingHours;
    @Element(name = "Stationsstalling") private boolean stationShed;
    @Element(name = "Voorzieningen") private String[] facilities;
    @Element(name = "Type") private String type;
    @Element(name = "Coordinaten") private double[] coordinates;
    @Element(name = "Toegangscontrole", required = false) private String access;
    @Element(name = "Beheerder") private String administrator;
    @Element(name = "BeheerderContact") private String administratorContact;

    public BikeShed() {
    }

    public BikeShed(@NonNull String name, @Nullable String description, @NonNull String municipality,
                    @NonNull String street, @NonNull String postcode, @NonNull String city, @NonNull String url,
                    int bikeCapacity, int referral, int capacityTotal,
                    @NonNull List<Section> sections, @NonNull String referralText,
                    @NonNull OpeningHours openingHours,
                    boolean stationShed, @NonNull String[] facilities, @NonNull String type,
                    @NonNull double[] coordinates, @NonNull String access,
                    @NonNull String administrator, @NonNull String administratorContact) {
        this.name = name;
        this.description = description;
        this.municipality = municipality;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.url = url;
        this.bikeCapacity = bikeCapacity;
        this.referral = referral;
        this.capacityTotal = capacityTotal;
        this.sections = sections;
        this.referralText = referralText;
        this.openingHours = openingHours;
        this.stationShed = stationShed;
        this.facilities = facilities;
        this.type = type;
        this.coordinates = coordinates;
        this.access = access;
        this.administrator = administrator;
        this.administratorContact = administratorContact;
    }

    public Long getLocalId() {
        return _id;
    }

    @NonNull public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @NonNull public String getExternalId() {
        return externalId;
    }

    public void setId(@NonNull String id) {
        this.externalId = id;
    }

    @NonNull public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(@NonNull String municipality) {
        this.municipality = municipality;
    }

    @NonNull public String getStreet() {
        return street;
    }

    public void setStreet(@NonNull String street) {
        this.street = street;
    }

    @NonNull public String getPostcode() {
        return postcode;
    }

    public void setPostcode(@NonNull String postcode) {
        this.postcode = postcode;
    }

    @NonNull public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public int getBikeCapacity() {
        return bikeCapacity;
    }

    public void setBikeCapacity(int bikeCapacity) {
        this.bikeCapacity = bikeCapacity;
    }

    public int getReferral() {
        return referral;
    }

    public void setReferral(int referral) {
        this.referral = referral;
    }

    public int getCapacityTotal() {
        return capacityTotal;
    }

    public void setCapacityTotal(int capacityTotal) {
        this.capacityTotal = capacityTotal;
    }

    @NonNull public List<Section> getSections() {
        return sections;
    }

    public void setSections(@NonNull List<Section> sections) {
        this.sections = sections;
    }

    @NonNull public String getReferralText() {
        return referralText;
    }

    public void setReferralText(@NonNull String referralText) {
        this.referralText = referralText;
    }

    @NonNull public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(@NonNull OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public boolean isStationShed() {
        return stationShed;
    }

    public void setStationShed(boolean stationShed) {
        this.stationShed = stationShed;
    }

    @NonNull public String[] getFacilities() {
        return facilities;
    }

    public void setFacilities(@NonNull String[] facilities) {
        this.facilities = facilities;
    }

    @NonNull public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@NonNull double[] coordinates) {
        this.coordinates = coordinates;
    }

    @NonNull public String getAccess() {
        return access;
    }

    public void setAccess(@NonNull String access) {
        this.access = access;
    }

    @NonNull public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(@NonNull String administrator) {
        this.administrator = administrator;
    }

    @NonNull public String getAdministratorContact() {
        return administratorContact;
    }

    public void setAdministratorContact(@NonNull String administratorContact) {
        this.administratorContact = administratorContact;
    }

    public static class Builder {
        private String mName;
        private String mMunicipality;
        private String mStreet;
        private String mPostcode;
        private String mCity;
        private String mUrl;
        private int mBikeCapacity;
        private int mReferral;
        private int mCapacityTotal;
        private List<Section> mSections;
        private String mReferralText;
        private OpeningHours mOpeningHours;
        private boolean mStationShed;
        private String[] mFacilities;
        private String mType;
        private double[] mCoordinates;
        private String mAccess;
        private String mAdministrator;
        private String mAdministratorContact;
        private String mDescription;

        @NonNull public Builder name(@NonNull String name) {
            mName = name;
            return this;
        }

        @NonNull public Builder description(@Nullable String description) {
            mDescription = description;
            return this;
        }

        @NonNull public Builder municipality(@NonNull String municipality) {
            mMunicipality = municipality;
            return this;
        }

        @NonNull public Builder street(@NonNull String street) {
            mStreet = street;
            return this;
        }

        @NonNull public Builder postcode(@NonNull String postcode) {
            mPostcode = postcode;
            return this;
        }

        @NonNull public Builder city(@NonNull String city) {
            mCity = city;
            return this;
        }

        @NonNull public Builder url(@NonNull String url) {
            mUrl = url;
            return this;
        }

        @NonNull public Builder bikeCapacity(int bikeCapacity) {
            mBikeCapacity = bikeCapacity;
            return this;
        }

        @NonNull public Builder referral(int referral) {
            mReferral = referral;
            return this;
        }

        @NonNull public Builder capacityTotal(int capacityTotal) {
            mCapacityTotal = capacityTotal;
            return this;
        }

        @NonNull public Builder sections(@NonNull List<Section> sections) {
            mSections = sections;
            return this;
        }

        @NonNull public Builder referralText(@NonNull String referralText) {
            mReferralText = referralText;
            return this;
        }

        @NonNull public Builder openingHours(@NonNull OpeningHours openingHours) {
            mOpeningHours = openingHours;
            return this;
        }

        @NonNull public Builder stationShed(boolean stationShed) {
            mStationShed = stationShed;
            return this;
        }

        @NonNull public Builder facilities(@NonNull String[] facilities) {
            mFacilities = facilities;
            return this;
        }

        @NonNull public Builder type(@NonNull String type) {
            mType = type;
            return this;
        }

        @NonNull public Builder coordinates(@NonNull double[] coordinates) {
            mCoordinates = coordinates;
            return this;
        }

        @NonNull public Builder access(@NonNull String access) {
            mAccess = access;
            return this;
        }

        @NonNull public Builder administrator(@NonNull String administrator) {
            mAdministrator = administrator;
            return this;
        }

        @NonNull public Builder administratorContact(@NonNull String administratorContact) {
            mAdministratorContact = administratorContact;
            return this;
        }

        @NonNull public BikeShed build() {
            return new BikeShed(mName, mDescription, mMunicipality, mStreet, mPostcode, mCity, mUrl, mBikeCapacity, mReferral, mCapacityTotal, mSections, mReferralText, mOpeningHours, mStationShed, mFacilities, mType, mCoordinates, mAccess, mAdministrator, mAdministratorContact);
        }
    }
}
