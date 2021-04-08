package eu.ark.creditark.services.creditarkservices.shared;

public class Customer extends CustomerIdentity {
	private static final long serialVersionUID = 6984318941813645644L;
	private String customerStatus;
	private double balance;
	private double creditLimit;
	private double proposedLimit;
	private String score;
	private String behavioralScore;

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getProposedLimit() {
		return proposedLimit;
	}

	public void setProposedLimit(double proposedLimit) {
		this.proposedLimit = proposedLimit;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getBehavioralScore() {
		return behavioralScore;
	}

	public void setBehavioralScore(String behavioralScore) {
		this.behavioralScore = behavioralScore;
	}
}
