package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.shared.Customer;

import java.util.List;
import java.util.concurrent.Future;

public interface ConcurrentService {
    public ClienteleStatisticsDto clientStatistic(ClienteleStatisticsInputDto input);
    public ContextInfo contextInfo(short contextId, String loginName);
    public List<Customer> getCustomers(CustomersInputDto input);
}
