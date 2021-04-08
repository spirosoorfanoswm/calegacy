package eu.ark.creditark.services.creditarkservices.shared;

import java.util.Date;
import java.util.Objects;

public class ScenarioParams {

    private int scenarioId;
    private int contextId;
    private Date snapshotDate;
    private String owner;
    private String desc;
    private double wacc;
    private double raroc;
    private double creditAmount;
    private int significantDigits;
    private double minLimit;
    private String worstAcceptableScore;
    private double minLimitGrowth;
    private double minLimitReduction;
    private double maxLimitGrowth;
    private double maxLimitReduction;
    private double minAcceptedLimitUse;
    private int maxDso;
    private double minAcceptedRwMargin;
    private double salesChange;
    private double marginChange;
    private double pdChange;
    private double minLimitPct;

    private ScenarioParams(int scenarioId, int contextId,
                           Date snapshotDate, String owner,
                           String desc, double wacc, double raroc,
                           double creditAmount, int significantDigits,
                           double minLimit, String worstAcceptableScore,
                           double minLimitGrowth, double minLimitReduction,
                           double maxLimitGrowth, double maxLimitReduction,
                           double minAcceptedLimitUse, int maxDso,
                           double minAcceptedRwMargin, double salesChange,
                           double marginChange, double pdChange,
                           double minLimitPct) {
        this.scenarioId = scenarioId;
        this.contextId = contextId;
        this.snapshotDate = snapshotDate;
        this.owner = owner;
        this.desc = desc;
        this.wacc = wacc;
        this.raroc = raroc;
        this.creditAmount = creditAmount;
        this.significantDigits = significantDigits;
        this.minLimit = minLimit;
        this.worstAcceptableScore = worstAcceptableScore;
        this.minLimitGrowth = minLimitGrowth;
        this.minLimitReduction = minLimitReduction;
        this.maxLimitGrowth = maxLimitGrowth;
        this.maxLimitReduction = maxLimitReduction;
        this.minAcceptedLimitUse = minAcceptedLimitUse;
        this.maxDso = maxDso;
        this.minAcceptedRwMargin = minAcceptedRwMargin;
        this.salesChange = salesChange;
        this.marginChange = marginChange;
        this.pdChange = pdChange;
        this.minLimitPct = minLimitPct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScenarioParams that = (ScenarioParams) o;
        return scenarioId == that.scenarioId &&
                contextId == that.contextId &&
                Double.compare(that.wacc, wacc) == 0 &&
                Double.compare(that.raroc, raroc) == 0 &&
                Double.compare(that.creditAmount, creditAmount) == 0 &&
                significantDigits == that.significantDigits &&
                Double.compare(that.minLimit, minLimit) == 0 &&
                Double.compare(that.minLimitGrowth, minLimitGrowth) == 0 &&
                Double.compare(that.minLimitReduction, minLimitReduction) == 0 &&
                Double.compare(that.maxLimitGrowth, maxLimitGrowth) == 0 &&
                Double.compare(that.maxLimitReduction, maxLimitReduction) == 0 &&
                Double.compare(that.minAcceptedLimitUse, minAcceptedLimitUse) == 0 &&
                maxDso == that.maxDso &&
                Double.compare(that.minAcceptedRwMargin, minAcceptedRwMargin) == 0 &&
                Double.compare(that.salesChange, salesChange) == 0 &&
                Double.compare(that.marginChange, marginChange) == 0 &&
                Double.compare(that.pdChange, pdChange) == 0 &&
                Double.compare(that.minLimitPct, minLimitPct) == 0 &&
                snapshotDate.equals(that.snapshotDate) &&
                owner.equals(that.owner) &&
                desc.equals(that.desc) &&
                worstAcceptableScore.equals(that.worstAcceptableScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenarioId, contextId, snapshotDate, owner, desc, wacc, raroc, creditAmount, significantDigits, minLimit, worstAcceptableScore, minLimitGrowth, minLimitReduction, maxLimitGrowth, maxLimitReduction, minAcceptedLimitUse, maxDso, minAcceptedRwMargin, salesChange, marginChange, pdChange, minLimitPct);
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public int getContextId() {
        return contextId;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public String getOwner() {
        return owner;
    }

    public String getDesc() {
        return desc;
    }

    public double getWacc() {
        return wacc;
    }

    public double getRaroc() {
        return raroc;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public int getSignificantDigits() {
        return significantDigits;
    }

    public double getMinLimit() {
        return minLimit;
    }

    public String getWorstAcceptableScore() {
        return worstAcceptableScore;
    }

    public double getMinLimitGrowth() {
        return minLimitGrowth;
    }

    public double getMinLimitReduction() {
        return minLimitReduction;
    }

    public double getMaxLimitGrowth() {
        return maxLimitGrowth;
    }

    public double getMaxLimitReduction() {
        return maxLimitReduction;
    }

    public double getMinAcceptedLimitUse() {
        return minAcceptedLimitUse;
    }

    public int getMaxDso() {
        return maxDso;
    }

    public double getMinAcceptedRwMargin() {
        return minAcceptedRwMargin;
    }

    public double getSalesChange() {
        return salesChange;
    }

    public double getMarginChange() {
        return marginChange;
    }

    public double getPdChange() {
        return pdChange;
    }

    public double getMinLimitPct() {
        return minLimitPct;
    }




    public static class Builder {
        private int scenarioId;
        private int contextId;
        private Date snapshotDate;
        private String owner;
        private String desc;
        private double wacc;
        private double raroc;
        private double creditAmount;
        private int significantDigits;
        private double minLimit;
        private String worstAcceptableScore;
        private double minLimitGrowth;
        private double minLimitReduction;
        private double maxLimitGrowth;
        private double maxLimitReduction;
        private double minAcceptedLimitUse;
        private int maxDso;
        private double minAcceptedRwMargin;
        private double salesChange;
        private double marginChange;
        private double pdChange;
        private double minLimitPct;

        public ScenarioParams build() {
            return new ScenarioParams(this.scenarioId,
                    this.contextId,
                    this.snapshotDate,
                    this.owner,
                    this.desc,
                    this.wacc,
                    this.raroc,
                    this.creditAmount,
                    this.significantDigits,
                    this.minLimit,
                    this.worstAcceptableScore,
                    this.minLimitGrowth,
                    this.minLimitReduction,
                    this.maxLimitGrowth,
                    this.maxLimitReduction,
                    this.minAcceptedLimitUse,
                    this.maxDso,
                    this.minAcceptedRwMargin,
                    this.salesChange,
                    this.marginChange,
                    this.pdChange,
                    this.minLimitPct);
        }

        public Builder withScenarioId(int scenarioId) {
            this.scenarioId = scenarioId;
            return this;
        }

        public Builder withContextId(int contextId) {
            this.contextId = contextId;
            return this;
        }

        public Builder withSnapshotDate(Date snapshotDate) {
            this.snapshotDate = snapshotDate;
            return this;
        }

        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder withDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder withWacc(double wacc) {
            this.wacc = wacc;
            return this;
        }

        public Builder withRaroc(double raroc) {
            this.raroc = raroc;
            return this;
        }

        public Builder withCreditAmount(double creditAmount) {
            this.creditAmount = creditAmount;
            return this;
        }

        public Builder withSignificantDigits(int significantDigits) {
            this.significantDigits = significantDigits;
            return this;
        }

        public Builder withMinLimit(double minLimit) {
            this.minLimit = minLimit;
            return this;
        }

        public Builder withWorstAcceptableScore(String worstAcceptableScore) {
            this.worstAcceptableScore = worstAcceptableScore;
            return this;
        }

        public Builder withMinLimitGrowth(double minLimitGrowth) {
            this.minLimitGrowth = minLimitGrowth;
            return this;
        }

        public Builder withMinLimitReduction(double minLimitReduction) {
            this.minLimitReduction = minLimitReduction;
            return this;
        }

        public Builder withMaxLimitGrowth(double maxLimitGrowth) {
            this.maxLimitGrowth = maxLimitGrowth;
            return this;
        }

        public Builder withMaxLimitReduction(double maxLimitReduction) {
            this.maxLimitReduction = maxLimitReduction;
            return this;
        }

        public Builder withMinAcceptedLimitUse(double minAcceptedLimitUse) {
            this.minAcceptedLimitUse = minAcceptedLimitUse;
            return this;
        }

        public Builder withMaxDso(int maxDso) {
            this.maxDso = maxDso;
            return this;
        }

        public Builder withMinAcceptedRwMargin(double minAcceptedRwMargin) {
            this.minAcceptedRwMargin = minAcceptedRwMargin;
            return this;
        }

        public Builder withSalesChange(double salesChange) {
            this.salesChange = salesChange;
            return this;
        }

        public Builder withMarginChange(double marginChange) {
            this.marginChange = marginChange;
            return this;
        }

        public Builder withPdChange(double pdChange) {
            this.pdChange = pdChange;
            return this;
        }

        public Builder withMinLimitPct(double minLimitPct) {
            this.minLimitPct = minLimitPct;
            return this;
        }
    }

}
