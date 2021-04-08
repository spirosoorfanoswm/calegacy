package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class ClienteleStatistics implements Serializable {
	private static final long serialVersionUID = -7550259318280361161L;

	private Date snapshotDatePrev;
	private int customers;
	private int customersPrev;
	private double balance;
	private double balancePrev;
	private double pastDue;
	private double pastDuePrev;
	private double limits;
	private double limitsPrev;
	private double overrides;
	private double overridesPrev;
	private double unusedLimits;
	private double unusedLimitsPrev;

	private double aggSales;
	private double aggSalesPrev;
	private double aggVat;
	private double aggVatPrev;
	private double aggTurnover;
	private double aggTurnoverPrev;
	private double aggPayments;
	private double aggPaymentsPrev;

	private double meanSales;
	private double meanSalesPrev;
	private double meanTurnover;
	private double meanTurnoverPrev;
	private double meanPayments;
	private double meanPaymentsPrev;
	private double meanExposure;
	private double meanExposurePrev;
	private double meanPastDue;
	private double meanPastDuePrev;
	private double meanLimits;
	private double meanLimitsPrev;
	private double meanOverrides;
	private double meanOverridesPrev;
	private double meanUnusedLimits;
	private double meanUnusedLimitsPrev;

	private double volSales;
	private double volSalesPrev;
	private double volPayments;
	private double volPaymentsPrev;
	private double volExposure;
	private double volExposurePrev;

	private int[] customersPerScore;
	private double[] exposurePerScore;
	private double[] limitsPerScore;
	private Date[] periodSnapshotDate;
	private double[] periodTurnover;
	private double[] periodSales;
	private double[] periodPayments;
	private double[] periodLimit;
	private double[] periodExposure;
	private double[] periodPastDue;

	public Date getSnapshotDatePrev() {
		return snapshotDatePrev;
	}

	public void setSnapshotDatePrev(Date snapshotDatePrev) {
		this.snapshotDatePrev = snapshotDatePrev;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	public int getCustomersPrev() {
		return customersPrev;
	}

	public void setCustomersPrev(int customersPrev) {
		this.customersPrev = customersPrev;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalancePrev() {
		return balancePrev;
	}

	public void setBalancePrev(double balancePrev) {
		this.balancePrev = balancePrev;
	}

	public double getPastDue() {
		return pastDue;
	}

	public void setPastDue(double pastDue) {
		this.pastDue = pastDue;
	}

	public double getPastDuePrev() {
		return pastDuePrev;
	}

	public void setPastDuePrev(double pastDuePrev) {
		this.pastDuePrev = pastDuePrev;
	}

	public double getLimits() {
		return limits;
	}

	public void setLimits(double limits) {
		this.limits = limits;
	}

	public double getLimitsPrev() {
		return limitsPrev;
	}

	public void setLimitsPrev(double limitsPrev) {
		this.limitsPrev = limitsPrev;
	}

	public double getOverrides() {
		return overrides;
	}

	public void setOverrides(double overrides) {
		this.overrides = overrides;
	}

	public double getOverridesPrev() {
		return overridesPrev;
	}

	public void setOverridesPrev(double overridesPrev) {
		this.overridesPrev = overridesPrev;
	}

	public double getUnusedLimits() {
		return unusedLimits;
	}

	public void setUnusedLimits(double unusedLimits) {
		this.unusedLimits = unusedLimits;
	}

	public double getUnusedLimitsPrev() {
		return unusedLimitsPrev;
	}

	public void setUnusedLimitsPrev(double unusedLimitsPrev) {
		this.unusedLimitsPrev = unusedLimitsPrev;
	}

	public double getAggSales() {
		return aggSales;
	}

	public void setAggSales(double aggSales) {
		this.aggSales = aggSales;
	}

	public double getAggSalesPrev() {
		return aggSalesPrev;
	}

	public void setAggSalesPrev(double aggSalesPrev) {
		this.aggSalesPrev = aggSalesPrev;
	}

	public double getAggVat() {
		return aggVat;
	}

	public void setAggVat(double aggVat) {
		this.aggVat = aggVat;
	}

	public double getAggVatPrev() {
		return aggVatPrev;
	}

	public void setAggVatPrev(double aggVatPrev) {
		this.aggVatPrev = aggVatPrev;
	}

	public double getMeanSales() {
		return meanSales;
	}

	public void setMeanSales(double meanSales) {
		this.meanSales = meanSales;
	}

	public double getAggTurnover() {
		return aggTurnover;
	}

	public void setAggTurnover(double aggTurnover) {
		this.aggTurnover = aggTurnover;
	}

	public double getAggTurnoverPrev() {
		return aggTurnoverPrev;
	}

	public void setAggTurnoverPrev(double aggTurnoverPrev) {
		this.aggTurnoverPrev = aggTurnoverPrev;
	}

	public double getMeanTurnover() {
		return meanTurnover;
	}

	public void setMeanTurnover(double meanTurnover) {
		this.meanTurnover = meanTurnover;
	}

	public double getMeanSalesPrev() {
		return meanSalesPrev;
	}

	public void setMeanSalesPrev(double meanSalesPrev) {
		this.meanSalesPrev = meanSalesPrev;
	}

	public double getMeanTurnoverPrev() {
		return meanTurnoverPrev;
	}

	public void setMeanTurnoverPrev(double meanTurnoverPrev) {
		this.meanTurnoverPrev = meanTurnoverPrev;
	}

	public double getVolSales() {
		return volSales;
	}

	public void setVolSales(double volSales) {
		this.volSales = volSales;
	}

	public double getVolSalesPrev() {
		return volSalesPrev;
	}

	public void setVolSalesPrev(double volSalesPrev) {
		this.volSalesPrev = volSalesPrev;
	}

	public double getMeanExposure() {
		return meanExposure;
	}

	public void setMeanExposure(double meanExposure) {
		this.meanExposure = meanExposure;
	}

	public double getMeanExposurePrev() {
		return meanExposurePrev;
	}

	public void setMeanExposurePrev(double meanExposurePrev) {
		this.meanExposurePrev = meanExposurePrev;
	}

	public double getVolExposure() {
		return volExposure;
	}

	public void setVolExposure(double volExposure) {
		this.volExposure = volExposure;
	}

	public double getVolExposurePrev() {
		return volExposurePrev;
	}

	public void setVolExposurePrev(double volExposurePrev) {
		this.volExposurePrev = volExposurePrev;
	}

	public double getMeanPastDue() {
		return meanPastDue;
	}

	public void setMeanPastDue(double meanPastDue) {
		this.meanPastDue = meanPastDue;
	}

	public double getMeanPastDuePrev() {
		return meanPastDuePrev;
	}

	public void setMeanPastDuePrev(double meanPastDuePrev) {
		this.meanPastDuePrev = meanPastDuePrev;
	}

	public double getMeanLimits() {
		return meanLimits;
	}

	public void setMeanLimits(double meanLimits) {
		this.meanLimits = meanLimits;
	}

	public double getMeanLimitsPrev() {
		return meanLimitsPrev;
	}

	public void setMeanLimitsPrev(double meanLimitsPrev) {
		this.meanLimitsPrev = meanLimitsPrev;
	}

	public double getMeanOverrides() {
		return meanOverrides;
	}

	public void setMeanOverrides(double meanOverrides) {
		this.meanOverrides = meanOverrides;
	}

	public double getMeanOverridesPrev() {
		return meanOverridesPrev;
	}

	public void setMeanOverridesPrev(double meanOverridesPrev) {
		this.meanOverridesPrev = meanOverridesPrev;
	}

	public double getMeanUnusedLimits() {
		return meanUnusedLimits;
	}

	public void setMeanUnusedLimits(double meanUnusedLimits) {
		this.meanUnusedLimits = meanUnusedLimits;
	}

	public double getMeanUnusedLimitsPrev() {
		return meanUnusedLimitsPrev;
	}

	public void setMeanUnusedLimitsPrev(double meanUnusedLimitsPrev) {
		this.meanUnusedLimitsPrev = meanUnusedLimitsPrev;
	}

	public int[] getCustomersPerScore() {
		return customersPerScore;
	}

	public void setCustomersPerScore(int[] customersPerScore) {
		this.customersPerScore = customersPerScore;
	}

	public double[] getExposurePerScore() {
		return exposurePerScore;
	}

	public void setExposurePerScore(double[] exposurePerScore) {
		this.exposurePerScore = exposurePerScore;
	}

	public double[] getLimitsPerScore() {
		return limitsPerScore;
	}

	public void setLimitsPerScore(double[] limitsPerScore) {
		this.limitsPerScore = limitsPerScore;
	}

	public Date[] getPeriodSnapshotDate() {
		return periodSnapshotDate;
	}

	public void setPeriodSnapshotDate(Date[] periodSnapshotDate) {
		this.periodSnapshotDate = periodSnapshotDate;
	}

	public double[] getPeriodTurnover() {
		return periodTurnover;
	}

	public void setPeriodTurnover(double[] periodTurnover) {
		this.periodTurnover = periodTurnover;
	}

	public double[] getPeriodLimit() {
		return periodLimit;
	}

	public void setPeriodLimit(double[] periodLimit) {
		this.periodLimit = periodLimit;
	}

	public double[] getPeriodExposure() {
		return periodExposure;
	}

	public void setPeriodExposure(double[] periodExposure) {
		this.periodExposure = periodExposure;
	}

	public double[] getPeriodPastDue() {
		return periodPastDue;
	}

	public void setPeriodPastDue(double[] periodPastDue) {
		this.periodPastDue = periodPastDue;
	}

	public double getAggPayments() {
		return aggPayments;
	}

	public void setAggPayments(double aggPayments) {
		this.aggPayments = aggPayments;
	}

	public double getAggPaymentsPrev() {
		return aggPaymentsPrev;
	}

	public void setAggPaymentsPrev(double aggPaymentsPrev) {
		this.aggPaymentsPrev = aggPaymentsPrev;
	}

	public double getMeanPayments() {
		return meanPayments;
	}

	public void setMeanPayments(double meanPayments) {
		this.meanPayments = meanPayments;
	}

	public double getMeanPaymentsPrev() {
		return meanPaymentsPrev;
	}

	public void setMeanPaymentsPrev(double meanPaymentsPrev) {
		this.meanPaymentsPrev = meanPaymentsPrev;
	}

	public double getVolPayments() {
		return volPayments;
	}

	public void setVolPayments(double volPayments) {
		this.volPayments = volPayments;
	}

	public double getVolPaymentsPrev() {
		return volPaymentsPrev;
	}

	public void setVolPaymentsPrev(double volPaymentsPrev) {
		this.volPaymentsPrev = volPaymentsPrev;
	}

	public double[] getPeriodPayments() {
		return periodPayments;
	}

	public void setPeriodPayments(double[] periodPayments) {
		this.periodPayments = periodPayments;
	}

	public double getUnusedPct() {
		return unusedLimits / limits;
	}

	public double getUnusedPctPrev() {
		return unusedLimitsPrev / limitsPrev;
	}

	public double getOverriddenPct() {
		return this.overrides / this.balance;
	}

	public double getOverriddenPctPrev() {
		return this.overridesPrev / this.balancePrev;
	}

	public double getPastPuePct() {
		return this.pastDue / this.balance;
	}

	public double getPastPuePctPrev() {
		return this.pastDuePrev / this.balance;
	}

	public double getMeanOverridesPct() {
		return meanOverrides / limits;
	}

	public double getMeanOverridesPctPrev() {
		return meanOverridesPrev / limitsPrev;
	}

	public double getMeanUnusedLimitsPct() {
		return meanUnusedLimits / limits;
	}

	public double getMeanUnusedLimitsPctPrev() {
		return meanUnusedLimitsPrev / limitsPrev;
	}

	public double getMeanDso() {
		return meanExposure * 360 / aggSales;
	}

	public double getMeanDsoPrev() {
		return meanExposurePrev * 360 / aggSalesPrev;
	}

	public int[] getCurCustomersPerScore() {
		int l = customersPerScore.length;
		return Arrays.copyOfRange(customersPerScore, l / 2, l);
	}

	public double[] getCurExposurePerScore() {
		return getLastHalf(exposurePerScore);
	}

	public double[] getCurLimitsPerScore() {
		return getLastHalf(limitsPerScore);
	}

	public double getAnnualTurnover() {
		double r = 0;
		for (int i = periodTurnover.length - 1, j = 0; j < 12 && i >= 0; i--, j++)
			r += periodTurnover[i];
		return r;
	}

	public double getAnnualSales() {
		double r = 0;
		for (int i = periodSales.length - 1, j = 0; j < 12 && i >= 0; i--, j++)
			r += periodSales[i];
		return r;
	}

	// private function

	private double[] getLastHalf(double[] value) {
		int l = value.length;
		return Arrays.copyOfRange(value, l / 2, l);
	}

	public void setPeriodSales(double[] periodSales) {
		this.periodSales = periodSales;
	}

	public double[] getperiodSales() {
		return periodSales;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ClienteleStatistics{");
		sb.append("snapshotDatePrev=").append(snapshotDatePrev);
		sb.append(", customers=").append(customers);
		sb.append(", customersPrev=").append(customersPrev);
		sb.append(", balance=").append(balance);
		sb.append(", balancePrev=").append(balancePrev);
		sb.append(", pastDue=").append(pastDue);
		sb.append(", pastDuePrev=").append(pastDuePrev);
		sb.append(", limits=").append(limits);
		sb.append(", limitsPrev=").append(limitsPrev);
		sb.append(", overrides=").append(overrides);
		sb.append(", overridesPrev=").append(overridesPrev);
		sb.append(", unusedLimits=").append(unusedLimits);
		sb.append(", unusedLimitsPrev=").append(unusedLimitsPrev);
		sb.append(", aggSales=").append(aggSales);
		sb.append(", aggSalesPrev=").append(aggSalesPrev);
		sb.append(", aggVat=").append(aggVat);
		sb.append(", aggVatPrev=").append(aggVatPrev);
		sb.append(", aggTurnover=").append(aggTurnover);
		sb.append(", aggTurnoverPrev=").append(aggTurnoverPrev);
		sb.append(", aggPayments=").append(aggPayments);
		sb.append(", aggPaymentsPrev=").append(aggPaymentsPrev);
		sb.append(", meanSales=").append(meanSales);
		sb.append(", meanSalesPrev=").append(meanSalesPrev);
		sb.append(", meanTurnover=").append(meanTurnover);
		sb.append(", meanTurnoverPrev=").append(meanTurnoverPrev);
		sb.append(", meanPayments=").append(meanPayments);
		sb.append(", meanPaymentsPrev=").append(meanPaymentsPrev);
		sb.append(", meanExposure=").append(meanExposure);
		sb.append(", meanExposurePrev=").append(meanExposurePrev);
		sb.append(", meanPastDue=").append(meanPastDue);
		sb.append(", meanPastDuePrev=").append(meanPastDuePrev);
		sb.append(", meanLimits=").append(meanLimits);
		sb.append(", meanLimitsPrev=").append(meanLimitsPrev);
		sb.append(", meanOverrides=").append(meanOverrides);
		sb.append(", meanOverridesPrev=").append(meanOverridesPrev);
		sb.append(", meanUnusedLimits=").append(meanUnusedLimits);
		sb.append(", meanUnusedLimitsPrev=").append(meanUnusedLimitsPrev);
		sb.append(", volSales=").append(volSales);
		sb.append(", volSalesPrev=").append(volSalesPrev);
		sb.append(", volPayments=").append(volPayments);
		sb.append(", volPaymentsPrev=").append(volPaymentsPrev);
		sb.append(", volExposure=").append(volExposure);
		sb.append(", volExposurePrev=").append(volExposurePrev);
		sb.append(", customersPerScore=").append(Arrays.toString(customersPerScore));
		sb.append(", exposurePerScore=").append(Arrays.toString(exposurePerScore));
		sb.append(", limitsPerScore=").append(Arrays.toString(limitsPerScore));
		sb.append(", periodSnapshotDate=").append(Arrays.toString(periodSnapshotDate));
		sb.append(", periodTurnover=").append(Arrays.toString(periodTurnover));
		sb.append(", periodSales=").append(Arrays.toString(periodSales));
		sb.append(", periodPayments=").append(Arrays.toString(periodPayments));
		sb.append(", periodLimit=").append(Arrays.toString(periodLimit));
		sb.append(", periodExposure=").append(Arrays.toString(periodExposure));
		sb.append(", periodPastDue=").append(Arrays.toString(periodPastDue));
		sb.append('}');
		return sb.toString();
	}
}
