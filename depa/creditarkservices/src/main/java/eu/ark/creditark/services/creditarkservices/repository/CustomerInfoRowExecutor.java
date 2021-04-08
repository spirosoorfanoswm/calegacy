package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CustomerInfoRowExecutor {
    ExecutorService executor = Executors.newWorkStealingPool();

    List<CustomerInfo> accept(List<CustomerInfoRowCallable> callables) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<CustomerInfo> resp = executor.invokeAll(callables).stream()
                .map(x -> {
                    try {
                        return x.get();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());



        executor.shutdown();
        return resp;
    }

}
