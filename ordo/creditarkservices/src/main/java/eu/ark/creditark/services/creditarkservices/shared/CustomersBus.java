package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.List;

public class CustomersBus implements Serializable {
	private static final long serialVersionUID = -4332942728699254491L;
	private List<Customer> customers;
	private int customersNum;
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getCustomersNum() {
		return customersNum;
	}

	public void setCustomersNum(int customersNum) {
		this.customersNum = customersNum;
	}
}
