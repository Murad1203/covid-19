package com.covid.covid19.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationStats {

    private int id;
    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPrevDay;



}
