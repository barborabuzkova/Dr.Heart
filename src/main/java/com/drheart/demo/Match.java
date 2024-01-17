package com.drheart.demo;

public class Match implements Comparable {
    private final Profile otherProfile;

    private final Double matchPercentage;

    /**
     * @param otherProfile profile to which the owning profile is matched
     * @param matchPercentage percentage of match (between 0 and 1)
     */
    public Match(Profile otherProfile, Double matchPercentage) {
        this.otherProfile = otherProfile;
        this.matchPercentage = matchPercentage;
    }

    /**
     * @return matchPercentage (% match between 0 and 1)
     */
    public Double getMatchPercentage() {
        return matchPercentage;
    }

    /**
     * @return other profile to which owning profile is matched
     */
    public Profile getOtherProfile() {
        return otherProfile;
    }

    /**
     * @param o the object to be compared.
     * @return used to provide a sorting in descending order
     */
    @Override
    public int compareTo(Object o) {
        return - matchPercentage.compareTo(((Match) o).getMatchPercentage());
    }
}
