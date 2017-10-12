package ca.bcit.ass2.chu_navarro;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

/**
 * Created by E on 2017-10-08.
 */

public class Country implements Parcelable {

    private String continent;
    private String country;
    private String capital;
    private String region;
    private String population;
    private String area;
    private String borders;
    private String flag;

    protected Country(Parcel in) {
        continent = in.readString();
        country = in.readString();
        capital = in.readString();
        region = in.readString();
        population = in.readString();
        area = in.readString();
        flag = in.readString();
        borders = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public Country() {

    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinent() {
        return this.continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return this.region;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPopulation() {
        return this.population;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return this.area;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getBorders() {
        return this.borders;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(continent);
        parcel.writeString(country);
        parcel.writeString(capital);
        parcel.writeString(region);
        parcel.writeString(population);
        parcel.writeString(area);
        parcel.writeString(flag);
        parcel.writeString(borders);
    }
}
