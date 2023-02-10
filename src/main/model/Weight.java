package model;

import model.util.HelperUtil;

// A set of weights decided by the user. It is used for calculating the score of each schedule to decide which schedules
// are better. A category with a higher weight will impact the final score more. Also contains the preferred start and
// end times. The scores are scaled by the ratio of their weights. For example, if compactWeight == 2 and balanceWeight
// == 1, then the score for the compactness of the schedule will be considered twice as important as the score for the
// balance.
public class Weight {
    // WEIGHTS
    private int compactWeight; // weight for scaling compact score
    private int balanceWeight; // weight for scaling balance score
    //DATA
    private int preferredStartTime; // the preferred start time in minutes
    private int preferredEndTime;   // the preferred end time in minutes

    // REQUIRES: preferredStartTime and preferredEndTime must be in the form of "hrs:mins" (ex "12:30")
    // EFFECTS: constructs a Weight that has a compact weight, a balance weight, and takes in Strings for the start
    //          and end times and will convert it to minutes
    public Weight(int compactWeight, int balanceWeight,
                  String preferredStartTime, String preferredEndTime) {
        this.compactWeight = compactWeight;
        this.balanceWeight = balanceWeight;
        this.preferredStartTime = HelperUtil.calculateMinutes(preferredStartTime);
        this.preferredEndTime = HelperUtil.calculateMinutes(preferredEndTime);
    }

    // EFFECTS: constructs a Weight that has a compact weight, a balance weight, takes in integers (ex. 600)
    //          for the times
    public Weight(int compactWeight, int balanceWeight,
                  int preferredStartTime, int preferredEndTime) {
        this.compactWeight = compactWeight;
        this.balanceWeight = balanceWeight;
        this.preferredStartTime = preferredStartTime;
        this.preferredEndTime = preferredEndTime;
    }

    public int getCompactWeight() {
        return compactWeight;
    }

    public void setCompactWeight(int compactWeight) {
        this.compactWeight = compactWeight;
    }

    public int getBalanceWeight() {
        return balanceWeight;
    }

    public void setBalanceWeight(int balanceWeight) {
        this.balanceWeight = balanceWeight;
    }

    public int getPreferredStartTime() {
        return preferredStartTime;
    }

    public void setPreferredStartTime(int preferredStartTime) {
        this.preferredStartTime = preferredStartTime;
    }

    public int getPreferredEndTime() {
        return preferredEndTime;
    }

    public void setPreferredEndTime(int preferredEndTime) {
        this.preferredEndTime = preferredEndTime;
    }


}
