package com.drheart.demo;

public class Match implements Comparable {
    private final Profile profile;

    private final Double matchPercentage;

    public Match(Profile profile, Double matchPercentage) {
        this.profile = profile;
        this.matchPercentage = matchPercentage;
    }

    public Double getMatchPercentage() {
        return matchPercentage;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public int compareTo(Object o) {
        return - matchPercentage.compareTo(((Match) o).getMatchPercentage());
    }
}
