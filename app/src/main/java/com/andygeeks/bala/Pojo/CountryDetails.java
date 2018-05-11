package com.andygeeks.bala.Pojo;

import java.io.Serializable;

public class CountryDetails implements Serializable {

    private String name;

    private String region;

    private String capital;

    private String subregion;

    private String flag;

    private String area;

    private String[] borders;

    private String population;

    private int numericCode;

    public int getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(int numericCode) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public int compareTo(CountryDetails countryDetails) {

        int compareQuantity = countryDetails.getBorders().length;

        //ascending order
        //return this.borders.length - compareQuantity;

        //descending order
        return this.borders.length - compareQuantity;

    }

}
