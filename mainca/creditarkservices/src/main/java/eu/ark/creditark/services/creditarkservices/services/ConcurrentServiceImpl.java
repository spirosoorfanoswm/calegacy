package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.BusinessRepository;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.shared.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class ConcurrentServiceImpl implements  ConcurrentService{

    @Autowired
    private BusinessService businessService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    //@Async("ConcurrentTaskExecutor")
    public ClienteleStatisticsDto clientStatistic(ClienteleStatisticsInputDto input) {
        //logger.info("Currently Executing clientStatistic - " + Thread.currentThread().getName()+ ":" + input.toString());
        try {
           return businessService.clienteleStatistics(input);
        } catch (DatabaseConnectionException e) {
            logger.error("clientStatistic {}" , e);
        }
       return  null;
    }

    @Override
   // @Async("ConcurrentTaskExecutor")
    public ContextInfo contextInfo(short contextId, String loginName) {
        logger.info("Currently Executing contextInfo - " + Thread.currentThread().getName().concat(""+contextId) +":"+loginName);
        try {
            return businessService.getContextInfo((int) contextId, loginName);
        } catch (DatabaseConnectionException e) {
            logger.error("contextInfo {}", e);
        }
        return  null;
    }

    @Override
  //  @Async("ConcurrentTaskExecutor")
    public List<Customer> getCustomers(CustomersInputDto input) {

        //TODO
        /*logger.info("Currently Executing getCustomers - " + Thread.currentThread().getName().concat(" : ").concat(input.toString()));
        try {
            return null
        } catch (DatabaseConnectionException e) {
            logger.error("contextInfo", e);
        }*/
        return  null;
    }

    public BusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }
}
