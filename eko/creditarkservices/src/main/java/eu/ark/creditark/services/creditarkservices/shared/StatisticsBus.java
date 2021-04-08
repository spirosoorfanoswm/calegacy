package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.HashMap;

public class StatisticsBus implements Serializable {
	private static final long serialVersionUID = -3504482923617905447L;
	private ClienteleStatistics clienteleStatistics;
	private HashMap<Integer,ClienteleStatistics> portfoliosStatistics;


	public ClienteleStatistics getClienteleStatistics() {
		return clienteleStatistics;
	}

	public void setClienteleStatistics(ClienteleStatistics clienteleStatistics) {
		this.clienteleStatistics = clienteleStatistics;
	}

	public HashMap<Integer,ClienteleStatistics> getPortfoliosStatistics() {
		return portfoliosStatistics;
	}

	public void setPortfoliosStatistics(HashMap<Integer,ClienteleStatistics> portfoliosStatistics) {
		this.portfoliosStatistics = portfoliosStatistics;
	}
}
