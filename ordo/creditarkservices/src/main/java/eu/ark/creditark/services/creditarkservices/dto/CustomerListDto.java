package eu.ark.creditark.services.creditarkservices.dto;

import eu.ark.creditark.services.creditarkservices.shared.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerListDto implements Serializable{

	private static final long serialVersionUID = 5417589528238863730L;
	private int customersCount;
	private List<Customer> customers;
	public int getCustomersCount() {
		return customersCount;
	}
	public void setCustomersCount(int customersCount) {
		this.customersCount = customersCount;
	}
	public List<Customer> getCustomers() {
		if(null == this.customers)
			this.customers = new ArrayList<>();
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	

}
