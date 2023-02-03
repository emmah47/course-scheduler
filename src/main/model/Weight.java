package model;

public class Weight {
    private int compactWeight;
    private int balanceWeight;
    private int preferredStartTime;
    private int preferredEndTime;


    public Weight(int compactWeight, int balanceWeight, int preferredStartTime, int preferredEndTime) {
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
