package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PortfolioAgingOverall implements Serializable  {
    List<PortfolioAging> portfolioAgingList;
    List<PortfolioAging> portfolioDunningAgingList;
    AveragePortfolioAging averageDaysToPay;
    AveragePortfolioAging averageDaysUnpaid;

    public List<PortfolioAging> getPortfolioAgingList() {
        if(null==this.portfolioAgingList) this.portfolioAgingList = new ArrayList<>(1);
        return portfolioAgingList;
    }

    public void setPortfolioAgingList(List<PortfolioAging> portfolioAgingList) {
        this.portfolioAgingList = portfolioAgingList;
    }

    public List<PortfolioAging> getPortfolioDunningAgingList() {
        if(null==this.portfolioDunningAgingList) this.portfolioDunningAgingList = new ArrayList<>(1);
        return portfolioDunningAgingList;
    }

    public void setPortfolioDunningAgingList(List<PortfolioAging> portfolioDunningAgingList) {
        this.portfolioDunningAgingList = portfolioDunningAgingList;
    }

    public AveragePortfolioAging getAverageDaysToPay() {
        return averageDaysToPay;
    }

    public void setAverageDaysToPay(AveragePortfolioAging averageDaysToPay) {
        this.averageDaysToPay = averageDaysToPay;
    }

    public AveragePortfolioAging getAverageDaysUnpaid() {
        return averageDaysUnpaid;
    }

    public void setAverageDaysUnpaid(AveragePortfolioAging averageDaysUnpaid) {
        this.averageDaysUnpaid = averageDaysUnpaid;
    }
}
