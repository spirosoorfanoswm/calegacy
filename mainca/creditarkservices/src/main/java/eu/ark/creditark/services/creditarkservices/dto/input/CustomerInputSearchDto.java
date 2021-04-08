package eu.ark.creditark.services.creditarkservices.dto.input;

import eu.ark.creditark.services.creditarkservices.enums.SearchBy;

import java.util.Date;
import java.util.Objects;

public class CustomerInputSearchDto extends CustomerInputDto {
    private SearchBy searchBy;
    private int portfolioId;
    private String searchKey;
    public CustomerInputSearchDto(String customerId, Date snapshotDate, int period, int contextId, SearchBy searchBy, int portfolioId, String searchKey, int scenarioId) {
        super(customerId, snapshotDate, period, contextId, scenarioId);
        this.searchBy = searchBy;
        this.portfolioId = portfolioId;
        this.searchKey = searchKey;
    }

    public SearchBy getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(SearchBy searchBy) {
        this.searchBy = searchBy;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerInputSearchDto)) return false;
        CustomerInputSearchDto that = (CustomerInputSearchDto) o;
        return getPortfolioId() == that.getPortfolioId() &&
                getSearchBy() == that.getSearchBy() &&
                getSearchKey().equals(that.getSearchKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchBy(), getPortfolioId(), getSearchKey());
    }
}
